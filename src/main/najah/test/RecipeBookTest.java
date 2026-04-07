package main.najah.test;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import main.najah.code.RecipeException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
@Execution(ExecutionMode.CONCURRENT)
@DisplayName("RecipeBook Tests")
public class TestRecipeBook {
    RecipeBook recipeBook ;
    @BeforeAll
    public static void beforeAll() {
        System.out.println("starting RecipeBookTests");
    }
    @BeforeEach
    public void setup() {
        recipeBook = new RecipeBook();
        System.out.println("Starting test case...");    }
    @AfterEach
    public void AfterEach() {
        System.out.println("Finishing test case...");
    }
    @AfterAll
    public static void afterAll() {
        System.out.println("All Tests Finished");
    }
    private Recipe createRecipe(String name, String price) throws RecipeException {
        Recipe r = new Recipe();
        r.setName(name);
        r.setPrice(price);
        return r;
    }
    @Test
    @DisplayName("Test Add valid recipe")
    public void testAddValidRecipe() throws RecipeException {
        Recipe r = createRecipe("Americano", "10");
        assertTrue(recipeBook.addRecipe(r));
        assertEquals("Americano", recipeBook.getRecipes()[0].getName());
    }
    @Test
   @DisplayName("Add duplicated recipe")
    public void testAddDuplicatedRecipe() throws RecipeException {
        Recipe r = createRecipe("Americano", "10");
        recipeBook.addRecipe(r);
            assertFalse(recipeBook.addRecipe(r));
    }
    @Test
    @DisplayName("Delete existing recipe")
    public void testDeleteExistingRecipe() throws RecipeException {
        Recipe r = createRecipe("Americano", "10");
        recipeBook.addRecipe(r);
        assertEquals("Americano",recipeBook.deleteRecipe(0));
    }
    @Test
    @DisplayName("Delete unexisting recipe")
    public void testDeleteUnexistingRecipe() throws RecipeException {
        assertNull(recipeBook.deleteRecipe(0));
    }
    @Test
    @DisplayName("Test Editing  existing recipe")
    public void testEditingRecipe() throws RecipeException {
        Recipe r = createRecipe("Americano", "10");
        Recipe r2 = createRecipe("Mocha", "7");
        recipeBook.addRecipe(r);
        recipeBook.addRecipe(r2);
        assertEquals("Americano",recipeBook.editRecipe(0,r2));
    }
    @Test
    @Timeout(value = 100,unit = TimeUnit.MILLISECONDS)
    @DisplayName("Test getRecipe Response")
    public void testGetRecipe() throws RecipeException {
        Recipe r = createRecipe("Americano", "10");
        recipeBook.addRecipe(r);
        assertEquals(4, recipeBook.getRecipes().length);
    }
    @Test
    @DisplayName("Test unexisting recipe")
    public void testUnexistingRecipe() throws RecipeException {
        Recipe r = createRecipe("Mocha", "10");
        assertNull(recipeBook.editRecipe(0,r));
    }
    @ParameterizedTest
    @DisplayName("add recipes with different prices")
    @CsvSource({
            "Latte, 6",
            "Coffee, 3",
            "Mocha, 5",
            "Espresso, 8"
    })
    void testAddRecipeWithDifferentPrices(String Name,String price) throws RecipeException {
        Recipe r = createRecipe(Name, price);
        assertTrue(recipeBook.addRecipe(r));
    }

}
