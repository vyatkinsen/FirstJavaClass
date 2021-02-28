import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NumbTest {

    @Test
    void getValue() {
        Numb t1 = new Numb("12124.00044", 2);
        assertEquals("12124.0", t1.getValue());
        Numb t2 = new Numb(-33.3466f, 2);
        assertEquals("-33.35", t2.getValue());
        Numb t3 = new Numb(0.0944, 4);
        assertEquals("0.0944", t3.getValue());
        Numb t4 = new Numb(-234.31669, 0);
        assertEquals("-234.0", t4.getValue());
        Numb t5 = new Numb(3240432);
        assertEquals("3240432.0", t5.getValue());
        Numb t6 = new Numb("-2233.3466", 2);
        assertEquals("-2233.35", t6.getValue());
        Numb t7 = new Numb(2342.00014, 2);
        assertEquals("2342.0", t7.getValue());
        Numb t8 = new Numb(-53452);
        assertEquals("-53452.0", t8.getValue());
    }

        @Test
    void roundingToZero() {
            Numb t1 = new Numb("12124.00044", 5);
            assertEquals(12124.0004, t1.roundingToZero(4));
            Numb t2 = new Numb("-33.4966", 4);
            assertEquals(-33.5, t2.roundingToZero(2));
            Numb t3 = new Numb("0.0944", 3);
            assertEquals(0.09, t3.roundingToZero(2));
            Numb t4 = new Numb("-23434.91669", 3);
            assertEquals(-23434.92, t4.roundingToZero(2));
            Numb t5 = new Numb("30432.00999", 0);
            assertEquals(30432.0, t5.roundingMath(2));
            Numb t6 = new Numb("-2233.6466", 4);
            assertEquals(-2233.7, t6.roundingToZero(1));
            Numb t7 = new Numb(2342.00014, 2);
            assertEquals(2342.0, t7.roundingMath(1));
            Numb t8 = new Numb("-53452.0124", 3);
            assertEquals(-53452.012, t8.roundingToZero(3));
        }

    @Test
    void roundingMath() {
        Numb t1 = new Numb("12124.00044", 5);
        assertEquals(12124.0004, t1.roundingMath(4));
        Numb t2 = new Numb("-33.4966", 4);
        assertEquals(-33.5, t2.roundingMath(2));
        Numb t3 = new Numb("0.0944", 3);
        assertEquals(0.09, t3.roundingMath(2));
        Numb t4 = new Numb("-23434.91669", 3);
        assertEquals(-23434.92, t4.roundingMath(2));
        Numb t5 = new Numb("30432.00999", 0);
        assertEquals(30432.0, t5.roundingMath(2));
        Numb t6 = new Numb("-2233.6466", 4);
        assertEquals(-2233.6, t6.roundingMath(1));
        Numb t7 = new Numb(2342.00014, 2);
        assertEquals(2342.0, t7.roundingMath(1));
        Numb t8 = new Numb("-53452.0124", 3);
        assertEquals(-53452.012, t8.roundingMath(3));
    }

    @Test
    void toInt() {
        Numb t1 = new Numb("122.60312", 3);
        assertEquals(122, t1.toInt());
        Numb t3 = new Numb("-1345.528", 1);
        assertEquals(-1345, t3.toInt());
        Numb t4 = new Numb(-2359.04005, 2);
        assertEquals(-2359, t4.toInt());
        Numb t5 = new Numb(3453);
        assertEquals(3453, t5.toInt());
    }

    @Test
    void toLong() {
        Numb t1 = new Numb(122.60312, 2);
        assertEquals(122, t1.toLong());
        Numb t2 = new Numb("1234358960111", 0);
        assertEquals(1234358960111L, t2.toLong());
        Numb t3 = new Numb(1347655.5280000000f, 1);
        assertEquals(1347655, t3.toLong());
        Numb t4 = new Numb("2643490807859.04005", 3);
        assertEquals(2643490807859L, t4.toLong());
        Numb t5 = new Numb(3453);
        assertEquals(3453, t5.toLong());
    }

    @Test
    void toDouble() {
        Numb t1 = new Numb(12124.00044, 1);
        assertEquals(12124.0, t1.toDouble());
        Numb t2 = new Numb(-33);
        assertEquals(-33.0, t2.toDouble());
        Numb t3 = new Numb("0.0944", 2);
        assertEquals(0.09, t3.toDouble());
        Numb t4 = new Numb("234.316", 1);
        assertEquals(234.3, t4.toDouble());
        Numb t5 = new Numb(-3240432);
        assertEquals(-3240432.0, t5.toDouble());
        Numb t6 = new Numb("-2233.3466", 4);
        assertEquals(-2233.3466, t6.toDouble());

    }

    @Test
    void toFloat() {
        Numb t1 = new Numb("9871.000004", 0);
        assertEquals(9871.0f, t1.toFloat());
        Numb t2 = new Numb("0.543566", 3);
        assertEquals(0.544f, t2.toFloat());
        Numb t3 = new Numb(137);
        assertEquals(137.0f, t3.toFloat());
        Numb t4 = new Numb(203.6236, 4);
        assertEquals(203.6236f, t4.toFloat());
        Numb t5 = new Numb(1.1344, 4);
        assertEquals(1.1344f, t5.toFloat());
        Numb t6 = new Numb("-0.99999", 5);
        assertEquals(-0.99999f, t6.toFloat());
    }

    @Test
    void plus() {
        Numb t1 = new Numb("9871.000004", 6);
        Numb t2 = new Numb("0.543566", 5);
        assertEquals("9871.543574", t1.plus(t2).getValue());
        Numb t3 = new Numb(137);
        Numb t4 = new Numb("203.6236", 2);
        assertEquals("340.62", t3.plus(t4).getValue());
        Numb t5 = new Numb(1.1344, 2);
        Numb t6 = new Numb("-0.99999", 3);
        assertEquals("0.13", t5.plus(t6).getValue());
        Numb t7 = new Numb("94.34923", 2);
        Numb t8 = new Numb(-303.4763, 3);
        assertEquals("-209.126", t7.plus(t8).getValue());
        Numb t9 = new Numb("-122.00044", 0);
        Numb t10 = new Numb(13.3466f, 2);
        assertEquals("-108.65", t9.plus(t10).getValue());
        Numb t11 = new Numb("-0.008010", 3);
        Numb t12 = new Numb("13.3466", 3);
        assertEquals("13.339", t11.plus(t12).getValue());
        Numb t13 = new Numb("-167522.900044", 1);
        Numb t14 = new Numb("-544488.3466", 2);
        assertEquals("-712011.25", t13.plus(t14).getValue());
        Numb t15 = new Numb("-0.100687", 3);
        Numb t16 = new Numb(-544488.3466, 2);
        assertEquals("-544488.451", t15.plus(t16).getValue());
        Numb t17 = new Numb(1.112, 3);
        Numb t18 = new Numb(1.112f, 1);
        assertEquals("2.212", t17.plus(t18).getValue());
        Numb t19 = new Numb(-2831478953952L);
        Numb t20 = new Numb(-37134.022312, 2);
        assertEquals("-2.83147899108602E12", t19.plus(t20).getValue());
    }

    @Test
    void minus() {
        Numb t1 = new Numb("12124.00044", 3);
        Numb t2 = new Numb(33.3466, 2);
        assertEquals("12090.65", t1.minus(t2).getValue());
        Numb t3 = new Numb(0.0944, 2);
        Numb t4 = new Numb("234.31669", 4);
        assertEquals("-234.2267", t3.minus(t4).getValue());
        Numb t5 = new Numb(3240432);
        Numb t6 = new Numb(-2233.3466,4);
        assertEquals("3242665.3466", t5.minus(t6).getValue());
        Numb t7 = new Numb(2342.00014,2);
        Numb t8 = new Numb(-53452);
        assertEquals("55794.0", t7.minus(t8).getValue());
        Numb t9 = new Numb(-1324.059, 3);
        Numb t10 = new Numb(-0.0016,2);
        assertEquals("-1324.059", t9.minus(t10).getValue());
        Numb t11 = new Numb(-841.0035, 4);
        Numb t12 = new Numb(-25913.0002, 4);
        assertEquals("25071.9967", t11.minus(t12).getValue());
        Numb t13 = new Numb(00099432.1000,0);
        Numb t14 = new Numb(3);
        assertEquals("99429.0", t13.minus(t14).getValue());
        Numb t15 = new Numb(-8800.555, 3);
        Numb t16 = new Numb(-3535.099, 3);
        assertEquals("-5265.456", t15.minus(t16).getValue());
        Numb t17 = new Numb(456.6, 0);
        Numb t18 = new Numb(8.386,3);
        assertEquals("448.614", t17.minus(t18).getValue());
        Numb t19 = new Numb(0.9952, 4);
        Numb t20 = new Numb(-124.45335, 4);
        assertEquals("125.4486", t19.minus(t20).getValue());
        Numb t21 = new Numb(0);
        Numb t22 = new Numb("0.0", 0);
        assertEquals("0.0", t21.minus(t22).getValue());
    }
//
    @Test
    void multiplication(){
        Numb t1 = new Numb("12124.00044", 3);
        Numb t2 = new Numb(33.3466, 2);
        assertEquals("404335.4", t1.multiplication(t2).getValue());
        Numb t3 = new Numb(0.0944, 2);
        Numb t4 = new Numb("234.31669", 4);
        assertEquals("21.0885", t3.multiplication(t4).getValue());
        Numb t5 = new Numb(3240432);
        Numb t6 = new Numb(-2233.3466,4);
        assertEquals("-7.2370077897312E9", t5.multiplication(t6).getValue());
        Numb t7 = new Numb(2342.00014,2);
        Numb t8 = new Numb(-53452);
        assertEquals("-1.25184584E8", t7.multiplication(t8).getValue());
        Numb t9 = new Numb(-1324.059, 3);
        Numb t10 = new Numb(-0.0016,2);
        assertEquals("0.0", t9.multiplication(t10).getValue());
        Numb t11 = new Numb(-841.0035, 4);
        Numb t12 = new Numb(-25913.0002, 4);
        assertEquals("2.17929238637E7", t11.multiplication(t12).getValue());
        Numb t13 = new Numb(00099432.1000,0);
        Numb t14 = new Numb(3);
        assertEquals("298296.0", t13.multiplication(t14).getValue());
        Numb t15 = new Numb(-8800.555, 3);
        Numb t16 = new Numb(-3535.099, 3);
        assertEquals("3.111083318E7", t15.multiplication(t16).getValue());
        Numb t17 = new Numb(456.6, 0);
        Numb t18 = new Numb(8.386,3);
        assertEquals("3832.402", t17.multiplication(t18).getValue());
        Numb t19 = new Numb(0.9952, 4);
        Numb t20 = new Numb(-124.45335, 4);
        assertEquals("-123.856", t19.multiplication(t20).getValue());
        Numb t21 = new Numb(0);
        Numb t22 = new Numb("0.0", 0);
        assertEquals("0.0", t21.multiplication(t22).getValue());
    }

    @Test
    void division(){
        Numb t1 = new Numb("12124.00044", 3);
        Numb t2 = new Numb(33.3466, 2);
        assertEquals("363.54", t1.division(t2).getValue());
        Numb t3 = new Numb(0.0944, 2);
        Numb t4 = new Numb("234.31669", 4);
        assertEquals("0.0", t3.division(t4).getValue());
        Numb t5 = new Numb(3240432);
        Numb t6 = new Numb(-2233.3466,4);
        assertEquals("-1451.0", t5.division(t6).getValue());
        Numb t7 = new Numb(2342.00014,2);
        Numb t8 = new Numb(53452);
        assertEquals("0.0", t7.division(t8).getValue());
        Numb t9 = new Numb(-1324.059, 3);
        Numb t10 = new Numb(-0.0016,2);
        assertThrows(ArithmeticException.class, () -> t9.division(t10).getValue());
        Numb t11 = new Numb(-841.0035, 4);
        Numb t12 = new Numb(-25913.0002, 4);
        assertEquals("0.0325", t11.division(t12).getValue());
        Numb t13 = new Numb(00099432.1000,0);
        Numb t14 = new Numb(3);
        assertEquals("33144.0", t13.division(t14).getValue());
        Numb t15 = new Numb(-8800.555, 3);
        Numb t16 = new Numb(-3535.099, 3);
        assertEquals("2.489", t15.division(t16).getValue());
        Numb t17 = new Numb(456.6, 0);
        Numb t18 = new Numb(8.386,3);
        assertEquals("54.0", t17.division(t18).getValue());
        Numb t19 = new Numb(0.9952, 4);
        Numb t20 = new Numb(124.45335, 4);
        assertEquals("0.008", t19.division(t20).getValue());
        Numb t21 = new Numb("2536972.0643546", 2);
        Numb t22 = new Numb(0);
        assertThrows(ArithmeticException.class, () -> t21.division(t22));
    }
}