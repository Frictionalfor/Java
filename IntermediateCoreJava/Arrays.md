# Arrays

## What is an Array?

An array is a container that holds a fixed number of values of the same type. Like a row of lockers - each locker holds one item, and all lockers are the same size.

```
Index:    0    1    2    3    4
        ||||||
Array:  | 10| 20| 30| 40| 50|
        ||||||
```

## Why Use Arrays?

- Store multiple values in ONE variable
- Easy to iterate through data
- Random access (fast retrieval by index)

## Creating Arrays

### Method 1: Using `new`
```java
int[] marks = new int[5];  // Array of 5 integers
marks[0] = 80;
marks[1] = 85;
marks[2] = 90;
marks[3] = 75;
marks[4] = 95;
```

### Method 2: Array Literal (Easier!)
```java
int[] marks = {80, 85, 90, 75, 95};
```

## Accessing Array Elements

Arrays use **zero-based indexing**:
- First element: index 0
- Last element: index length-1

```java
int[] numbers = {10, 20, 30, 40, 50};

System.out.println(numbers[0]);   // 10 (first element)
System.out.println(numbers[2]);   // 30 (third element)
System.out.println(numbers[4]);   // 50 (last element)

// Out of bounds!
System.out.println(numbers[5]);   // Error! Only 5 elements (0-4)
```

## Array Properties

### Length
```java
int[] arr = {1, 2, 3, 4, 5};
System.out.println(arr.length);  // 5
```

### Important: `.length` not `.length()`
```java
// Array
int len = arr.length;    // No parentheses!

// String
int len = str.length();  // Has parentheses!
```

## Single-Dimensional Arrays

Store data in a single row.

```java
// Integer array
int[] ages = {18, 20, 22, 25, 30};

// String array
String[] names = {"Alice", "Bob", "Charlie"};

// Double array
double[] prices = {19.99, 29.99, 39.99};
```

## Multi-Dimensional Arrays

Arrays of arrays. Think of it as a table/matrix.

### 2D Array - Like a Table
```
         Column 0  Column 1  Column 2
Row 0:   ||||
         |  1 |  2 |  3 |
Row 1:   |++¤
         |  4 |  5 |  6 |
Row 2:   ||||
```

### Creating 2D Arrays
```java
// Method 1
int[][] matrix = new int[3][3];  // 3 rows, 3 columns

// Method 2 (easier)
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

### Accessing Elements
```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

System.out.println(matrix[0][0]);  // 1 (row 0, col 0)
System.out.println(matrix[1][2]);  // 6 (row 1, col 2)
System.out.println(matrix[2][1]);  // 8 (row 2, col 1)
```

### Iterating 2D Arrays
```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Nested loops
for (int i = 0; i < matrix.length; i++) {        // Rows
    for (int j = 0; j < matrix[i].length; j++) { // Columns
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
// Output:
// 1 2 3
// 4 5 6
// 7 8 9
```

### Enhanced for Loop with 2D Arrays
```java
for (int[] row : matrix) {
    for (int element : row) {
        System.out.print(element + " ");
    }
    System.out.println();
}
```

## Common Array Operations

### Finding Maximum
```java
int[] numbers = {5, 2, 8, 1, 9};
int max = numbers[0];

for (int i = 1; i < numbers.length; i++) {
    if (numbers[i] > max) {
        max = numbers[i];
    }
}

System.out.println("Maximum: " + max);  // 9
```

### Finding Minimum
```java
int[] numbers = {5, 2, 8, 1, 9};
int min = numbers[0];

for (int i = 1; i < numbers.length; i++) {
    if (numbers[i] < min) {
        min = numbers[i];
    }
}

System.out.println("Minimum: " + min);  // 1
```

### Calculating Sum
```java
int[] numbers = {1, 2, 3, 4, 5};
int sum = 0;

for (int num : numbers) {
    sum += num;
}

System.out.println("Sum: " + sum);  // 15
```

### Calculating Average
```java
int[] numbers = {80, 85, 90, 75, 95};
int sum = 0;

for (int num : numbers) {
    sum += num;
}

double average = (double) sum / numbers.length;
System.out.println("Average: " + average);  // 85.0
```

### Searching for Element
```java
int[] numbers = {10, 20, 30, 40, 50};
int target = 30;
boolean found = false;

for (int i = 0; i < numbers.length; i++) {
    if (numbers[i] == target) {
        System.out.println("Found at index: " + i);
        found = true;
        break;
    }
}

if (!found) {
    System.out.println("Not found");
}
```

### Reversing Array
```java
int[] arr = {1, 2, 3, 4, 5};

for (int i = 0; i < arr.length / 2; i++) {
    int temp = arr[i];
    arr[i] = arr[arr.length - 1 - i];
    arr[arr.length - 1 - i] = temp;
}

// arr is now {5, 4, 3, 2, 1}
```

## Copying Arrays

### Shallow Copy (Reference)
```java
int[] original = {1, 2, 3};
int[] copy = original;  // Both point to same array!
copy[0] = 100;
System.out.println(original[0]);  // 100 (changed!)
```

### Deep Copy (Actual Copy)
```java
int[] original = {1, 2, 3};
int[] copy = new int[original.length];

for (int i = 0; i < original.length; i++) {
    copy[i] = original[i];
}

copy[0] = 100;
System.out.println(original[0]);  // 1 (unchanged)
```

### Using `clone()`
```java
int[] original = {1, 2, 3};
int[] copy = original.clone();  // Creates a copy
```

## Common Mistakes

 **ArrayIndexOutOfBoundsException**
```java
int[] arr = {1, 2, 3};
System.out.println(arr[3]);  // Error! Index 3 doesn't exist
```

 **Forgetting array size**
```java
int[] arr = new int[5];
arr[5] = 100;  // Error! Valid indices: 0-4
```

## Key Takeaways

1. Arrays store multiple values of same type
2. Zero-based indexing (first element is at index 0)
3. Fixed size (can't change after creation)
4. Single-dimensional: one row of data
5. Multi-dimensional: arrays of arrays (matrices)
6. Always check bounds (0 to length-1)
7. Use enhanced for loop for simple iteration

## Practice

1. Create array of 10 student marks, find average
2. Find largest and smallest in array
3. Reverse an array
4. Create 3x3 matrix, print it nicely
5. Search for element in array, print its index
