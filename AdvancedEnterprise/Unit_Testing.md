# Unit Testing

## What is Unit Testing?

Unit testing tests **individual units** (methods/classes) in isolation.

Think of it as **checking each LEGO brick** before building the castle.

```
Unit Test → Test a single method
Integration Test → Test multiple components together
E2E Test → Test entire application
```

## Why Unit Testing?

1. **Catch bugs early**: Before they reach production
2. **Confidence**: Refactor safely
3. **Documentation**: Tests show how code works
4. **Design**: Forces good code structure

## JUnit - Java Testing Framework

### JUnit 5 (Jupiter)

Modern testing framework for Java.

### Setup

**Maven:**
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
</dependency>
```

**Gradle:**
```groovy
testImplementation 'org.junit.jupiter:junit-jupiter-api:5.9.2'
testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.9.2'
```

## Writing Tests

### Basic Test Structure
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    
    @Test
    void testAddition() {
        Calculator calc = new Calculator();
        int result = calc.add(2, 3);
        assertEquals(5, result);
    }
}
```

### Assertions

| Method | Description |
|--------|-------------|
| `assertEquals(expected, actual)` | Values are equal |
| `assertNotEquals(expected, actual)` | Values are not equal |
| `assertTrue(condition)` | Condition is true |
| `assertFalse(condition)` | Condition is false |
| `assertNull(object)` | Object is null |
| `assertNotNull(object)` | Object is not null |
| `assertSame(expected, actual)` | Same object |
| `assertNotSame(expected, actual)` | Different objects |
| `assertArrayEquals(expected, actual)` | Arrays are equal |
| `assertThrows(Exception.class, () → {...})` | Exception thrown |

## Complete Example

### Calculator Class
```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    public int subtract(int a, int b) {
        return a - b;
    }
    
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }
}
```

### Test Class
```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    void testAddition() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(10, calculator.add(7, 3));
        assertEquals(0, calculator.add(-5, 5));
    }
    
    @Test
    void testSubtraction() {
        assertEquals(2, calculator.subtract(5, 3));
        assertEquals(-3, calculator.subtract(0, 3));
    }
    
    @Test
    void testMultiplication() {
        assertEquals(15, calculator.multiply(3, 5));
        assertEquals(-10, calculator.multiply(-2, 5));
    }
    
    @Test
    void testDivision() {
        assertEquals(2, calculator.divide(6, 3));
        assertEquals(3, calculator.divide(10, 3));  // Integer division
    }
    
    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () → {
            calculator.divide(5, 0);
        });
    }
}
```

## Test Lifecycle Annotations

### `@BeforeEach`
Runs before each test method.

```java
@BeforeEach
void setUp() {
    calculator = new Calculator();
}
```

### `@AfterEach`
Runs after each test method.

```java
@AfterEach
void tearDown() {
    // Cleanup
}
```

### `@BeforeAll`
Runs once before all tests.

```java
@BeforeAll
static void init() {
    // Initialize once
}
```

### `@AfterAll`
Runs once after all tests.

```java
@AfterAll
static void cleanup() {
    // Cleanup once
}
```

## Running Tests

### Command Line
```bash
mvn test
```

### IDE
Right-click test file → Run

## Mockito - Mocking Framework

Mock objects for testing in isolation.

### Setup
```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.3.1</version>
    <scope>test</scope>
</dependency>
```

### Example: Mocking Database

```java
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    
    @Test
    void testGetUser() {
        // Create mock
        UserRepository mockRepo = mock(UserRepository.class);
        
        // Define behavior
        when(mockRepo.findById(1)).thenReturn(new User("Alice", 25));
        
        // Use mock
        UserService service = new UserService(mockRepo);
        User user = service.getUser(1);
        
        // Verify
        assertEquals("Alice", user.getName());
        verify(mockRepo).findById(1);
    }
}
```

### Common Mockito Methods

| Method | Description |
|--------|-------------|
| `mock(Class)` | Create mock object |
| `when(mock.method()).thenReturn(value)` | Define behavior |
| `verify(mock).method()` | Verify method called |
| `times(n)` | Verify called n times |
| `never()` | Verify never called |
| `any()` | Match any value |

## Test Naming Convention

```java
@Test
void methodName_condition_expectedResult() {
    // test
}

// Examples
void add_twoNumbers_returnsSum()
void divide_byZero_throwsException()
void isValidEmail_invalidEmail_returnsFalse()
```

## Test Coverage

Percentage of code covered by tests.

- Aim for **80%+** coverage
- Not 100% - diminishing returns
- Focus on critical paths

## Test-Driven Development (TDD)

1. **Write failing test**
2. **Write code to pass test**
3. **Refactor**
4. Repeat

```
RED → Write failing test
GREEN → Write minimum code to pass
REFACTOR → Improve code
```

## Common Assertions Examples

```java
// String tests
assertEquals("Hello", greeting);
assertTrue(name.startsWith("A"));
assertFalse(age < 0);

// Collection tests
List<String> list = Arrays.asList("a", "b", "c");
assertTrue(list.contains("a"));
assertEquals(3, list.size());

// Exception tests
assertThrows(IllegalArgumentException.class, () → {
    validateAge(-5);
});
```

## Best Practices

1. **One assertion per test** (ideally)
2. **Test edge cases**: Zero, negative, null
3. **Use descriptive names**: `testAddition_positiveNumbers_returnsSum()`
4. **Keep tests independent**: Don't rely on other tests
5. **Use setUp/tearDown**: Initialize/clean resources
6. **Don't test implementation**: Test behavior

## Complete Example: Student Service

```java
// Student.java
public class Student {
    private String name;
    private int marks;
    
    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
    
    public String getName() { return name; }
    public int getMarks() { return marks; }
    public String getGrade() {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        return "B";
    }
}

// StudentService.java
public class StudentService {
    public String getHonorStatus(Student student) {
        if (student.getMarks() >= 90) {
            return "On Honor Roll";
        }
        return "Regular";
    }
}

// StudentServiceTest.java
class StudentServiceTest {
    private StudentService service;
    
    @BeforeEach
    void setUp() {
        service = new StudentService();
    }
    
    @Test
    void testHighMarksStudent_isOnHonorRoll() {
        Student student = new Student("Alice", 95);
        assertEquals("On Honor Roll", service.getHonorStatus(student));
    }
    
    @Test
    void testLowMarksStudent_isRegular() {
        Student student = new Student("Bob", 75);
        assertEquals("Regular", service.getHonorStatus(student));
    }
}
```

## Key Takeaways

1. Unit tests test individual methods/classes
2. JUnit 5 is standard testing framework
3. Use assertions to verify expected results
4. Mockito for mocking dependencies
5. `@BeforeEach` for setup, `@AfterEach` for cleanup
6. Test edge cases and error conditions
7. Aim for 80%+ test coverage

## Practice

1. Write tests for Calculator class
2. Test all operations (add, subtract, multiply, divide)
3. Test edge cases (divide by zero, negative numbers)
4. Write tests for String utility class
5. Practice with Mockito for service classes
