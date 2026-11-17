# Concurrency Utilities

## What are Concurrency Utilities?

Java provides powerful utilities in `java.util.concurrent` package for **thread-safe** and **efficient** concurrent programming.

Think of it as a **toolbox** with ready-to-use thread-safe components.

## Why Use Them?

1. **Thread-safe**: No need to write synchronization
2. **Efficient**: Optimized for performance
3. **High-level**: Easier than raw threads
4. **Scalable**: Handle large concurrent operations

## 1. Executor Framework

Manages thread pools - reuses threads instead of creating new ones.

### Creating Thread Pool
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void main(String[] args) {
        // Create thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Submit tasks
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() → {
                System.out.println("Task " + taskId + " by " + Thread.currentThread().getName());
            });
        }
        
        // Shutdown executor
        executor.shutdown();
    }
}
```

### Types of Thread Pools

| Method | Description |
|--------|-------------|
| `newFixedThreadPool(n)` | Fixed number of threads |
| `newCachedThreadPool()` | Creates threads as needed |
| `newSingleThreadExecutor()` | Single thread |
| `newScheduledThreadPool(n)` | Scheduled tasks |

### Complete Example
```java
import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.submit(() → {
                System.out.println("Task " + taskId + " running in " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " completed");
            });
        }
        
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        
        System.out.println("All tasks completed");
    }
}
```

## 2. Callable and Future

`Callable` returns result (unlike Runnable which returns void). `Future` holds result.

```java
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        // Callable task that returns result
        Callable<Integer> task = () → {
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            return sum;
        };
        
        // Submit task, get Future
        Future<Integer> future = executor.submit(task);
        
        // Do other work
        System.out.println("Doing other work...");
        
        // Get result (blocks until ready)
        Integer result = future.get();
        System.out.println("Sum: " + result);  // 5050
        
        executor.shutdown();
    }
}
```

## 3. Concurrent Collections

Thread-safe versions of standard collections.

### ConcurrentHashMap
```java
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        
        map.put("Alice", 95);
        map.put("Bob", 87);
        
        // Thread-safe operations
        map.computeIfPresent("Alice", (key, value) → value + 5);
        System.out.println(map.get("Alice"));  // 100
    }
}
```

### CopyOnWriteArrayList
```java
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        
        list.add("Alice");
        list.add("Bob");
        
        // Iteration is thread-safe (snapshot)
        for (String name : list) {
            System.out.println(name);
        }
    }
}
```

## 4. CountDownLatch

Wait for multiple threads to complete.

```java
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        
        Thread t1 = new Thread(() → {
            System.out.println("Task 1 completed");
            latch.countDown();
        });
        
        Thread t2 = new Thread(() → {
            System.out.println("Task 2 completed");
            latch.countDown();
        });
        
        Thread t3 = new Thread(() → {
            System.out.println("Task 3 completed");
            latch.countDown();
        });
        
        t1.start();
        t2.start();
        t3.start();
        
        // Wait for all tasks
        latch.await();
        System.out.println("All tasks completed!");
    }
}
```

## 5. CyclicBarrier

Wait for all threads to reach a barrier point.

```java
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () → {
            System.out.println("All parties arrived, proceeding...");
        });
        
        for (int i = 1; i <= 3; i++) {
            new Thread(() → {
                try {
                    System.out.println("Thread " + Thread.currentThread().getId() + " arrived");
                    barrier.await();  // Wait for others
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
```

## 6. Semaphore

Control access to resource (permits).

```java
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);  // 2 permits
        
        for (int i = 1; i <= 5; i++) {
            final int id = i;
            new Thread(() → {
                try {
                    semaphore.acquire();  // Acquire permit
                    System.out.println("Thread " + id + " accessing resource");
                    Thread.sleep(1000);
                    semaphore.release();  // Release permit
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}
```

## 7. BlockingQueue

Thread-safe queue for producer-consumer pattern.

```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);
        
        // Producer
        new Thread(() → {
            try {
                for (int i = 1; i <= 10; i++) {
                    queue.put(i);  // Blocks if queue is full
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        
        // Consumer
        new Thread(() → {
            try {
                for (int i = 1; i <= 10; i++) {
                    Integer item = queue.take();  // Blocks if queue is empty
                    System.out.println("Consumed: " + item);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
```

## 8. Atomic Variables

Thread-safe operations without synchronization.

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) throws Exception {
        AtomicInteger counter = new AtomicInteger(0);
        
        ExecutorService executor = Executors.newFixedThreadPool(10);
        
        for (int i = 0; i < 1000; i++) {
            executor.submit(() → counter.incrementAndGet());
        }
        
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        
        System.out.println("Final count: " + counter.get());  // 1000
    }
}
```

## Key Takeaways

1. **ExecutorService**: Thread pool management
2. **Callable/Future**: Return results from threads
3. **ConcurrentHashMap**: Thread-safe map
4. **CountDownLatch**: Wait for multiple threads
5. **Semaphore**: Control access (permits)
6. **BlockingQueue**: Producer-consumer pattern
7. **Atomic variables**: Lock-free thread safety

## Practice

1. Create thread pool, submit 5 tasks
2. Use Callable to compute sum of numbers
3. Implement producer-consumer with BlockingQueue
4. Use CountDownLatch to wait for multiple threads
5. Use AtomicInteger for thread-safe counter
