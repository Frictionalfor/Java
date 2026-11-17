# Build Tools

## What are Build Tools?

Build tools **automate** the process of:
- Compiling source code
- Managing dependencies
- Running tests
- Packaging (JAR/WAR files)
- Deploying applications

Like a **project manager** that handles all repetitive tasks!

## Popular Build Tools

1. **Maven** - Convention over configuration, XML-based
2. **Gradle** - Flexible, Groovy/Kotlin DSL, faster

## Maven

### What is Maven?

- XML-based build tool
- Convention over configuration
- Manages dependencies automatically
- Standard project structure

### Project Structure
```
project/
  | pom.xml                    (Project configuration)
  | src/
  |   | main/
  |   |   | java/             (Source code)
  |   |   | resources/        (Config files, properties)
  |   | test/
  |       | java/             (Test code)
  | target/                    (Compiled output)
```

### pom.xml Explained

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
    <!-- Project coordinates -→
    <groupId>com.example</groupId>
    <artifactId>myapp</artifactId>
    <version>1.0-SNAPSHOT</version>
    
    <!-- Dependencies -→
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>
    </dependencies>
    
    <!-- Build configuration -→
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### Key Elements
- **groupId**: Company/project name (like package)
- **artifactId**: Project name
- **version**: Project version
- **dependencies**: Libraries needed

### Common Maven Commands

```bash
# Compile source code
mvn compile

# Run tests
mvn test

# Package as JAR
mvn package

# Clean build
mvn clean

# Install to local repo
mvn install

# Generate project from archetype
mvn archetype:generate
```

## Gradle

### What is Gradle?

- Groovy/Kotlin DSL build tool
- More flexible than Maven
- Faster builds (incremental)
- Modern and powerful

### Project Structure
```
project/
  | build.gradle             (Build script)
  | settings.gradle          (Project settings)
  | src/
  |   | main/java/
  |   | main/resources/
  | build/                   (Output directory)
```

### build.gradle Explained

```groovy
plugins {
    id 'java'
    id 'application'
}

group = 'com.example'
version = '1.0.0'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'mysql:mysql-connector-java:8.0.33'
    testImplementation 'junit:junit:4.13.2'
}

application {
    mainClass = 'com.example.Main'
}
```

### Common Gradle Commands

```bash
# Build project
gradle build

# Compile
gradle compileJava

# Run tests
gradle test

# Clean
gradle clean

# Run application
gradle run
```

## Dependency Management

### What are Dependencies?

External libraries your project needs.

```
Your Project
    | Spring Boot (dependency)
    | MySQL Connector (dependency)
    | JUnit (dependency)
    | Your code
```

### Transitive Dependencies
```
Your Project → Spring Boot → Spring Core → Commons Logging
```

Build tool automatically manages all these!

## Repositories

Where dependencies are downloaded from.

```xml
<!-- Maven -→
<repositories>
    <repository>
        <id>central</id>
        <url>https://repo.maven.apache.org/maven2</url>
    </repository>
</repositories>
```

```groovy
// Gradle
repositories {
    mavenCentral()
    maven { url "https://jitpack.io" }
}
```

## Maven Lifecycle

```
clean → validate → compile → test → package → verify → install → deploy
```

## Common Dependencies

### Spring Boot
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.0.0</version>
</dependency>
```

### Database
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

### Testing
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

## Maven vs Gradle

| Feature | Maven | Gradle |
|---------|-------|--------|
| Build File | XML (pom.xml) | Groovy/Kotlin (build.gradle) |
| Speed | Slower | Faster |
| Flexibility | Less | More |
| Learning Curve | Easy | Moderate |
| Convention | Strong | Flexible |
| IDE Support | Excellent | Good |

## Which to Choose?

- **Maven**: Industry standard, lots of tutorials, stable
- **Gradle**: Modern, faster, more flexible
- **For beginners**: Start with Maven (simpler)
- **For large projects**: Consider Gradle

## Key Takeaways

1. Build tools automate compilation, testing, packaging
2. **Maven**: XML, convention-based, stable
3. **Gradle**: DSL, fast, flexible
4. Dependencies managed automatically
5. Standard project structure
6. Essential for real-world projects

## Practice

1. Create Maven project structure
2. Add Spring Boot dependency
3. Run `mvn compile` and `mvn test`
4. Create simple Gradle build file
5. Compare Maven and Gradle for same project
