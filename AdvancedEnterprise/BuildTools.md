# Build Tools Guide

## Maven

### pom.xml Structure
```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>myapp</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <version>3.0.0</version>
        </dependency>
    </dependencies>
</project>
```

### Common Commands
- `mvn compile` - Compile source code
- `mvn test` - Run tests
- `mvn package` - Create JAR/WAR
- `mvn clean` - Clean build

## Gradle

### build.gradle Structure
```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.0.0'
}

group = 'com.example'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
}
```

### Common Commands
- `gradle build` - Build project
- `gradle test` - Run tests
- `gradle clean` - Clean build
- `gradle run` - Run application

## Comparison
| Feature | Maven | Gradle |
|---------|-------|--------|
| Build File | XML (pom.xml) | Groovy/Kotlin DSL |
| Speed | Slower | Faster |
| Flexibility | Less | More |
| Learning Curve | Easy | Moderate |
