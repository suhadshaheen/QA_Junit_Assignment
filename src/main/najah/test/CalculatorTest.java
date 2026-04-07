package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.*;

import main.najah.code.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Calculator Tests")
public class CalculatorTest {

    Calculator calc;
@BeforeAll
static void BeforeAll()
{
    System.out.println("Starting CalculatorTests");
}
    @BeforeEach
    void setUp() throws Exception {
       calc = new Calculator();
        System.out.println("Starting test case...");
    }
    @AfterEach
    void AfterEach()  {
        System.out.println("Finished Test Case...");
    }
@AfterAll
static void AfterAll()  {
    System.out.println("All Tests Finished");
}
    @Test
    @Order(1)
    @DisplayName("Valid Addition")
    void testAddition() {
        assertEquals(10,calc.add(5,5));
        assertEquals(10, calc.add(1, 2, 3, 4));
    }
    @Test
    @Order(2)
    @DisplayName("test invalid add")
    void testAddZero() {
        assertEquals(0, calc.add());
        assertEquals(0, calc.add(0,0,0));
    }

 @Test
 @Order(3)
 @DisplayName("Valid Division")
 void testDivision() {
    assertEquals(5,calc.divide(10,2));

 }

 @Test
 @Order(4)
 @DisplayName("Divide by zero")
 void testInvalidDivision() {
    assertThrows(ArithmeticException.class, () ->calc.divide(10,0));
 }
 @Test
 @DisplayName("test valid n factorial")
 @Order(5)
 void testValidFactorial() {
    assertEquals(120,calc.factorial(5));
 }
@Test
@DisplayName("Test invalid n factorial")
@Order(6)
void testInvalidFactorial() {
    assertThrows(IllegalArgumentException.class, () ->calc.factorial(-5));
}
    @ParameterizedTest
    @Order(7)
    @DisplayName("Parameterized Division Test")
    @CsvSource({
            "10,2,5",
            "5,5,1",
            "20,4,5",
            "1,1,1"
    })
    void testDivisionParameterized(int a, int b, int expected) {
        assertEquals(expected, calc.divide(a, b));
    }
    @Test
    @DisplayName("Test Factorial Response time on large n")
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    @Order(8)
    void testFactorialResponse() {
    assertEquals(3628800, calc.factorial(10));
    }

    @Test
    @Order(9)
    @Disabled("Intentionally failing: factorial overflows for larger inputs, to Fix by using long or BigInteger.")
    @DisplayName("Disabled failing test for factorial overflow")
    void testFactorialOverflowExpectedCorrectValue() {
        assertEquals(6227020800L, calc.factorial(13));
    }

}
