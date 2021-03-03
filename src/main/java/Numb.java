import org.jetbrains.annotations.NotNull;

public class Numb {
    private long number;
    private int precision = 0;
    private int scale = 0;

    public Numb(@NotNull String s, int precision){
        if (precision <= 0 || precision >= 19) throw new IllegalArgumentException("Указанная точность некорректна");
        String[] str = s.trim().split("\\.");
        if (str.length > 2 || !s.matches("\\A[-]?\\d+([.](\\d+))?\\z"))
            throw new IllegalArgumentException("Введено некорректное число");
        setValue(str, precision);
    }

    public Numb(double d, int precision){
        if (precision <= 0 || precision >= 19) throw new IllegalArgumentException("Указанная точность некорректна");
        String[] str = String.valueOf(d).trim().split("\\.");
        if (str.length > 2 || !String.valueOf(d).matches("\\A[-]?\\d+([.](\\d+))?\\z"))
            throw new IllegalArgumentException("Введено некорректное число");
        setValue(str, precision);
    }

    public Numb(float f, int precision){
        if (precision <= 0 || precision >= 19) throw new IllegalArgumentException("Указанная точность некорректна");
        String[] str = String.valueOf(f).trim().split("\\.");
        if (str.length > 2 || !String.valueOf(f).matches("\\A[-]?\\d+([.](\\d+))?\\z"))
            throw new IllegalArgumentException("Введено некорректное число");
        setValue(str, precision);
    }

    public Numb(int i, int precision){
        setValue(new String[] {String.valueOf(i)}, precision);
    }

    public Numb(long l, int precision){
        setValue(new String[] {String.valueOf(l)}, precision);
    }

    private void setValue(String[] str, int precision){
        this.precision = precision;
        char sign = ' ';
        if (str[0].charAt(0) == '-') sign = '-';
        str[0] = String.valueOf(Math.abs(Long.parseLong(str[0])));
        switch (str.length){
            case 2 -> {
                int lenOfNum = str[0].length() + str[1].length();
                String strNum = str[0] + str[1];
                StringBuilder sb = new StringBuilder();
                if (precision > lenOfNum){
                    scale = str[1].length();
                    sb.append(sign).append(strNum);
                    number = Long.parseLong(sb.toString().trim());
                } else if (precision < lenOfNum){
                    scale = str[1].length() - (lenOfNum - precision);
                    sb.append(sign).append(strNum, 0, precision);
                    number = Long.parseLong(sb.toString().trim());

                } else {
                    scale = str[1].length();
                    number = Long.parseLong((sign + strNum).trim());
                }
                if (precision < lenOfNum && Integer.parseInt(String.valueOf(strNum.charAt(precision))) >= 5) {
                    if (number > 0) number++;
                    else number--;
                }
            }
            case 1 -> {
                if (precision > str[0].length()){
                    number = Long.parseLong((sign + str[0]).trim());
                } else if (precision < str[0].length()){
                    scale = precision - str[0].length();
                    number = Long.parseLong((sign + str[0]).trim().substring(0, precision));
                } else {
                    scale = 0;
                    number = Long.parseLong((sign + str[0]).trim());
                }
                if (precision < str[0].length() && Integer.parseInt(String.valueOf(str[0].charAt(precision))) >= 5) {
                    if (number > 0) number++;
                    else number--;
                }
            }
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
        String rt = String.valueOf((long) (number / Math.pow(10, scale - i)));
        StringBuilder sb = new StringBuilder();
        sb.append(rt).insert(rt.length() - i, ".");
        if (rt.length() - i == 0) sb.insert(0, "0");
        if (i == 0) sb.append(0);
        return new Numb(sb.toString(), precision);
    }

    public Numb roundingMath(int i) {
        long rt = (long) (number / Math.pow(10, scale - i));
        String strNum = String.valueOf(number);
        StringBuilder sb = new StringBuilder();
        int newLen = String.valueOf(number).length() - scale + i;

        if (newLen < strNum.length() && Integer.parseInt(String.valueOf(strNum.charAt(newLen))) >= 5) rt++;

        sb.append(rt).insert(String.valueOf(rt).length() - i, ".");
        if (String.valueOf(rt).length() - i == 0) sb.insert(0, "0");
        if (i == 0) sb.append(0);

        return new Numb(sb.toString(), precision);
    }

    public int toInt(){ return (int) (number / Math.pow(10, scale)); }

    public long toLong(){ return (long) (number / Math.pow(10, scale)); }

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

    public Numb plus(Numb anotherNumb){
        long firstNum = number;
        long secondNum = anotherNumb.number;
        int maxScale = Math.max(scale, anotherNumb.scale);
        int minPrecision = Math.min(precision, anotherNumb.precision);

        if (scale != maxScale) firstNum = aligment(firstNum, scale, maxScale);
        if (anotherNumb.scale != maxScale) secondNum = aligment(secondNum, anotherNumb.scale, maxScale);
        if (firstNum + secondNum == 0) return new Numb(0, minPrecision);

        int dot = String.valueOf(firstNum + secondNum).length() - maxScale;
        StringBuilder sb = new StringBuilder();
        sb.append(firstNum + secondNum).insert(dot, ".");
        if (dot == 0) sb.insert(0, "0");

        return new Numb(sb.toString(), minPrecision);
    }

    public Numb minus(Numb anotherNumb){
        long firstNum = number;
        long secondNum = anotherNumb.number;
        int maxScale = Math.max(scale, anotherNumb.scale);
        int minPrecision = Math.min(precision, anotherNumb.precision);

        if (scale != maxScale) firstNum = aligment(firstNum, scale, maxScale);
        if (anotherNumb.scale != maxScale) secondNum = aligment(secondNum, anotherNumb.scale, maxScale);
        if (firstNum - secondNum == 0) return new Numb(0, minPrecision);

        int dot = String.valueOf(firstNum - secondNum).length() - maxScale;
        StringBuilder sb = new StringBuilder();
        sb.append(firstNum - secondNum).insert(dot, ".");
        if (dot == 0) sb.insert(0, "0");

        return new Numb(sb.toString(), minPrecision);
    }

    public Numb multiplication(Numb anotherNumb){
        long firstNum = number;
        long secondNum = anotherNumb.number;
        int lenOfFracts = scale + anotherNumb.scale;
        int minPrecision = Math.min(precision, anotherNumb.precision);

        long result = firstNum*secondNum;
        int lenOfRes = String.valueOf(result).length();

        StringBuilder sb = new StringBuilder();
        if (lenOfRes - lenOfFracts < String.valueOf(result).length()) sb.append(firstNum * secondNum).insert(lenOfRes - lenOfFracts, ".");
        else sb.append(firstNum * secondNum);

        return new Numb(sb.toString(), minPrecision);
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

        StringBuilder sb = new StringBuilder();
        int count = 0;
        String toR = String.valueOf(firstNum / secondNum);
        int difLen = toR.length() - idx;

        if (difLen < 0) {
            sb.append("0.");
            while (difLen < 0) {
                sb.append(0);
                difLen++;
            }
        }
        while(count != toR.length()){
            if (count == toR.length() - idx) sb.append(".");
            sb.append(toR.charAt(count));
            count++;
        }

        return new Numb(sb.toString(), minPrecision);
    }
}