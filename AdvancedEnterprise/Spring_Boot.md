# Spring Boot Framework

## What is Spring Boot?

Spring Boot is a **framework for building production-ready applications** quickly. It simplifies Spring development with:
- **Auto-configuration**: No manual config needed
- **Starter dependencies**: One dependency includes everything
- **Embedded server**: No need for external server setup

## Why Spring Boot?

1. **Fast Development**: Create apps in minutes
2. **Production-Ready**: Built-in monitoring, health checks
3. **No XML Config**: Everything is Java-based
4. **Microservices**: Perfect for building microservices

## Setting Up Spring Boot

### Using Spring Initializr (Easiest)
1. Go to https://start.spring.io
2. Select:
   - Project: Maven/Gradle
   - Language: Java
   - Spring Boot: 3.x
   - Dependencies: Spring Web, Spring Data JPA, MySQL Driver
3. Download and extract
4. Import into IDE

## Project Structure
```
project/
  | src/
  |   | main/
  |   |   | java/com/example/demo/
  |   |   |   | DemoApplication.java   (Main class)
  |   |   |   | controller/            (REST controllers)
  |   |   |   | service/               (Business logic)
  |   |   |   | model/                 (Entities)
  |   |   |   | repository/            (Data access)
  |   |   | resources/
  |   |       | application.properties (Configuration)
  |   | test/
  | pom.xml / build.gradle
```

## Application Properties

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/school
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## Main Application Class

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

`@SpringBootApplication` = `@Configuration` + `@EnableAutoConfiguration` + `@ComponentScan`

## REST API Example

### Complete Spring Boot Application

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/students")
public class StudentApplication {
    
    // In-memory storage (use database in real app)
    private static Map<Integer, Student> students = new HashMap<>();
    private static int nextId = 1;
    
    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }
    
    // GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }
    
    // GET student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return students.get(id);
    }
    
    // POST create student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        student.setId(nextId++);
        students.put(student.getId(), student);
        return student;
    }
    
    // PUT update student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        students.put(id, student);
        return student;
    }
    
    // DELETE student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        students.remove(id);
        return "Student deleted";
    }
}

// Model class
class Student {
    private int id;
    private String name;
    private int age;
    private String grade;
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
```

## REST API Endpoints

| HTTP Method | Endpoint | Action |
|-------------|----------|--------|
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| POST | `/api/students` | Create student |
| PUT | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Delete student |

## Testing REST API

```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void testGetAllStudents() {
        ResponseEntity<List> response = restTemplate.getForEntity(
            "/api/students", List.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setName("Alice");
        student.setAge(20);
        
        ResponseEntity<Student> response = restTemplate.postForEntity(
            "/api/students", student, Student.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
```

## Spring Boot Starters

| Starter | Purpose |
|---------|---------|
| `spring-boot-starter-web` | Build web apps, REST APIs |
| `spring-boot-starter-data-jpa` | Database access with JPA |
| `spring-boot-starter-security` | Security and authentication |
| `spring-boot-starter-test` | Testing |
| `spring-boot-starter-thymeleaf` | Template engine for web UI |

## Layered Architecture

```
Controller Layer (REST endpoints)
    v calls
Service Layer (Business logic)
    v calls
Repository Layer (Database access)
    v uses
Model/Entity Layer (Data structure)
```

### Controller
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
}
```

### Service
```java
@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;
    
    public List<User> findAll() {
        return repository.findAll();
    }
    
    public User save(User user) {
        return repository.save(user);
    }
}
```

### Repository
```java
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // CRUD methods automatically provided!
}
```

## Key Takeaways

1. Spring Boot simplifies Spring development
2. Auto-configuration eliminates manual setup
3. Starter dependencies simplify management
4. Build REST APIs with `@RestController`
5. Layered architecture: Controller → Service → Repository
6. Embedded server included
7. Production-ready with minimal code

## Practice

1. Create Spring Boot project from Spring Initializr
2. Create REST API with CRUD operations
3. Test endpoints with Postman
4. Add database with JPA
5. Implement layered architecture
6. Add validation and error handling
