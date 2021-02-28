import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NumbTest {

    @Test
    void before() {
        Numb t1 = new Numb("122.3444");
        assertEquals(3, t1.before());
        Numb t2 = new Numb("2147489.01");
        assertEquals(7, t2.before());
        Numb t3 = new Numb("0.0");
        assertEquals(1, t3.before());
        Numb t4 = new Numb("5420.026004");
        assertEquals(4, t4.before());
        Numb t5 = new Numb("1337228.0876876973582");
        assertEquals(7, t5.before());
        Numb t6 = new Numb("1337228293845204395");
        assertEquals(19, t6.before());
    }

    @Test
    void after() {
        Numb t1 = new Numb("122.3444");
        assertEquals(4, t1.after());
        Numb t2 = new Numb("2147489.00000");
        assertEquals(0, t2.after());
        Numb t3 = new Numb("0.0");
        assertEquals(1, t3.after());
        Numb t4 = new Numb("5420.026004");
        assertEquals(6, t4.after());
        Numb t5 = new Numb("1337228.0876876973582");
        assertEquals(13, t5.after());
    }

    @Test
    void numberToString() {
        Numb t1 = new Numb("12124.00044");
        assertEquals("12124.00044", t1.numberToString());
        Numb t2 = new Numb("-33.3466");
        assertEquals("-33.3466", t2.numberToString());
        Numb t3 = new Numb("0.0944");
        assertEquals("0.0944", t3.numberToString());
        Numb t4 = new Numb("-234.31669");
        assertEquals("-234.31669", t4.numberToString());
        Numb t5 = new Numb("3240432.0");
        assertEquals("3240432.0", t5.numberToString());
        Numb t6 = new Numb("-2233.3466");
        assertEquals("-2233.3466", t6.numberToString());
        Numb t7 = new Numb("2342.00014");
        assertEquals("2342.00014", t7.numberToString());
        Numb t8 = new Numb("-53452.0");
        assertEquals("-53452.0", t8.numberToString());
    }

    @Test
    void stringToNumber() {

        Numb t1 = new Numb("12124.00044");
        assertEquals(12124.00044, t1.stringToNumber());
        Numb t2 = new Numb("-33.3466");
        assertEquals(-33.3466, t2.stringToNumber());
        Numb t3 = new Numb("0.0944");
        assertEquals(0.0944, t3.stringToNumber());
        Numb t4 = new Numb("-234.31669");
        assertEquals(-234.31669, t4.stringToNumber());
        Numb t5 = new Numb("3240432.0");
        assertEquals(3240432.0, t5.stringToNumber());
        Numb t6 = new Numb("-2233.3466");
        assertEquals(-2233.3466, t6.stringToNumber());
        Numb t7 = new Numb("2342.00014");
        assertEquals(2342.00014, t7.stringToNumber());
        Numb t8 = new Numb("-53452.0");
        assertEquals(-53452.0, t8.stringToNumber());
    }

    @Test
    void roundingToZero() {
        Numb t1 = new Numb("12124.00044");
        assertEquals("12124.0", t1.roundingToZero().getValue());
        Numb t2 = new Numb("-33.4966");
        assertEquals("-33.0", t2.roundingToZero().getValue());
        Numb t3 = new Numb("0.0944");
        assertEquals("0.0", t3.roundingToZero().getValue());
        Numb t4 = new Numb("-234324.969");
        assertEquals("-234324.0", t4.roundingToZero().getValue());
        Numb t5 = new Numb("3240432.0999");
        assertEquals("3240432.0", t5.roundingToZero().getValue());
        Numb t6 = new Numb("-2233.6466");
        assertEquals("-2233.0", t6.roundingToZero().getValue());
        Numb t7 = new Numb("2342.00014");
        assertEquals("2342.0", t7.roundingToZero().getValue());
        Numb t8 = new Numb("-53452.0124");
        assertEquals("-53452.0", t8.roundingToZero().getValue());
    }

    @Test
    void roundingMath() {
        Numb t1 = new Numb("12124.00044");
        assertEquals("12124.0", t1.roundingMath().getValue());
        Numb t2 = new Numb("-33.4966");
        assertEquals("-33.0", t2.roundingMath().getValue());
        Numb t3 = new Numb("0.0944");
        assertEquals("0.0", t3.roundingMath().getValue());
        Numb t4 = new Numb("-23434.91669");
        assertEquals("-23435.0", t4.roundingMath().getValue());
        Numb t5 = new Numb("30432.00999");
        assertEquals("30432.0", t5.roundingMath().getValue());
        Numb t6 = new Numb("-2233.6466");
        assertEquals("-2234.0", t6.roundingMath().getValue());
        Numb t7 = new Numb("2342.00014");
        assertEquals("2342.0", t7.roundingMath().getValue());
        Numb t8 = new Numb("-53452.0124");
        assertEquals("-53452.0", t8.roundingMath().getValue());
    }

    @Test
    void toInt() {
        Numb t1 = new Numb("122.60312");
        assertEquals(122, t1.toInt());
        Numb t2 = new Numb ("-1234358960111");
        assertThrows(NumberFormatException.class, t2::toInt);
        Numb t3 = new Numb("-1345.528");
        assertEquals(-1345, t3.toInt());
        Numb t4 = new Numb("-2359.04005");
        assertEquals(-2359, t4.toInt());
        Numb t5 = new Numb("3453");
        assertEquals(3453, t5.toInt());
    }

    @Test
    void toLong() {
        Numb t1 = new Numb("122.60312");
        assertEquals(122, t1.toLong());
        Numb t2 = new Numb ("1234358960111");
        assertEquals(1234358960111L, t2.toLong());
        Numb t3 = new Numb("1347655.5280000000f");
        assertEquals(1347655, t3.toLong());
        Numb t4 = new Numb("2643490807859.04005");
        assertEquals(2643490807859L, t4.toLong());
        Numb t5 = new Numb("3453.0");
        assertEquals(3453, t5.toLong());
    }

    @Test
    void toDouble() {
        Numb t1 = new Numb("12124.00044");
        assertEquals(12124.00044, t1.toDouble());
        Numb t2 = new Numb("-33");
        assertEquals(-33.0, t2.toDouble());
        Numb t3 = new Numb("0.0944");
        assertEquals(0.0944, t3.toDouble());
        Numb t4 = new Numb("234.316");
        assertEquals(234.316, t4.toDouble());
        Numb t5 = new Numb("-3240432");
        assertEquals(-3240432.0, t5.toDouble());
        Numb t6 = new Numb("-2233.3466");
        assertEquals(-2233.3466, t6.toDouble());

    }

    @Test
    void toFloat() {
        Numb t1 = new Numb("9871.000004");
        assertEquals(9871.0f, t1.toFloat());
        Numb t2 = new Numb("0.543566");
        assertEquals(0.543566f, t2.toFloat());
        Numb t3 = new Numb("137.0");
        assertEquals(137.0f, t3.toFloat());
        Numb t4 = new Numb("203.6236");
        assertEquals(203.6236f, t4.toFloat());
        Numb t5 = new Numb("1.1344");
        assertEquals(1.1344f, t5.toFloat());
        Numb t6 = new Numb("-0.99999");
        assertEquals(-0.99999f, t6.toFloat());
    }

    @Test
    void plus() {
        Numb t1 = new Numb("9871.000004");
        Numb t2 = new Numb("0.543566");
        assertEquals("9871.54357", t1.plus(t2).getValue());
        Numb t3 = new Numb("137.0");
        Numb t4 = new Numb("203.6236");
        assertEquals("340.6236", t3.plus(t4).getValue());
        Numb t5 = new Numb("1.1344");
        Numb t6 = new Numb("-0.99999");
        assertEquals("0.13441", t5.plus(t6).getValue());
        Numb t7 = new Numb("94.34923");
        Numb t8 = new Numb("-303.4763");
        assertEquals("-209.12707", t7.plus(t8).getValue());
        Numb t9 = new Numb("-122.00044");
        Numb t10 = new Numb("13.3466");
        assertEquals("-108.65384", t9.plus(t10).getValue());
        Numb t11 = new Numb("-0.008010");
        Numb t12 = new Numb("13.3466");
        assertEquals("13.33859", t11.plus(t12).getValue());
        Numb t13 = new Numb("-167522.900044");
        Numb t14 = new Numb("-544488.3466");
        assertEquals("-712011.246644", t13.plus(t14).getValue());
        Numb t15 = new Numb("-0.100687");
        Numb t16 = new Numb("-544488.3466");
        assertEquals("-544488.447287", t15.plus(t16).getValue());
        Numb t17 = new Numb("1.112");
        Numb t18 = new Numb("1.112");
        assertEquals("2.224", t17.plus(t18).getValue());
        Numb t19 = new Numb("-2831478953952");
        Numb t20 = new Numb("-37134.022312");
        assertEquals("-2831478991086.022312", t19.plus(t20).getValue());
    }

    @Test
    void minus() {
        Numb t1 = new Numb("12124.00044");
        Numb t2 = new Numb("33.3466");
        assertEquals("12090.65384", t1.minus(t2).getValue());
        Numb t3 = new Numb("0.0944");
        Numb t4 = new Numb("234.31669");
        assertEquals("-234.22229", t3.minus(t4).getValue());
        Numb t5 = new Numb("3240432.0");
        Numb t6 = new Numb("-2233.3466");
        assertEquals("3242665.3466", t5.minus(t6).getValue());
        Numb t7 = new Numb("2342.00014");
        Numb t8 = new Numb("-53452.0");
        assertEquals("55794.00014", t7.minus(t8).getValue());
        Numb t9 = new Numb("-1324.059");
        Numb t10 = new Numb("-0.0016");
        assertEquals("-1324.0574", t9.minus(t10).getValue());
        Numb t11 = new Numb("-841.0035");
        Numb t12 = new Numb("-25913.0002");
        assertEquals("-25071.9967", t11.minus(t12).getValue());
        Numb t13 = new Numb("00099432.1000");
        Numb t14 = new Numb("3.0");
        assertEquals("99429.1", t13.minus(t14).getValue());
        Numb t15 = new Numb("-8800.555");
        Numb t16 = new Numb("-3535.099");
        assertEquals("-5265.456", t15.minus(t16).getValue());
        Numb t17 = new Numb("456.6");
        Numb t18 = new Numb("8.386");
        assertEquals("448.214", t17.minus(t18).getValue());
        Numb t19 = new Numb("0.9952");
        Numb t20 = new Numb("-124.45335");
        assertEquals("125.44855", t19.minus(t20).getValue());
        Numb t21 = new Numb("0.0");
        Numb t22 = new Numb("0.0");
        assertEquals("0.0", t21.minus(t22).getValue());
    }

    @Test
    void multiplication(){
        Numb t1 = new Numb("12124.00044");
        Numb t2 = new Numb("33.3466");
        assertEquals("404294.193072504",t1.multiplication(t2).getValue());
        Numb t3 = new Numb("0.0944");
        Numb t4 = new Numb("234.316");
        assertEquals("22.1194304",t3.multiplication(t4).getValue());
        Numb t5 = new Numb("3240432.0");
        Numb t6 = new Numb("-2233.3466");
        assertEquals("-7237007789.7312",t5.multiplication(t6).getValue());
        Numb t7 = new Numb("2342.00014");
        Numb t8 = new Numb("-53452.0");
        assertEquals("-125184591.48328",t7.multiplication(t8).getValue());
        Numb t9 = new Numb("-1324.059");
        Numb t10 = new Numb("-0.0016");
        assertEquals("2.1184944",t9.multiplication(t10).getValue());
        Numb t11 = new Numb("-841439569");
        Numb t12 = new Numb("-25913.0002");
        assertEquals("21804223719784.9138",t11.multiplication(t12).getValue());
        Numb t13 = new Numb("00099432.1000");
        Numb t14 = new Numb("3.0");
        assertEquals("298296.3",t13.multiplication(t14).getValue());
        Numb t15 = new Numb("-8800.555");
        Numb t16 = new Numb("-3535.099");
        assertEquals("31110833.179945",t15.multiplication(t16).getValue());
        Numb t17 = new Numb("456.6");
        Numb t18 = new Numb("0");
        assertEquals("0.0",t17.multiplication(t18).getValue());
        Numb t19 = new Numb("0.9952");
        Numb t20 = new Numb("-124.45335");
        assertEquals("-123.85597392",t19.multiplication(t20).getValue());
        Numb t21 = new Numb("0.0");
        Numb t22 = new Numb("0.0");
        assertEquals("0.0", t21.multiplication(t22).getValue());
    }

    @Test
    void division(){
        Numb t1 = new Numb("12124.00044");
        Numb t2 = new Numb("33.3466");
        assertEquals("364.0",t1.division(t2).getValue());
        Numb t3 = new Numb("0.944");
        Numb t4 = new Numb("234.31669");
        assertEquals("0.0",t3.division(t4).getValue());
        Numb t5 = new Numb("3240432");
        Numb t6 = new Numb("-2233.3466");
        assertEquals("-1451.0",t5.division(t6).getValue());
        Numb t7 = new Numb("2342.00014");
        Numb t8 = new Numb("-53452");
        assertEquals("0.0",t7.division(t8).getValue());
        Numb t9 = new Numb("-1324.059");
        Numb t10 = new Numb("-0.0016");
        assertEquals("827537.0",t9.division(t10).getValue());
        Numb t11 = new Numb("-841.0035");
        Numb t12 = new Numb("-25913.0002");
        assertEquals("0.0",t11.division(t12).getValue());
        Numb t13 = new Numb("00099432.1000");
        Numb t14 = new Numb("3.0");
        assertEquals("33144.0",t13.division(t14).getValue());
        Numb t15 = new Numb("-8800.555");
        Numb t16 = new Numb("-3535.099");
        assertEquals("2.0",t15.division(t16).getValue());
        Numb t17 = new Numb("456.6");
        Numb t18 = new Numb("8.386");
        assertEquals("54.0",t17.division(t18).getValue());
        Numb t19 = new Numb("0.9952");
        Numb t20 = new Numb("-124.45335");
        assertEquals("0.0",t19.division(t20).getValue());
        Numb t21 = new Numb("0.0");
        Numb t22 = new Numb("0.0");
        assertEquals("0.0",t21.division(t22).getValue());
        Numb t23 = new Numb("2536972.0643546");
        Numb t24 = new Numb("0.0");
        assertThrows(ArithmeticException.class, () -> t23.division(t24));
    }
}