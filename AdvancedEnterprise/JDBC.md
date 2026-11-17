# JDBC

## What is JDBC?

**JDBC (Java Database Connectivity)** is an API to connect and execute queries with databases.

Like a **bridge** between Java application and database.

```
Java Application → JDBC API → Database Driver → Database
```

## JDBC Architecture

```
JDBC API (Java code)
    v
JDBC Driver Manager
    v
JDBC Driver (database-specific)
    v
Database (MySQL, PostgreSQL, etc.)
```

## JDBC Drivers

| Type | Name | Description |
|------|------|-------------|
| Type 1 | JDBC-ODBC Bridge | Uses ODBC driver |
| Type 2 | Native API | Uses database native library |
| Type 3 | Network Protocol | Middleware server |
| Type 4 | Thin Driver | Pure Java, most common |

## JDBC Steps

1. **Load driver** (optional in modern JDBC)
2. **Create connection**
3. **Create statement**
4. **Execute query**
5. **Process results**
6. **Close connection**

## Setting Up

### 1. Add JDBC Driver to Project

**Maven:**
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

**Gradle:**
```groovy
implementation 'mysql:mysql-connector-java:8.0.33'
```

## Complete Example

```java
import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/school";
        String username = "root";
        String password = "password";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            // 1. Load driver (optional)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. Create connection
            connection = DriverManager.getConnection(url, username, password);
            
            // 3. Create statement
            statement = connection.createStatement();
            
            // 4. Execute query
            resultSet = statement.executeQuery("SELECT * FROM students");
            
            // 5. Process results
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                
                System.out.println(id + " | " + name + " | " + age);
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // 6. Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

## try-with-resources for JDBC

```java
public class JDBCWithTryWithResources {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/school";
        
        try (
            Connection conn = DriverManager.getConnection(url, "root", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students")
        ) {
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

## CRUD Operations

### CREATE (INSERT)
```java
String sql = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";

try (
    Connection conn = DriverManager.getConnection(url, user, pass);
    PreparedStatement pstmt = conn.prepareStatement(sql)
) {
    pstmt.setString(1, "Alice");
    pstmt.setInt(2, 20);
    pstmt.setString(3, "A");
    
    int rowsAffected = pstmt.executeUpdate();
    System.out.println("Inserted " + rowsAffected + " row(s)");
}
```

### READ (SELECT)
```java
String sql = "SELECT * FROM students WHERE grade = ?";

try (
    Connection conn = DriverManager.getConnection(url, user, pass);
    PreparedStatement pstmt = conn.prepareStatement(sql)
) {
    pstmt.setString(1, "A");
    
    try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
    }
}
```

### UPDATE
```java
String sql = "UPDATE students SET age = ? WHERE id = ?";

try (
    Connection conn = DriverManager.getConnection(url, user, pass);
    PreparedStatement pstmt = conn.prepareStatement(sql)
) {
    pstmt.setInt(1, 21);
    pstmt.setInt(2, 101);
    
    int rowsAffected = pstmt.executeUpdate();
    System.out.println("Updated " + rowsAffected + " row(s)");
}
```

### DELETE
```java
String sql = "DELETE FROM students WHERE id = ?";

try (
    Connection conn = DriverManager.getConnection(url, user, pass);
    PreparedStatement pstmt = conn.prepareStatement(sql)
) {
    pstmt.setInt(1, 101);
    
    int rowsAffected = pstmt.executeUpdate();
    System.out.println("Deleted " + rowsAffected + " row(s)");
}
```

## PreparedStatement vs Statement

| Feature | Statement | PreparedStatement |
|---------|-----------|-------------------|
| Performance | Slower | Faster (pre-compiled) |
| SQL Injection | Vulnerable | Protected |
| Parameters | Concatenate | Use `?` placeholders |

**Always use PreparedStatement!**

## JDBC Data Types

| Java Type | SQL Type |
|-----------|----------|
| `int` | `INTEGER` |
| `long` | `BIGINT` |
| `double` | `DOUBLE` |
| `String` | `VARCHAR` |
| `boolean` | `BOOLEAN` |
| `Date` | `DATE` |
| `Timestamp` | `TIMESTAMP` |
| `Blob` | `BLOB` |
| `Clob` | `CLOB` |

## Transaction Management

```java
Connection conn = DriverManager.getConnection(url, user, pass);

try {
    conn.setAutoCommit(false);  // Start transaction
    
    // Execute multiple statements
    // ...
    
    conn.commit();  // Save changes
    
} catch (SQLException e) {
    conn.rollback();  // Undo changes
} finally {
    conn.close();
}
```

## Batch Processing

```java
String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

try (
    Connection conn = DriverManager.getConnection(url, user, pass);
    PreparedStatement pstmt = conn.prepareStatement(sql)
) {
    // Add multiple batches
    pstmt.setString(1, "Alice");
    pstmt.setInt(2, 20);
    pstmt.addBatch();
    
    pstmt.setString(1, "Bob");
    pstmt.setInt(2, 22);
    pstmt.addBatch();
    
    // Execute batch
    int[] results = pstmt.executeBatch();
    System.out.println("Inserted " + results.length + " rows");
}
```

## Metadata

```java
DatabaseMetaData metaData = conn.getMetaData();

System.out.println("Database: " + metaData.getDatabaseProductName());
System.out.println("Driver: " + metaData.getDriverName());

ResultSet tables = metaData.getTables(null, null, "%", null);
while (tables.next()) {
    System.out.println(tables.getString("TABLE_NAME"));
}
```

## Connection Pooling

For production, use connection pools (HikariCP, Apache DBCP).

```java
// Example with HikariCP
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/school");
config.setUsername("root");
config.setPassword("password");

HikariDataSource ds = new HikariDataSource(config);
Connection conn = ds.getConnection();
```

## Key Takeaways

1. JDBC = Java to database bridge
2. Always use `PreparedStatement` (prevents SQL injection)
3. Use try-with-resources for auto-closing
4. Close ResultSet, Statement, Connection in order
5. Use connection pools in production

## Practice

1. Create database and table in MySQL
2. Insert student records using JDBC
3. Read all students from database
4. Update student marks
5. Delete student record
