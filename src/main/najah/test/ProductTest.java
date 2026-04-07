package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import main.najah.code.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import java.util.concurrent.TimeUnit;

@DisplayName("Product Tests")
public class ProductTest {
    Product p;
    @BeforeAll
    static void BeforeAll()
    {
        System.out.println("Starting ProductTests");
    }
    @BeforeEach
    void setUp() throws Exception {
        p = new Product("Charger",50);
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
    @DisplayName("Valid product creation")
    void testProductCreation() {
        assertEquals("Charger", p.getName());
        p.applyDiscount(10);
        assertEquals(10, p.getDiscount());
        p.applyDiscount(p.getPrice());
        assertEquals(50, p.getDiscount());
    }
    @Test
    @DisplayName("Test getFinalPrice Response")
    @Timeout(value = 1000,unit = TimeUnit.MILLISECONDS)
    void testGetFinalPriceResponse() {
        p.applyDiscount(50);
        double price= p.getFinalPrice();
        assertEquals(price, p.getFinalPrice());
    }
    @Test
    @DisplayName("Negative price Test")
    void testNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Phone Case", -100));
    }
    @Test
    @DisplayName("Invalid Discount Test on applyDiscount Method")
    void TestInvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(60));
        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(-5));
    }
    @ParameterizedTest
    @DisplayName("Parameterized discount test")
    @CsvFileSource(resources = "Data.csv")
    void testDiscountParameterized(double discount, double expected) {
        p.applyDiscount(discount);
        assertEquals(expected, p.getFinalPrice());
    }

}
