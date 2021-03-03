import org.jetbrains.annotations.NotNull;

public class Numb {
    private long number;
    private int precision = 0;
    private int scale = 0;

    public Numb(@NotNull String s, int precision){ setValue(s, precision); }

    public Numb(double d, int precision){ setValue(String.valueOf(d), precision); }

    public Numb(float f, int precision){ setValue(String.valueOf(f), precision); }

    public Numb(int i, int precision){ setValue(String.valueOf(i), precision); }

    public Numb(long l, int precision){ setValue(String.valueOf(l), precision); }

    private void setValue(String s, int precision){
        if (precision <= 0 || precision >= 19) throw new IllegalArgumentException("Указанная точность некорректна");
        this.precision = precision;

        String[] strNum = s.trim().split("\\.");
        if (strNum.length > 2 || !s.matches("\\A[-]?\\d+[.]?(\\d+)?\\z"))
            throw new IllegalArgumentException("Введено некорректное число");

        char sign = ' ';
        if (strNum[0].charAt(0) == '-') sign = '-';

        String[] str = new String[2];
        str[0] = String.valueOf(Math.abs(Long.parseLong(strNum[0])));

        if(strNum.length == 2) str[1] = strNum[1];
        else str[1] = "0";

        int lenOfNum = str[0].length() + str[1].length();
        String fullNum = str[0] + str[1];
        StringBuilder sb = new StringBuilder();

        if (precision >= lenOfNum){
            scale = str[1].length();
            sb.append(sign).append(fullNum);
        } else {
            scale = str[1].length() - (lenOfNum - precision);
            sb.append(sign).append(fullNum, 0, precision);
        }
        number = Long.parseLong(sb.toString().trim());
        if (precision < lenOfNum && Integer.parseInt(String.valueOf(fullNum.charAt(precision))) >= 5) {
            if (number > 0) number++;
            else number--;
        }
    }

    public String getValue(){
        StringBuilder get = new StringBuilder();
        if (String.valueOf(number).length() - scale < 0){
            get.append("0.").append("0".repeat(Math.abs(String.valueOf(number).length() - scale)));
        }
        for (int i = 0; i < String.valueOf(number).length(); i++){
            if (i == String.valueOf(number).length() - scale) {
                get.append(".");
            }
            get.append(String.valueOf(number).charAt(i));
        }
        if (String.valueOf(number).length() - scale == 0) get.insert(0, "0");
        get.append("0".repeat(Math.max(0, -scale)));
        return get.toString(); }

    public Numb roundingToZero(int i) {
        long rt = (long) (number / Math.pow(10, scale - i));
        return new Numb(collector(rt, i), precision);
    }

    public Numb roundingMath(int i) {
        long rt = (long) (number / Math.pow(10, scale - i));
        if (String.valueOf(number).length() - scale + i < String.valueOf(number).length() &&
                (int)(number % Math.pow(10, scale - i)) / Math.pow(10, scale - i - 1) >= 5)
            rt++;
        return new Numb(collector(rt, i), precision);
    }

    public int toInt(){
        double toRet = number / Math.pow(10, scale);
        if (toRet * 10 % 10 >= 5) return (int) (toRet + 1);
        else return (int) toRet;
    }

    public long toLong(){
        double toRet = number / Math.pow(10, scale);
        if (toRet * 10 % 10 >= 5) return (long) (toRet + 1);
        else return (long) toRet;
    }

    public float toFloat(){ return (float) (number / Math.pow(10, scale)); }

    public double toDouble(){ return number / Math.pow(10, scale); }

    private long aligment(long num, int scale,  int maxScale){
        int defOfScale = maxScale - scale;
        while (defOfScale != 0){
            num *= 10;
            defOfScale--;
        }
        return num;
    }

    private String collector(long num, int scale){
        StringBuilder sb = new StringBuilder();
        int difLen = String.valueOf(num).length() - scale;
        if (difLen <= 0) {
            sb.append("0.");
            while (difLen < 0) {
                sb.append(0);
                difLen++;
            }
            sb.append(num);
        } else sb.append(num).insert(difLen, ".");
        return sb.toString();
    }

    public Numb plus(Numb anotherNumb){
        long firstNum = number;
        long secondNum = anotherNumb.number;
        int maxScale = Math.max(scale, anotherNumb.scale);
        int minPrecision = Math.min(precision, anotherNumb.precision);

        if (scale != maxScale) firstNum = aligment(firstNum, scale, maxScale);
        if (anotherNumb.scale != maxScale) secondNum = aligment(secondNum, anotherNumb.scale, maxScale);
        if (firstNum + secondNum == 0) return new Numb(0, minPrecision);

        return new Numb(collector(firstNum + secondNum, maxScale), minPrecision);
    }

    public Numb minus(Numb anotherNumb){
        long firstNum = number;
        long secondNum = anotherNumb.number;
        int maxScale = Math.max(scale, anotherNumb.scale);
        int minPrecision = Math.min(precision, anotherNumb.precision);

        if (scale != maxScale) firstNum = aligment(firstNum, scale, maxScale);
        if (anotherNumb.scale != maxScale) secondNum = aligment(secondNum, anotherNumb.scale, maxScale);
        if (firstNum - secondNum == 0) return new Numb(0, minPrecision);

        return new Numb(collector(firstNum - secondNum, maxScale), minPrecision);
    }

    public Numb multiplication(Numb anotherNumb){
        long firstNum = number;
        long secondNum = anotherNumb.number;
        int lenOfFracts = scale + anotherNumb.scale;
        int minPrecision = Math.min(precision, anotherNumb.precision);

        long result = firstNum*secondNum;
        if (result == 0) return new Numb(0, 1);

        return new Numb(collector(firstNum * secondNum, lenOfFracts), minPrecision);
    }

    public Numb division(Numb anotherNumb){
        long firstNum = number;
        long secondNum = anotherNumb.number;
        int maxScale = Math.max(scale,anotherNumb.scale);
        int minPrecision = Math.min(precision, anotherNumb.precision);

        if (scale != maxScale) firstNum = aligment(firstNum, scale, maxScale);
        if (anotherNumb.scale != maxScale) secondNum = aligment(secondNum, anotherNumb.scale, maxScale);
        if (firstNum == 0) return new Numb(0, 1);
        if (secondNum == 0) throw new ArithmeticException("Нельзя делить на ноль");

        int idx = 0;
        while (firstNum % secondNum != 0 && idx < minPrecision){
            idx++;
            firstNum *= 10;
        }

        return new Numb(collector(firstNum / secondNum, idx), minPrecision);
    }
}