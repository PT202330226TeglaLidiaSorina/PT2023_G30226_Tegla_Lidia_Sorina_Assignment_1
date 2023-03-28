package org.example;
import PolynomialStuff.Polynomial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PolynomialTest {
    Polynomial polynomial1;
    Polynomial polynomial2;

    @BeforeEach
    void setUp() {
        polynomial1 = Polynomial.createPolynomial("3x^2 + 2x + 1");
        polynomial2 = Polynomial.createPolynomial("x^2 + x");
    }


    @Test
    void testAddition() {
        Polynomial sum = polynomial1.add(polynomial2);
        assertEquals("1+3x+4x^2", sum.toString());
    }

    @Test
    void testSubtraction() {
        Polynomial difference = polynomial1.subtract(polynomial2);
        assertEquals("1+1x+2x^2", difference.toString());
    }

    @Test
    void testMultiplication() {
        Polynomial product = polynomial1.multiply(polynomial2);
        assertEquals("1x+3x^2+5x^3+3x^4", product.toString());
    }

    @Test
    void testDerivation() {
        Polynomial derivative1 = polynomial1.derivation();
        Polynomial derivative2 = polynomial2.derivation();

        assertEquals("2+6x", derivative1.toString());
        assertEquals("1+2x", derivative2.toString());
    }

    @Test
    void testIntegration() {
        Polynomial integral1 = polynomial1.integration();
        Polynomial integral2 = polynomial2.integration();

        assertEquals("1x+x^2+x^3", integral1.toString());
        assertEquals("0.5x^2+0.3x^3", integral2.toString());
    }

   /* @Test
    void testDivision() {
        Polynomial divisor = Polynomial.createPolynomial("x + 1");
        Polynomial[] result = polynomial1.division(divisor);
    }*/

}
