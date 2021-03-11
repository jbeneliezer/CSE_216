import org.junit.jupiter.api.Assertions;

public class BasicMathTest extends Assertions {

    public static void divideTest() {
        BasicMath a = new BasicMath();
        assertEquals(2.0, a.divide(10, 5), "success");
    }

    public static void divideTest2() {
        BasicMath b = new BasicMath();
        assertThrows(ArithmeticException.class, () -> {
            b.divide(10, 0);
        });
    }

    public static void main(String... args) {
        divideTest();
        divideTest2();
    }
}
