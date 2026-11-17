# Advanced Java Foundations Notes

## Java Collections Framework

- **List**: Ordered collection (ArrayList, LinkedList)
- **Set**: Unordered collection, no duplicates (HashSet, TreeSet)
- **Map**: Key-value pairs (HashMap, TreeMap)
- **Queue**: FIFO (LinkedList, PriorityQueue)

## Generics

- Type safety at compile time
- Bounded type parameters: `<T extends Number>`
- Wildcards: `?`, `<? extends T>`, `<? super T>`

## Lambda Expressions

- Concise way to represent functions
- Syntax: `(parameters) → expression`
- Enables functional programming

## Stream API

- Process collections declaratively
- Operations: filter, map, reduce, collect
- Lazy evaluation, parallel processing

## Built-in Functional Interfaces

- **Predicate<T>**: Takes T, returns boolean
- **Consumer<T>**: Takes T, returns void
- **Supplier<T>**: Takes nothing, returns T
- **Function<T, R>**: Takes T, returns R

## Optional Class

- Container for nullable values
- Prevents NullPointerException
- Methods: `orElse()`, `ifPresent()`, `isPresent()`

## File I/O

- **java.io**: Traditional I/O (File, FileReader, BufferedReader)
- **java.nio**: Modern I/O (Path, Files, ByteBuffer)

## Multithreading

- **Thread class**: Extend Thread, override run()
- **Runnable interface**: Implement run(), pass to Thread
- Thread lifecycle: New, Runnable, Blocked, Waiting, Timed Waiting, Terminated

## Concurrency Utilities

- **ExecutorService**: Thread pool management
- **ThreadPoolExecutor**: Configurable thread pool
- **synchronized**: Mutual exclusion
- **Lock**: Explicit locking mechanism
