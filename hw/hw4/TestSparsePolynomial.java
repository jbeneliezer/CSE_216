import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestSparsePolynomial {

    /**
     * Tests the string parameterized constructor for SparsePolynomial, and uses the assertArrayEquals method to compare
     * return value of getPolynomial() to equivalent int array
     *
     * Tests edge cases and test invalid input with assertThrows
     * Also tests wellFormed
     */
    public void testConstructor(){
        SparsePolynomial p_1 = new SparsePolynomial("2x^3 - 3x^2 + 2x + 1");
        SparsePolynomial p_2 = new SparsePolynomial("0");
        SparsePolynomial p_3 = new SparsePolynomial("x");
        SparsePolynomial p_4 = new SparsePolynomial(String.format("%dx^%d", Integer.MAX_VALUE, -100));
        Map<Integer, Integer> t_1 = new HashMap<>();
        t_1.put(3, 2);
        t_1.put(2, -3);
        t_1.put(1, 2);
        t_1.put(0, 1);
        Map<Integer, Integer> t_2 = new HashMap<>();
        t_2.put(0, 0);
        Map<Integer, Integer> t_3 = new HashMap<>();
        t_3.put(1, 1);
        Map<Integer, Integer> t_4 = new HashMap<>();
        t_4.put(-100, Integer.MAX_VALUE);
        assertEquals(new SparsePolynomial(t_1), p_1);
        assertEquals(new SparsePolynomial(t_2), p_2);
        assertEquals(new SparsePolynomial(t_3), p_3);
        assertEquals(new SparsePolynomial(t_4), p_4);
        assertThrows(NumberFormatException.class, () -> {
            new SparsePolynomial("");
        });
    }

    /**
     * Tests that degree function of Dense Polynomial works, including max and min values
     */
    public void testDegree() {
        SparsePolynomial p_1 = new SparsePolynomial("2x^3 - 3x^2 + 2x + 1");
        SparsePolynomial p_2 = new SparsePolynomial("0");
        SparsePolynomial p_3 = new SparsePolynomial("x");
        SparsePolynomial p_4 = new SparsePolynomial(String.format("%dx^%d - 23x^-15", Integer.MAX_VALUE, -100));
        assertEquals(3, p_1.degree(), "should be 3");
        assertEquals(0, p_2.degree(), "should be 0");
        assertEquals(1, p_3.degree(), "should be 1");
        assertEquals(-15, p_4.degree(), "should be -15");
    }

    public void testIsZero() {
        SparsePolynomial p_1 = new SparsePolynomial("0");
        SparsePolynomial p_2 = new SparsePolynomial("x");
        assertTrue(p_1.isZero());
        assertFalse(p_2.isZero());
    }

    public void testAdd() {
        SparsePolynomial p_1 = new SparsePolynomial("4x^4 + 3x^2 - 2x + 6");
        SparsePolynomial p_2 = new SparsePolynomial("3x^3 + 7x^2 + x - 1");
        SparsePolynomial p_3 = null;
        Map<Integer, Integer> t_1 = new HashMap<>();
        t_1.put(0, 5);
        t_1.put(1, -1);
        t_1.put(2, 10);
        t_1.put(3, 3);
        t_1.put(4, 4);
        assertEquals(new SparsePolynomial(t_1), p_1.add(p_2));
        assertThrows(NullPointerException.class, () -> {
            p_1.add(p_3);
        });
    }
    public void testMultiply() {
        SparsePolynomial p_1 = new SparsePolynomial("4x^4 + 3x^2 - 2x + 6");
        SparsePolynomial p_2 = new SparsePolynomial("3x^3 + 7x^2 + x - 1");
        SparsePolynomial p_3 = null;
        Map<Integer, Integer> t_1 = new HashMap<>();
        t_1.put(0, -6);
        t_1.put(1, 8);
        t_1.put(2, 37);
        t_1.put(3, 7);
        t_1.put(4, 11);
        t_1.put(5, 13);
        t_1.put(6, 28);
        t_1.put(7, 12);
        assertEquals(new SparsePolynomial(t_1), p_1.multiply(p_2));
        assertThrows(NullPointerException.class, () -> {
            p_1.multiply(p_3);
        });
    }
    public void testSubtract() {
        SparsePolynomial p_1 = new SparsePolynomial("4x^4 + 3x^2 - 2x + 6");
        SparsePolynomial p_2 = new SparsePolynomial("3x^3 + 7x^2 + x - 1");
        SparsePolynomial p_3 = null;
        Map<Integer, Integer> t_1 = new HashMap<>();
        t_1.put(0, 7);
        t_1.put(1, -3);
        t_1.put(2, -4);
        t_1.put(3, -3);
        t_1.put(4, 4);
        assertEquals(new SparsePolynomial(t_1), p_1.subtract(p_2));
        assertThrows(NullPointerException.class, () -> {
            p_1.subtract(p_3);
        });
    }
    public void testMinus() {
        SparsePolynomial p_1 = new SparsePolynomial("4x^4 + 3x^2 - 2x + 6");
        SparsePolynomial p_2 = null;
        Map<Integer, Integer> t_1 = new HashMap<>();
        t_1.put(0, -6);
        t_1.put(1, 2);
        t_1.put(2, -3);
        t_1.put(4, -4);
        assertEquals(new SparsePolynomial(t_1), p_1.minus());
        assertThrows(NullPointerException.class, () -> {
            p_2.minus();
        });
    }

    public void testToString() {
        SparsePolynomial p_1 = new SparsePolynomial("2x^3 - 3x^2 + 2x + 1");
        SparsePolynomial p_2 = new SparsePolynomial("0");
        SparsePolynomial p_3 = new SparsePolynomial("x");
        SparsePolynomial p_4 = new SparsePolynomial(String.format("%dx^%d", Integer.MAX_VALUE, -100));
        String t_1 = "2x^3 - 3x^2 + 2x + 1";
        String t_2 = "0";
        String t_3 = "x";
        String t_4 = String.format("%dx^%d", Integer.MAX_VALUE, -100);
        assertEquals(t_1, p_1.toString());
        assertEquals(t_2, p_2.toString());
        assertEquals(t_3, p_3.toString());
        assertEquals(t_4, p_4.toString());
    }

}
