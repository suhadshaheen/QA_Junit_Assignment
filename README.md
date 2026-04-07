# JUnit5 Assignment #2

## Project Structure
- src/main/najah/code/ → Source classes
- src/main/najah/test/ → Test classes

## Test Classes
- CalculatorTest → Calculator
- ProductTest → Product
- UserServiceTest → UserService
- RecipeBookTest → RecipeBook

## How to Run
1. Open project in IntelliJ IDEA
2. Right click `main.najah.test` → Run Tests
3. Or run `TestsSuite` to run all tests
## Test Coverage
Used **IntelliJ IDEA built in coverage tool** equivalent to EclEmma instead of Eclipse.

### How to run coverage in IntelliJ:
1. Right click on test class or package
2. Select **"Run with Coverage"**
3. Coverage report appears in the Coverage panel

### Coverage Results
- CalculatorTest → 100%
- ProductTest → 100%
- UserServiceTest → 100%
- RecipeBookTest → 100%

## Parallel Execution
Enabled in `RecipeBookTest` using:
- `@Execution(ExecutionMode.CONCURRENT)`
- `junit-platform.properties` file