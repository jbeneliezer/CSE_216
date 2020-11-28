import static org.junit.jupiter.api.Assertions.*;

public class TestDensePolynomial {

    /**
     * Tests the string parameterized constructor for DensePolynomial, and uses the assertArrayEquals method to compare
     * return value of getPolynomial() to equivalent int array
     *
     * Tests edge cases and test invalid input with assertThrows
     */
    public static void testConstructor(){
        DensePolynomial p_1 = new DensePolynomial("2x^3 - 3x^2 + 2x + 1");
        DensePolynomial p_2 = new DensePolynomial("0");
        DensePolynomial p_3 = new DensePolynomial("x");
        DensePolynomial p_4 = new DensePolynomial(String.format("%dx^%d", Integer.MAX_VALUE, DensePolynomial.MAX_SIZE - 1));
        DensePolynomial p_5 = new DensePolynomial("");
        int[] t_1 = new int[DensePolynomial.MAX_SIZE];
        t_1[0] = 1;
        t_1[1] = 2;
        t_1[2] = -3;
        t_1[3] = 2;
        int[] t_2 = new int[DensePolynomial.MAX_SIZE];
        int[] t_3 = new int[DensePolynomial.MAX_SIZE];
        t_3[1] = 1;
        int[] t_4 = new int[DensePolynomial.MAX_SIZE];
        t_4[DensePolynomial.MAX_SIZE - 1] = Integer.MAX_VALUE;
        int[] t_5 = new int[DensePolynomial.MAX_SIZE];
        assertArrayEquals(t_1, p_1.getPolynomial(), "should equal {1, 2, 3, 2, ...}");
        assertArrayEquals(t_2, p_2.getPolynomial(), "should equal {0, 0, 0, ...}");
        assertArrayEquals(t_3, p_3.getPolynomial(), "should equal {0, 1, 0, 0, ...}");
        assertArrayEquals(t_4, p_4.getPolynomial(), "should equal {..., Integer.MAX_VALUE}");
        assertArrayEquals(t_5, p_5.getPolynomial(), "should equal {0, 0, 0, ...}");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            new DensePolynomial(String.format("%dx^%d", 1, DensePolynomial.MAX_SIZE));
        });
        assertThrows(NumberFormatException.class, () -> {
            new DensePolynomial("-x^-2");
        });
    }

    /**
     * Tests that degree function of Dense Polynomial works, including max and min values
     */
    public static void testDegree() {
        DensePolynomial p_1 = new DensePolynomial("2x^3 - 3x^2 + 2x + 1");
        DensePolynomial p_2 = new DensePolynomial("0");
        DensePolynomial p_3 = new DensePolynomial("x");
        DensePolynomial p_4 = new DensePolynomial(String.format("%dx^%d", Integer.MAX_VALUE, DensePolynomial.MAX_SIZE - 1));
        DensePolynomial p_5 = new DensePolynomial("");
        assertEquals(3, p_1.degree(), "should be 3");
        assertEquals(0, p_2.degree(), "should be 0");
        assertEquals(1, p_3.degree(), "should be 1");
        assertEquals(DensePolynomial.MAX_SIZE - 1, p_4.degree(), "should be DensePolynomial.MAX_SIZE - 1");
        assertEquals(0, p_5.degree(), "should be 0");
    }

    public static void testIsZero() {
        DensePolynomial p_1 = new DensePolynomial("0");
        DensePolynomial p_2 = new DensePolynomial("x");
        assertTrue(p_1.isZero());
        assertFalse(p_2.isZero());
    }

    public static void testAdd() {
        DensePolynomial p_1 = new DensePolynomial("4x^4 + 3x^2 - 2x + 6");
        DensePolynomial p_2 = new DensePolynomial("3x^3 + 7x^2 + x - 1");
        int[] t_1 = new int[DensePolynomial.MAX_SIZE];
        t_1[0] = 5;
        t_1[1] = -1;
        t_1[2] = 10;
        t_1[3] = 3;
        t_1[4] = 4;
        assertArrayEquals(((DensePolynomial) p_1.add(p_2)).getPolynomial(), t_1, "should be {5, -1, 10, 3, 4, ...}");
    }

    public static void main(String... args) {
        testConstructor();
        testDegree();
        testIsZero();
        testAdd();
    }
}
