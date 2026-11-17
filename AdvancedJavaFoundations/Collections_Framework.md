# Java Collections Framework

## What are Collections?

Collections are **group of objects** stored in a single unit. Like a container that holds multiple items.

**Array vs Collection:**
- Array: Fixed size, same type only
- Collection: Dynamic size, flexible, many methods

## Collection Framework Hierarchy

```
Iterable (interface)
| Collection (interface)
    | List (interface)
    |   | ArrayList (class)
    |   | LinkedList (class)
    | Set (interface)
    |   | HashSet (class)
    |   | TreeSet (class)
    | Queue (interface)
        | LinkedList (class)
        | PriorityQueue (class)

Map (interface) - NOT part of Collection
| HashMap (class)
| TreeMap (class)
```

## List Interface

Ordered collection, allows duplicates, index-based access.

### ArrayList
- Backed by array
- Fast random access
- Slow insertion/deletion in middle

```java
import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        // Create ArrayList
        List<String> fruits = new ArrayList<>();
        
        // Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        
        // Access elements
        System.out.println(fruits.get(0));     // Apple
        System.out.println(fruits.get(2));     // Orange
        
        // Size
        System.out.println(fruits.size());     // 3
        
        // Contains
        System.out.println(fruits.contains("Banana"));  // true
        
        // Remove
        fruits.remove("Banana");
        
        // Iterate
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}
```

### LinkedList
- Doubly linked list
- Fast insertion/deletion
- Slow random access

```java
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
    public static void main(String[] args) {
        List<String> tasks = new LinkedList<>();
        
        // Add at specific position
        tasks.add(0, "Wake up");
        tasks.add("Brush teeth");
        tasks.add("Eat breakfast");
        
        // Remove first
        ((LinkedList<String>) tasks).removeFirst();
        
        // Add at end
        tasks.addLast("Go to work");
    }
}
```

## Set Interface

Unordered collection, no duplicates.

### HashSet
- No order guaranteed
- Fast performance
- Allows one null

```java
import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        Set<String> uniqueNames = new HashSet<>();
        
        uniqueNames.add("Alice");
        uniqueNames.add("Bob");
        uniqueNames.add("Alice");  // Duplicate - ignored!
        
        System.out.println(uniqueNames.size());  // 2
        System.out.println(uniqueNames);  // [Alice, Bob] (order may vary)
    }
}
```

### TreeSet
- Sorted order
- Slower than HashSet
- No null values

```java
import java.util.TreeSet;
import java.util.Set;

public class TreeSetDemo {
    public static void main(String[] args) {
        Set<Integer> numbers = new TreeSet<>();
        
        numbers.add(50);
        numbers.add(10);
        numbers.add(30);
        numbers.add(20);
        
        System.out.println(numbers);  // [10, 20, 30, 50] (sorted!)
    }
}
```

## Map Interface

Key-value pairs. Keys must be unique.

### HashMap
- No order
- Fast performance
- One null key, multiple null values

```java
import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<Integer, String> students = new HashMap<>();
        
        // Add key-value pairs
        students.put(101, "Alice");
        students.put(102, "Bob");
        students.put(103, "Charlie");
        
        // Get value by key
        System.out.println(students.get(102));  // Bob
        
        // Check key
        System.out.println(students.containsKey(101));  // true
        
        // Check value
        System.out.println(students.containsValue("Alice"));  // true
        
        // Iterate
        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
```

### TreeMap
- Sorted by keys
- Slower than HashMap

```java
import java.util.TreeMap;
import java.util.Map;

public class TreeMapDemo {
    public static void main(String[] args) {
        Map<Integer, String> students = new TreeMap<>();
        
        students.put(103, "Charlie");
        students.put(101, "Alice");
        students.put(102, "Bob");
        
        System.out.println(students);  // {101=Alice, 102=Bob, 103=Charlie}
    }
}
```

## Queue Interface

FIFO (First In First Out).

### PriorityQueue
Elements ordered by priority (natural order or custom).

```java
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueDemo {
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();
        
        // Add elements
        queue.offer(30);
        queue.offer(10);
        queue.offer(20);
        
        // Peek (view front without removing)
        System.out.println(queue.peek());  // 10 (smallest first)
        
        // Poll (remove front)
        System.out.println(queue.poll());  // 10
        System.out.println(queue.poll());  // 20
    }
}
```

## Common Operations Across Collections

| Operation | List | Set | Map |
|-----------|------|-----|-----|
| Add | `add()` | `add()` | `put()` |
| Remove | `remove()` | `remove()` | `remove()` |
| Get | `get(index)` | N/A | `get(key)` |
| Size | `size()` | `size()` | `size()` |
| Clear | `clear()` | `clear()` | `clear()` |

## Choosing the Right Collection

| Need | Use |
|------|-----|
| Duplicates allowed, index access | ArrayList |
| Frequent insertions/deletions | LinkedList |
| No duplicates | HashSet |
| Sorted unique elements | TreeSet |
| Key-value pairs | HashMap |
| Sorted key-value pairs | TreeMap |
| Priority ordering | PriorityQueue |

## Practical Example: Student Management

```java
import java.util.*;

public class StudentManagement {
    public static void main(String[] args) {
        // List - All students (with duplicates)
        List<String> students = new ArrayList<>();
        students.add("Alice");
        students.add("Bob");
        students.add("Alice");  // Duplicate allowed
        
        // Set - Unique student names
        Set<String> uniqueStudents = new HashSet<>(students);
        
        // Map - Roll number to name
        Map<Integer, String> studentMap = new HashMap<>();
        studentMap.put(101, "Alice");
        studentMap.put(102, "Bob");
        studentMap.put(103, "Charlie");
        
        // Iterate all
        System.out.println("All students: " + students);
        System.out.println("Unique students: " + uniqueStudents);
        System.out.println("Student map: " + studentMap);
    }
}
```

## Key Takeaways

1. **List**: Ordered, allows duplicates, index-based
2. **Set**: Unordered, no duplicates
3. **Map**: Key-value pairs, unique keys
4. **Queue**: FIFO ordering
5. Use generics for type safety: `List<String>`
6. Choose right collection for your needs

## Practice

1. Create ArrayList of 5 student names, iterate
2. Create HashSet to remove duplicates from list
3. Create HashMap to store student roll numbers and names
4. Use TreeSet to sort numbers automatically
