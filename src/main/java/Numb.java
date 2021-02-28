import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Numb {
    private final String number;
    private final String whole;
    private final String fractional;
    private boolean sign = false;

    public Numb(@NotNull String s) {
        number = s;
        String[] str = s.split("\\.");
        if (str.length>2) throw new IllegalArgumentException();
        if (String.valueOf(number.charAt(0)).equals("-"))  sign = true;
        whole = String.valueOf(Math.abs(Long.parseLong(str[0])));
        if (str.length == 1 || (str[1].length()==1 && str[1].charAt(0) == '0')) fractional = "0";
        else {
            StringBuilder sb = new StringBuilder();
            int idx = str[1].length() ;
            while (idx>0){
                if (str[1].charAt(idx - 1) == '0'){
                    idx--;
                }
                else break;
            }
            while(idx>0){
                sb.insert(0, str[1].charAt(idx - 1));
                idx--;
            }
            fractional = sb.toString();}
    }

    public String getValue(){
        if (sign) return "-" + whole + "." + fractional;
        else  return whole + "." + fractional;
    }

    public int before() { return String.valueOf(whole).length(); }

    public int after() { return fractional.length(); }

    public String numberToString() { return number; }

    public double stringToNumber() {
        if (sign) return -Double.parseDouble(whole + "." + fractional);
        else  return Double.parseDouble(whole + "." + fractional);
    }

    public Numb roundingToZero() {
        if (sign) return new Numb("-" + whole);
        else return new Numb(whole);
    }

    public Numb roundingMath() {
        int lenOfFract = fractional.length();
        long toRes;
        int r = 5;
        while (String.valueOf(r).length() < lenOfFract) r *= 10;

        if (String.valueOf( r + Long.parseLong(fractional)).length() > lenOfFract) toRes =  Long.parseLong(whole) + 1;
        else toRes = Long.parseLong(whole);

        if (sign) return new Numb(String.valueOf((-toRes)));
        else return new Numb(String.valueOf((toRes)));
    }

    public int toInt(){
        if (sign) return -Integer.parseInt(whole);
        else return Integer.parseInt(whole); }

    public long toLong(){
        if (sign) return -Long.parseLong(whole);
        else return Long.parseLong(whole); }

    public float toFloat(){ return Float.parseFloat(number); }

    public double toDouble(){ return Double.parseDouble(number); }

    private List<Integer> alignmentWhole(@NotNull List<Integer> list, int maxSize){
        int fal = list.size();
        if (fal < maxSize) {
            while (fal != maxSize) {
                list.add(0, 0);
                fal++;
            }
        }
        return list;
    }

    private List<Integer> alignmentFractional(@NotNull List<Integer> list, int maxSize){
        int fal = list.size();
        if (fal < maxSize) {
            while (fal != maxSize) {
                list.add(0);
                fal++;
            }
        }
        return list;
    }

    private long[] aligment(@NotNull String secondWhole, String secondFractional) {
        List<Integer> firstWholeList = new ArrayList<>(), secondWholeList = new ArrayList<>();
        int fwl = whole.length();
        int swl = secondWhole.length();

        for (int i = 0; i < fwl; i++) firstWholeList.add(Integer.parseInt(String.valueOf(whole.charAt(i))));
        for (int i = 0; i < swl; i++) secondWholeList.add(Integer.parseInt(String.valueOf(secondWhole.charAt(i))));

        int maxWhlen = Math.max(fwl, swl);

        List<Integer> alignmentFirstWholeList = alignmentWhole(firstWholeList, maxWhlen);
        List<Integer> alignmentSecondWholeList = alignmentWhole(secondWholeList, maxWhlen);

        for (int i = 0; i < fractional.length(); i++) alignmentFirstWholeList.add(Integer.parseInt(String.valueOf(fractional.charAt(i))));
        for (int i = 0; i < secondFractional.length(); i++) alignmentSecondWholeList.add(Integer.parseInt(String.valueOf(secondFractional.charAt(i))));

        int maxSize = Math.max(alignmentFirstWholeList.size(), alignmentSecondWholeList.size());

        List<Integer> firstNumList = alignmentFractional(alignmentFirstWholeList, maxSize);
        List<Integer> secondNumList = alignmentFractional(alignmentSecondWholeList, maxSize);

        long firstNum = 0, secondNum = 0;
        long co = 1;
        int size = firstNumList.size();

        for (int c = size - 1; c >= 0; c--){
            firstNum += co * firstNumList.get(c);
            co*=10;
        }
        co = 1;
        for (int c = size - 1; c >= 0; c--){
            secondNum += co * secondNumList.get(c);
            co*=10;
        }
        return new long[]{firstNum, secondNum};
    }

    public Numb plus(@NotNull Numb anotherNumb){
        long[] aligmentNumbers = aligment(anotherNumb.whole, anotherNumb.fractional);
        long firstNum = aligmentNumbers[0];
        long secondNum = aligmentNumbers[1];
        int maxFract = Math.max(fractional.length(), anotherNumb.fractional.length());

        if (firstNum > secondNum && anotherNumb.sign && !sign) return new Numb(minus(firstNum, secondNum, maxFract));
        if (firstNum < secondNum && sign && !anotherNumb.sign) return new Numb(minus(secondNum, firstNum, maxFract));
        if (firstNum < secondNum && anotherNumb.sign && !sign) return new Numb("-" + minus(secondNum, firstNum, maxFract));
        if (firstNum > secondNum && sign && !anotherNumb.sign) return new Numb("-" + minus(firstNum, secondNum, maxFract));
        if(sign && anotherNumb.sign) return new Numb("-" + plus(firstNum, secondNum, maxFract));
        if (!sign && !anotherNumb.sign)  return new Numb(plus(firstNum, secondNum, maxFract));
        else return new Numb("0");
    }

    private String plus(long first, long second, int maxFract) {
        String toReturn = String.valueOf(first + second);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < toReturn.length(); i++){
            if (i == toReturn.length() - maxFract) sb.append(".");
            sb.append(toReturn.charAt(i));
        }
        return sb.toString();
    }

    public Numb minus(@NotNull Numb anotherNumb){

        long[] aligmentNumbers = aligment(anotherNumb.whole, anotherNumb.fractional);
        long firstNum = aligmentNumbers[0];
        long secondNum = aligmentNumbers[1];
        int maxFract = Math.max(fractional.length(), anotherNumb.fractional.length());

        if (firstNum < secondNum && !sign && !anotherNumb.sign || firstNum < secondNum && sign && anotherNumb.sign)
            return new Numb("-" + minus(secondNum, firstNum, maxFract));
        else if (firstNum > secondNum && !sign && !anotherNumb.sign) return new Numb(minus(firstNum, secondNum, maxFract));
        else if (firstNum > secondNum && sign && anotherNumb.sign) return new Numb("-" + minus(firstNum, secondNum, maxFract));
        else if(sign && anotherNumb.sign) return new Numb("-" + plus(firstNum, secondNum, maxFract));
        else if (!sign && anotherNumb.sign) return new Numb(plus(firstNum, secondNum, maxFract));
        else return new Numb("0");
    }

    private String minus(long first, long second, int maxFract){
        String toReturn = String.valueOf(first - second);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < toReturn.length(); i++){
            if (i == toReturn.length() - maxFract) sb.append(".");
            sb.append(toReturn.charAt(i));
        }

        if (sb.indexOf(".") == 0) return 0 + sb.toString();
        else return sb.toString();
    }

    public Numb multiplication(Numb anotherNumb){

        ArrayList<Long> firstNum = new ArrayList<>(), secondNumber = new ArrayList<>();

        for (int i = 0; i < whole.length(); i++) firstNum.add(Long.parseLong(String.valueOf(whole.charAt(i))));
        for (int i = 0; i < anotherNumb.whole.length(); i++) secondNumber.add(Long.parseLong(String.valueOf(anotherNumb.whole.charAt(i))));

        for (int i = 0; i < fractional.length(); i++) firstNum.add(Long.parseLong(String.valueOf(fractional.charAt(i))));
        for (int i = 0; i < anotherNumb.fractional.length(); i++) secondNumber.add(Long.parseLong(String.valueOf(anotherNumb.fractional.charAt(i))));

        int diOfFractLen = fractional.length()+anotherNumb.fractional.length();

        if (sign == anotherNumb.sign) return new Numb(multiplication(firstNum, secondNumber, diOfFractLen));
        else return new Numb("-" + multiplication(firstNum, secondNumber, diOfFractLen));
    }

    @NotNull
    private String multiplication(@NotNull ArrayList<Long> first, ArrayList<Long> second, double lenOfFracts) {
        long fn = 0, sn = 0;
        long count = 1;

        for (int c = first.size() - 1; c >= 0; c--) {
            fn += count * first.get(c);
            count *= 10;
        }
        count = 1;
        for (int c = second.size() - 1; c >= 0; c--) {
            sn += count * second.get(c);
            count *= 10;
        }

        String res = String.valueOf(fn * sn);
        StringBuilder sb = new StringBuilder();

        int idx = 0;
        while(idx != res.length()){
            if (idx == res.length() - lenOfFracts){
                sb.append('.');
            }
            sb.append(res.charAt(idx));
            idx++;
        }
        return sb.toString();
    }

    public Numb division(Numb anotherNumb){
        ArrayList<Integer> firstNum = new ArrayList<>(), secondNum = new ArrayList<>();

        for (int i = 0; i < whole.length(); i++) firstNum.add(Integer.parseInt(String.valueOf(whole.charAt(i))));
        for (int i = 0; i < anotherNumb.whole.length(); i++) secondNum.add(Integer.parseInt(String.valueOf(anotherNumb.whole.charAt(i))));

        for (int i = 0; i < fractional.length(); i++) firstNum.add(Integer.parseInt(String.valueOf(fractional.charAt(i))));
        for (int i = 0; i < anotherNumb.fractional.length(); i++) secondNum.add(Integer.parseInt(String.valueOf(anotherNumb.fractional.charAt(i))));

        int difOfFractLen = Math.abs(fractional.length()-anotherNumb.fractional.length());

        if (fractional.length() < anotherNumb.fractional.length()) {
            while (difOfFractLen != 0){
                firstNum.add(0);
                difOfFractLen--;
            }
        }
        else {
            while (difOfFractLen != 0){
                secondNum.add(0);
                difOfFractLen--;
            }
        }

        long fn = 0, sn = 0;
        long count = 1;
        for (int c = firstNum.size() - 1; c >= 0; c--) {
            fn += count * firstNum.get(c);
            count *= 10;
        }

        count = 1;
        for (int c = secondNum.size() - 1; c >= 0; c--) {
            sn += count * secondNum.get(c);
            count *= 10;
        }

        if (fn == 0) return new Numb("0.0");
        if (sn == 0) throw new ArithmeticException("Нельзя делить на ноль");
        String result;
        if (sign == anotherNumb.sign)  result = division(fn, sn);
        else result = "-" + division(fn, sn);
        if (Long.parseLong(whole) == 0 && Long.parseLong(anotherNumb.whole)>0) result="0.0";
        return (new Numb(result)).roundingMath();
    }

    @NotNull
    private String division(long fn, long sn) {
        int stop = 8;
        int idx = 0;

        while (fn%sn!=0 && idx<stop){
            idx++;
            fn*=10;
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        String toR = String.valueOf(fn/sn);
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
        return sb.toString();
    }
}