package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import main.najah.code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

@DisplayName("UserService Tests")
class UserServiceSimpleTest {
    UserService userService;
    @BeforeAll
    static void BeforeAll()
    {
        System.out.println("Starting UserServiceTests");
    }
    @BeforeEach
    void setUp() throws Exception {
        userService = new UserService();
        System.out.println("Starting Test Case...");
    }
    @AfterEach
    void AfterEach() throws Exception {
        System.out.println("Finished Test Case...");
    }
    @AfterAll
    static void AfterAll()  {
        System.out.println("All Tests Finished");
    }
    @Test
    @DisplayName("Test Valid Email")
    void testValidEmail() {
        assertTrue(userService.isValidEmail("test@example.com"));
    }
    @Test
    @DisplayName("Test Invalid Email")
    void testInvalidEmail() {
        assertFalse(userService.isValidEmail(null),"email should not be null");
        assertFalse(userService.isValidEmail("suhad.com"),"email should contains @");
        assertFalse(userService.isValidEmail("suhadshaheen@stuedu"),"email should contains .");
    }
    @Test
    @DisplayName("Valid authentication")
    void testAuthenticateValid() {
        assertTrue(userService.authenticate("admin", "1234"));
    }
    @Test
    @DisplayName("Invalid authentication")
    void testAuthenticateInvalid() {
        assertFalse(userService.authenticate("admin", "33333"));
        assertFalse(userService.authenticate("someone", "1234"));
    }

    @ParameterizedTest
    @DisplayName("Parameterized email Test")
    @CsvSource({
            "s12325463@stu.najah.edu, true",
            "suhad-shaheen, false",
            "suhad@gmail.com, true",
            "suhadsh.com, false"
    })
    void testIsValidEmailParameterized(String email, boolean expected) {
        assertEquals(expected, userService.isValidEmail(email));
    }

    @Test
    @DisplayName("Test auth response")
    @Timeout(value = 100,unit = TimeUnit.MILLISECONDS)
    void testAuthResponse() {
        userService.authenticate("admin", "1234");
    }


}
