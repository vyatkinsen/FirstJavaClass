import org.jetbrains.annotations.NotNull;

public class Numb {
    private final String strNum;
    private  long number;
    private  int scale = 0;

    public Numb(@NotNull String s, int scale) {
        strNum = s;
        this.scale = scale;
        String[] str = s.trim().split("\\.");

        if (str.length>2 || !s.matches("\\A[-]?\\d+([.](\\d+))?\\z")) throw new IllegalArgumentException("Введено некорректное число");

        number = Math.abs(Long.parseLong(str[0]));
        if (str.length == 2) {
            int idx = 0;
            while (idx<scale){
                number = number *10 + Integer.parseInt(String.valueOf(str[1].charAt(idx)));
                idx++;
            }
            if (scale < str[1].length() && Integer.parseInt(String.valueOf(str[1].charAt(scale))) >= 5) number++;
            if ((str[0]).charAt(0)=='-') number = -number;
            }
    }

    public Numb(double d, int scale){
        strNum = String.valueOf(d);
        this.scale = scale;
        String[] str = String.valueOf(d).trim().split("\\.");

        if (str.length>2 || !strNum.matches("\\A[-]?\\d+([.](\\d+))?\\z")) throw new IllegalArgumentException("Введено некорректное число");

        number = Math.abs(Long.parseLong(str[0]));
        int idx = 0;
        while (idx<scale){
            number = number *10 + Integer.parseInt(String.valueOf(str[1].charAt(idx)));
            idx++;
        }
        if (scale < str[1].length() && Integer.parseInt(String.valueOf(str[1].charAt(scale))) >= 5) number++;
        if (d<0) number = -number;
    }

    public Numb(float f, int scale){
        strNum = String.valueOf(f);
        this.scale = scale;
        String[] str = String.valueOf(f).trim().split("\\.");

        if (str.length>2 || !strNum.matches("\\A[-]?\\d+([.](\\d+))?\\z")) throw new IllegalArgumentException("Введено некорректное число");

        number = Math.abs(Long.parseLong(str[0]));
        int idx = 0;
        while (idx<scale){
            number = number *10 + Integer.parseInt(String.valueOf(str[1].charAt(idx)));
            idx++;
        }
        if (scale < str[1].length() && Integer.parseInt(String.valueOf(str[1].charAt(scale))) >= 5) number++;
        if (f<0) number = -number;
    }

    public Numb(int i){
        strNum = String.valueOf(i);
        number = i;
    }

    public Numb(long l){
        strNum = String.valueOf(l);
        number = l;
    }


    public String getValue(){ return String.valueOf(number / Math.pow(10, scale)); }

    public double roundingToZero(int i) { return Math.floor(number / Math.pow(10, scale - i))/Math.pow(10, i); }

    public double roundingMath(int i){ return Math.round(number / Math.pow(10, scale - i))/Math.pow(10, i); }

    public int toInt(){ return (int) (number / Math.pow(10, scale)); }

    public long toLong(){ return (long) (number / Math.pow(10, scale)); }

    public float toFloat(){ return (float) (number / Math.pow(10, scale)); }

    public double toDouble(){ return number / Math.pow(10, scale); }

    private long aligment(long num, int scale,  int maxScale){
        int defOfScale = maxScale - scale;
        while (defOfScale != 0){
            num*=10;
            defOfScale--;
        }
        return num;
    }

    public Numb plus(Numb anotherNumb){
        long firstNum = number;
        long secondNum = anotherNumb.number;
        int maxScale = Math.max(scale, anotherNumb.scale);

        if (scale != maxScale) firstNum = aligment(firstNum, scale, maxScale);
        if (anotherNumb.scale != maxScale) secondNum = aligment(secondNum, anotherNumb.scale, maxScale);

        String result = String.valueOf(firstNum+secondNum);
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < result.length(); i++){
            if (i == result.length() - maxScale) res.append(".");
            res.append(result.charAt(i));
        }
        if (res.charAt(0) == '.') res.insert(0, "0");
        return new Numb(res.toString(), maxScale);
    }

    public Numb minus(Numb anotherNumb){
        long firstNum = number;
        long secondNum = anotherNumb.number;
        int maxScale = Math.max(scale, anotherNumb.scale);

        if (scale != maxScale) firstNum = aligment(firstNum, scale, maxScale);
        if (anotherNumb.scale != maxScale) secondNum = aligment(secondNum, anotherNumb.scale, maxScale);

        String result = String.valueOf(firstNum-secondNum);
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < result.length(); i++){
            if (i == result.length() - maxScale) res.append(".");
            res.append(result.charAt(i));
        }
        return new Numb(res.toString(), maxScale);
    }

    public Numb multiplication(Numb anotherNumb){
        long firstNum = number;
        long secondNum = anotherNumb.number;
        int lenOfFracts = scale + anotherNumb.scale;
        int maxScale = Math.max(scale,anotherNumb.scale);

        String result = String.valueOf(firstNum*secondNum);
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < result.length(); i++){
            if (i == result.length() - lenOfFracts) res.append(".");
            res.append(result.charAt(i));
        }
        return new Numb(res.toString(), maxScale);
    }

    public Numb division(Numb anotherNumb){
        long firstNum = number;
        long secondNum = anotherNumb.number;
        int maxScale = Math.max(scale,anotherNumb.scale);
        int minScale = Math.min(scale,anotherNumb.scale);

        if (scale != maxScale) firstNum = aligment(firstNum, scale, maxScale);
        if (anotherNumb.scale != maxScale) secondNum = aligment(secondNum, anotherNumb.scale, maxScale);
        if (firstNum == 0) return new Numb("0.0", 1);
        if (secondNum == 0) throw new ArithmeticException("Нельзя делить на ноль");
        int stop = 8;
        int idx = 0;
        while (firstNum%secondNum!=0 && idx<stop){
            idx++;
            firstNum*=10;
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        String toR = String.valueOf(firstNum/secondNum);
        int difLen = toR.length() - idx;

        if (difLen < 0) {
            sb.append("0.");
            while (difLen<0) {
                sb.append(0);
                difLen++;
            }
        }
        while(count != toR.length()){
            if (count == toR.length() - idx) sb.append(".");
            sb.append(toR.charAt(count));
            count++;
        }
        return (new Numb(sb.toString(), minScale));
    }
}