package mathLibrary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathOperationsTest {

    @Test
    void testAdd() {
        assertEquals(30.0, MathOperations.add(10, 20));
    }

    @Test
    void testSubtract() {
        assertEquals(10.0, MathOperations.subtract(20, 10));
    }

    @Test
    void testMultiply() {
        assertEquals(200.0, MathOperations.multiply(10, 20));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, MathOperations.divide(20, 10));
    }

    @Test
    void testDivideByZero() {
        assertThrows(
                ArithmeticException.class,
                () -> MathOperations.divide(10, 0)
        );
    }

    @Test
    void testPower() {
        assertEquals(100.0, MathOperations.power(10, 2));
    }

    @Test
    void testSqrt() {
        assertEquals(5.0, MathOperations.sqrt(25));
    }

    @Test
    void testSqrtNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> MathOperations.sqrt(-25)
        );
    }
}