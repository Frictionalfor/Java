# Advanced & Enterprise Level Notes

## Memory Management

- **Heap**: Objects stored here, shared among threads, garbage collected
- **Stack**: Method calls and local variables, thread-specific
- **Garbage Collection**: Automatic memory management, removes unreachable objects

## JDBC

- Java Database Connectivity API
- Steps: Load driver → Create connection → Create statement → Execute query → Close connection
- Drivers: Type 1 (JDBC-ODBC), Type 2 (Native API), Type 3 (Network Protocol), Type 4 (Thin Driver)

## Build Tools

- **Maven**: XML-based (pom.xml), convention over configuration
- **Gradle**: Groovy/Kotlin DSL, faster builds

## Version Control

- **Git**: Distributed version control
- Commands: `git init`, `git add`, `git commit`, `git push`, `git pull`, `git branch`

## Unit Testing

- **JUnit**: Testing framework for Java
- **Mockito**: Mocking framework
- Test methods annotated with `@Test`
- Assertions: `assertEquals`, `assertTrue`, etc.

## Spring Boot Framework

- Dependency Injection (IoC)
- Auto-configuration
- REST API development
- Microservices architecture
