import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NumbTest {

    @Test
    void getValue() {
        Numb t1 = new Numb("12124.00044", 4);
        assertEquals("12120", t1.getValue());
        Numb t2 = new Numb(-33.3466f, 2);
        assertEquals("-33", t2.getValue());
        Numb t3 = new Numb(0.0944, 4);
        assertEquals("0.094", t3.getValue());
        Numb t4 = new Numb(-234.31669, 2);
        assertEquals("-230", t4.getValue());
        Numb t5 = new Numb(3240432, 4);
        assertEquals("3240000", t5.getValue());
        Numb t6 = new Numb("-2233.3466", 2);
        assertEquals("-2200", t6.getValue());
        Numb t7 = new Numb(2342.00014, 2);
        assertEquals("2300", t7.getValue());
        Numb t8 = new Numb(53452, 2);
        assertEquals("53000", t8.getValue());
    }

    @Test
    void roundingToZero() {
            Numb t1 = new Numb("12124.00044", 10);
            assertEquals("12124.0004", t1.roundingToZero(4).getValue());
            Numb t2 = new Numb(-33.4966f, 6);
            assertEquals("-33.49", t2.roundingToZero(2).getValue());
            Numb t3 = new Numb(0.0944, 3);
            assertEquals("0.0", t3.roundingToZero(1).getValue());
            Numb t4 = new Numb("-23434.91669", 3);
            assertEquals("-23400", t4.roundingToZero(2).getValue());
            Numb t5 = new Numb("30432.00999", 10);
            assertEquals("30432.009", t5.roundingToZero(3).getValue());
            Numb t6 = new Numb(-2233.6466, 4);
            assertEquals("-2234", t6.roundingToZero(1).getValue());
            Numb t7 = new Numb(2345.04536, 2);
            assertEquals("2300", t7.roundingToZero(5).getValue());
            Numb t8 = new Numb(2342.5, 5);
            assertEquals("2342.0", t8.roundingToZero(0).getValue());
        }

    @Test
    void roundingMath() {
        Numb t1 = new Numb("12124.12345", 10);
        assertEquals("12124.1235", t1.roundingMath(4).getValue());
        Numb t2 = new Numb(-33.4966f, 6);
        assertEquals("-33.49", t2.roundingMath(2).getValue());
        Numb t3 = new Numb(0.0944, 3);
        assertEquals("0.1", t3.roundingMath(1).getValue());
        Numb t4 = new Numb("-23434.91669", 3);
        assertEquals("-23400", t4.roundingMath(2).getValue());
        Numb t5 = new Numb("30432.00999", 10);
        assertEquals("30432.010", t5.roundingMath(3).getValue());
        Numb t6 = new Numb(-2233.6466, 4);
        assertEquals("-2234", t6.roundingMath(1).getValue());
        Numb t7 = new Numb(2345.04536, 2);
        assertEquals("2300", t7.roundingMath(5).getValue());
        Numb t8 = new Numb(2342.5, 5);
        assertEquals("2343.0", t8.roundingMath(0).getValue());
    }

    @Test
    void toInt() {
        Numb t1 = new Numb("122.60312", 3);
        assertEquals(123, t1.toInt());
        Numb t3 = new Numb("-1345.528", 1);
        assertEquals(-1000, t3.toInt());
        Numb t4 = new Numb(-2359.04005, 2);
        assertEquals(-2400, t4.toInt());
        Numb t5 = new Numb(3453, 2);
        assertEquals(3500, t5.toInt());
    }

    @Test
    void toLong() {
        Numb t1 = new Numb(122.60312, 2);
        assertEquals(120, t1.toLong());
        Numb t2 = new Numb("1234358960111", 4);
        assertEquals(1234000000000L, t2.toLong());
        Numb t3 = new Numb(1347655.5280000000f, 10);
        assertEquals(1347656L, t3.toLong());
        Numb t4 = new Numb("2643490807859.04005", 3);
        assertEquals(2640000000000L, t4.toLong());
        Numb t5 = new Numb(3453, 2);
        assertEquals(3500L, t5.toLong());
    }

    @Test
    void toDouble() {
        Numb t1 = new Numb(12124.00044, 6);
        assertEquals(12124.0, t1.toDouble());
        Numb t2 = new Numb(-33, 2);
        assertEquals(-33, t2.toDouble());
        Numb t3 = new Numb("0.0944", 6);
        assertEquals(0.0944, t3.toDouble());
        Numb t4 = new Numb("234.316", 2);
        assertEquals(230.0, t4.toDouble());
        Numb t5 = new Numb(-3240432, 10);
        assertEquals(-3240432.0, t5.toDouble());
        Numb t6 = new Numb("-2233.3466", 5);
        assertEquals(-2233.3, t6.toDouble());

    }

    @Test
    void toFloat() {
        Numb t1 = new Numb("9871.000004", 2);
        assertEquals(9900.0f, t1.toFloat());
        Numb t2 = new Numb("0.543566", 3);
        assertEquals(0.54f, t2.toFloat());
        Numb t3 = new Numb(137, 4);
        assertEquals(137.0f, t3.toFloat());
        Numb t4 = new Numb(203.6236, 4);
        assertEquals(203.6f, t4.toFloat());
        Numb t5 = new Numb(1.1344, 4);
        assertEquals(1.134f, t5.toFloat());
        Numb t6 = new Numb("-0.99999", 5);
        assertEquals(-1.0f, t6.toFloat());
    }

    @Test
    void plus() {
        Numb t1 = new Numb("9871.000004", 10);
        Numb t2 = new Numb("0.543566", 5);
        assertEquals("9871.5", t1.plus(t2).getValue());
        Numb t3 = new Numb("137", 4);
        Numb t4 = new Numb("203.6236", 5);
        assertEquals("340.6", t3.plus(t4).getValue());
        Numb t5 = new Numb(1.1344, 10);
        Numb t6 = new Numb("-0.99999", 7);
        assertEquals("0.13441", t5.plus(t6).getValue());
        Numb t7 = new Numb("94.34923", 5);
        Numb t8 = new Numb(-303.4763, 6);
        assertEquals("-209.13", t7.plus(t8).getValue());
        Numb t9 = new Numb("-122.00044", 3);
        Numb t10 = new Numb(13.3466f, 6);
        assertEquals("-109", t9.plus(t10).getValue());
        Numb t11 = new Numb("-0.008010", 10);
        Numb t12 = new Numb("13.3466", 16);
        assertEquals("13.338590", t11.plus(t12).getValue());
        Numb t13 = new Numb("-167522.900044", 16);
        Numb t14 = new Numb("-544488.3466", 18);
        assertEquals("-712011.246644", t13.plus(t14).getValue());
        Numb t15 = new Numb("-0.100687", 10);
        Numb t16 = new Numb(-544488.3466, 15);
        assertEquals("-544488.4473", t15.plus(t16).getValue());
        Numb t17 = new Numb(1.112, 3);
        Numb t18 = new Numb(-1.112f, 3);
        assertEquals("0.0", t17.plus(t18).getValue());
        Numb t19 = new Numb(-28312L, 18);
        Numb t20 = new Numb(-37134.022312, 10);
        assertEquals("-65446.02231", t19.plus(t20).getValue());
    }

    @Test
    void minus() {
        Numb t1 = new Numb("9871.000004", 10);
        Numb t2 = new Numb("0.543566", 5);
        assertEquals("9870.5", t1.minus(t2).getValue());
        Numb t3 = new Numb("137", 4);
        Numb t4 = new Numb("203.6236", 5);
        assertEquals("-66.62", t3.minus(t4).getValue());
        Numb t5 = new Numb(1.1344, 10);
        Numb t6 = new Numb("-0.99999", 7);
        assertEquals("2.13439", t5.minus(t6).getValue());
        Numb t7 = new Numb("94.34923", 5);
        Numb t8 = new Numb(-303.4763, 6);
        assertEquals("397.83", t7.minus(t8).getValue());
        Numb t9 = new Numb("-122.00044", 3);
        Numb t10 = new Numb(13.3466f, 6);
        assertEquals("-135", t9.minus(t10).getValue());
        Numb t11 = new Numb("-0.008010", 10);
        Numb t12 = new Numb("13.3466", 16);
        assertEquals("-13.354610", t11.minus(t12).getValue());
        Numb t13 = new Numb("-167522.900044", 16);
        Numb t14 = new Numb("-544488.3466", 18);
        assertEquals("376965.446556", t13.minus(t14).getValue());
        Numb t15 = new Numb("-0.100687", 10);
        Numb t16 = new Numb(-544488.3466, 15);
        assertEquals("544488.2459", t15.minus(t16).getValue());
        Numb t17 = new Numb(1.112, 3);
        Numb t18 = new Numb(1.112f, 3);
        assertEquals("0.0", t17.minus(t18).getValue());
        Numb t19 = new Numb(-28312L, 18);
        Numb t20 = new Numb(-37134.022312, 10);
        assertEquals("8822.02231", t19.minus(t20).getValue());
        Numb t21 = new Numb(0, 1);
        Numb t22 = new Numb("0.0", 2);
        assertEquals("0", t21.minus(t22).getValue());
        Numb t23 = new Numb(16.3, 3);
        Numb t24 = new Numb("10.3", 3);
        assertEquals("6.0", t23.minus(t24).getValue());
    }

    @Test
    void multiplication(){
        Numb t1 = new Numb("12124.00044", 10);
        Numb t2 = new Numb(33.3466, 9);
        assertEquals("404294.193", t1.multiplication(t2).getValue());
        Numb t3 = new Numb(0.0944, 5);
        Numb t4 = new Numb("234.31669", 4);
        assertEquals("22.12", t3.multiplication(t4).getValue());
        Numb t5 = new Numb(3240432, 9);
        Numb t6 = new Numb(-2233.3466,10);
        assertEquals("-7237007790", t5.multiplication(t6).getValue());
        Numb t7 = new Numb(2342.00014,10);
        Numb t8 = new Numb(-53452, 7);
        assertEquals("-125184600", t7.multiplication(t8).getValue());
        Numb t9 = new Numb(-1324.059, 3);
        Numb t10 = new Numb(-0.0016,2);
        assertEquals("0", t9.multiplication(t10).getValue());
        Numb t11 = new Numb(-841.0035, 4);
        Numb t12 = new Numb(-25913.0002, 4);
        assertEquals("21790000", t11.multiplication(t12).getValue());
        Numb t13 = new Numb(99432.1000,14);
        Numb t14 = new Numb(3, 8);
        assertEquals("298296.30", t13.multiplication(t14).getValue());
        Numb t15 = new Numb(-8800.555, 7);
        Numb t16 = new Numb(-3535.099, 7);
        assertEquals("31110830", t15.multiplication(t16).getValue());
        Numb t17 = new Numb(456.6, 9);
        Numb t18 = new Numb(8.386,9);
        assertEquals("3829.0476", t17.multiplication(t18).getValue());
        Numb t19 = new Numb(0.9952, 10);
        Numb t20 = new Numb(-124.45335, 5);
        assertEquals("-123.85", t19.multiplication(t20).getValue());
        Numb t21 = new Numb(0, 7);
        Numb t22 = new Numb("0.0", 1);
        assertEquals("0", t21.multiplication(t22).getValue());
    }

    @Test
    void division(){
        Numb t1 = new Numb("12124.00044", 10);
        Numb t2 = new Numb(33.3466, 9);
        assertEquals("363.575310", t1.division(t2).getValue());
        Numb t3 = new Numb(0.0944, 5);
        Numb t4 = new Numb(234.31669, 5);
        assertEquals("0.0004", t3.division(t4).getValue());
        Numb t5 = new Numb(32404, 9);
        Numb t6 = new Numb(-2233.3466,10);
        assertEquals("-14.5091675", t5.division(t6).getValue());
        Numb t7 = new Numb(234542.00014,10);
        Numb t8 = new Numb(-53452, 7);
        assertEquals("-4.387899", t7.division(t8).getValue());
        Numb t9 = new Numb(-1324.059, 3);
        Numb t10 = new Numb(0,2);
        assertThrows(ArithmeticException.class, () -> t9.division(t10));
        Numb t11 = new Numb(-841.0035, 4);
        Numb t12 = new Numb(-25913.0002, 4);
        assertEquals("0.032", t11.division(t12).getValue());
        Numb t13 = new Numb(99432.1000,14);
        Numb t14 = new Numb(3, 8);
        assertEquals("33144.033", t13.division(t14).getValue());
        Numb t15 = new Numb(-8800.555, 7);
        Numb t16 = new Numb(-3535.099, 7);
        assertEquals("2.489479", t15.division(t16).getValue());
        Numb t17 = new Numb(456.6, 9);
        Numb t18 = new Numb(8.386,9);
        assertEquals("54.4478893", t17.division(t18).getValue());
        Numb t19 = new Numb(124.9952, 10);
        Numb t20 = new Numb(-124.45335, 5);
        assertEquals("-1.0044", t19.division(t20).getValue());
        Numb t21 = new Numb("2536972.0643546", 2);
        Numb t22 = new Numb(0, 1);
        assertThrows(ArithmeticException.class, () -> t21.division(t22));
    }
}