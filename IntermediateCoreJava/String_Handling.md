# String Handling

## What is a String?

A String is a sequence of characters. In Java, strings are objects, not primitive types.

```java
String name = "Alice";  // String literal
String greeting = new String("Hello");  // Using new keyword
```

## String Immutability

**Strings are IMMUTABLE** - they cannot be changed once created!

```java
String str = "Hello";
str.concat(" World");  // Creates NEW string
System.out.println(str);  // Still "Hello"!
```

Every modification creates a new String object in memory.

## String Pool (Special Memory Area)

Java stores string literals in a special pool for efficiency:

```java
String s1 = "Hello";   // Created in pool
String s2 = "Hello";   // Reuses from pool (same object!)
String s3 = new String("Hello");  // New object in heap
```

### Comparing Strings

 **Wrong:** Using `==` checks reference, not content
```java
String s1 = "Hello";
String s2 = "Hello";

System.out.println(s1 == s2);  // true (same pool object)
System.out.println(s1 == s3);  // false (different objects)
```

 **Correct:** Use `.equals()`
```java
System.out.println(s1.equals(s2));  // true (same content)
```

## String Class Methods

### Length
```java
String text = "Hello";
System.out.println(text.length());  // 5
```

### Character at Index
```java
String text = "Hello";
System.out.println(text.charAt(0));  // H
System.out.println(text.charAt(4));  // o
```

### Substring
```java
String text = "Hello World";

// From index to end
System.out.println(text.substring(6));    // World

// From index (inclusive) to index (exclusive)
System.out.println(text.substring(0, 5)); // Hello
```

### Concatenation
```java
String first = "Hello";
String second = "World";

// Using + operator
String result = first + " " + second;  // Hello World

// Using concat()
String result2 = first.concat(" ").concat(second);  // Hello World
```

### Compare Strings
```java
String s1 = "apple";
String s2 = "Apple";

System.out.println(s1.equals(s2));      // false (case-sensitive)
System.out.println(s1.equalsIgnoreCase(s2));  // true (ignores case)

// Compare alphabetically
System.out.println(s1.compareTo(s2));   // positive (s1 > s2)
```

### Upper and Lower Case
```java
String text = "Hello World";

System.out.println(text.toUpperCase());  // HELLO WORLD
System.out.println(text.toLowerCase());  // hello world
```

### Trim (Remove Whitespace)
```java
String text = "  Hello World  ";
System.out.println(text.trim());  // "Hello World"
```

### Replace
```java
String text = "Hello World";

System.out.println(text.replace('l', 'L'));      // HeLLo WorLd
System.out.println(text.replace("World", "Java")); // Hello Java
```

### Split (Break into Array)
```java
String text = "apple,banana,orange";
String[] fruits = text.split(",");

// fruits = ["apple", "banana", "orange"]
for (String fruit : fruits) {
    System.out.println(fruit);
}
```

### Contains (Check if Substring Exists)
```java
String text = "Hello World";

System.out.println(text.contains("World"));   // true
System.out.println(text.contains("Java"));    // false
```

### Starts With / Ends With
```java
String text = "Hello World";

System.out.println(text.startsWith("Hello"));  // true
System.out.println(text.endsWith("World"));    // true
```

### Index Of (Find Position)
```java
String text = "Hello World";

System.out.println(text.indexOf('o'));      // 4 (first occurrence)
System.out.println(text.indexOf("World"));  // 6
System.out.println(text.indexOf("Java"));   // -1 (not found)
```

### isEmpty / isBlank
```java
String s1 = "";
String s2 = "   ";

System.out.println(s1.isEmpty());    // true
System.out.println(s1.isBlank());    // true
System.out.println(s2.isEmpty());    // false
System.out.println(s2.isBlank());    // true
```

## StringBuilder (Mutable String)

When you need to modify strings frequently, use StringBuilder - it's faster and mutable!

### Creating StringBuilder
```java
StringBuilder sb = new StringBuilder("Hello");
```

### Common Operations
```java
StringBuilder sb = new StringBuilder("Hello");

// Append
sb.append(" World");        // Hello World
sb.append(" Java");         // Hello World Java

// Insert at position
sb.insert(6, "Beautiful "); // Hello Beautiful World Java

// Delete
sb.delete(6, 16);           // Hello World Java

// Replace
sb.replace(6, 11, "Amazing"); // Hello Amazing Java

// Reverse
sb.reverse();               // avaJ gnizamA olleH
```

### StringBuilder Methods
| Method | Description |
|--------|-------------|
| `append()` | Add text at end |
| `insert()` | Insert at position |
| `delete()` | Remove characters |
| `replace()` | Replace characters |
| `reverse()` | Reverse string |
| `length()` | Get length |
| `toString()` | Convert to String |

### Example
```java
StringBuilder sb = new StringBuilder();

for (int i = 1; i <= 5; i++) {
    sb.append(i).append(" ");
}

System.out.println(sb.toString());  // "1 2 3 4 5 "
```

## StringBuffer (Thread-Safe Version)

Similar to StringBuilder but thread-safe (slower).

```java
StringBuffer sbf = new StringBuffer("Hello");
sbf.append(" World");
System.out.println(sbf);  // Hello World
```

**When to use:**
- StringBuilder: Single-threaded (most cases)
- StringBuffer: Multi-threaded (rare)

## Comparing String, StringBuilder, StringBuffer

| Feature | String | StringBuilder | StringBuffer |
|---------|--------|---------------|--------------|
| Mutability | Immutable | Mutable | Mutable |
| Thread-safe | Yes | No | Yes |
| Performance | Slow | Fast | Moderate |
| Use case | Fixed text | Single-threaded | Multi-threaded |

## Common String Operations

### Reverse a String
```java
public class ReverseString {
    public static void main(String[] args) {
        String original = "Hello";
        String reversed = "";

        for (int i = original.length() - 1; i >= 0; i--) {
            reversed += original.charAt(i);
        }

        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reversed);
    }
}
```

### Check Palindrome
```java
public class Palindrome {
    public static void main(String[] args) {
        String text = "madam";
        String reversed = new StringBuilder(text).reverse().toString();

        if (text.equals(reversed)) {
            System.out.println(text + " is a palindrome");
        } else {
            System.out.println(text + " is NOT a palindrome");
        }
    }
}
```

### Count Vowels
```java
public class CountVowels {
    public static void main(String[] args) {
        String text = "Hello World";
        int count = 0;

        for (char c : text.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                count++;
            }
        }

        System.out.println("Vowels: " + count);  // 3
    }
}
```

## Key Takeaways

1. Strings are immutable - every change creates new object
2. Use `.equals()` to compare content, not `==`
3. String Pool improves performance
4. StringBuilder for mutable strings (faster)
5. StringBuffer for thread-safe mutable strings
6. Many useful methods: `length()`, `charAt()`, `substring()`, `split()`, etc.

## Practice

1. Reverse a string
2. Check if string is palindrome
3. Count vowels and consonants
4. Count words in a sentence
5. Replace all spaces with hyphens
