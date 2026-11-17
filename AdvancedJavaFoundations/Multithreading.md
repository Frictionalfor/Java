# Multithreading

## What is Multithreading?

Multithreading allows your program to execute **multiple tasks simultaneously**.

Real-world analogy:
- You cooking (single-threaded): Chop vegetables → Cook → Serve
- Multiple chefs (multithreaded): One chops, one cooks, one serves at same time!

## Thread vs Process

| Process | Thread |
|---------|--------|
| Independent execution unit | Smallest unit of execution |
| Has its own memory | Shares process memory |
| Heavyweight | Lightweight |
| Inter-process communication is hard | Threads communicate easily |

## Thread Lifecycle

```
New → Runnable → Running → Blocked/Waiting → Terminated
```

1. **New**: Thread created but not started
2. **Runnable**: Ready to run, waiting for CPU
3. **Running**: Currently executing
4. **Blocked/Waiting**: Waiting for resource
5. **Terminated**: Execution complete

## Creating Threads

### Method 1: Extend Thread Class

```java
class MyThread extends Thread {
    @Override
    public void run() {
        // Code to execute in thread
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread: " + i);
            try {
                Thread.sleep(500);  // Pause 500ms
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();  // Start thread (calls run() method)
    }
}
```

**Important:** Call `start()`, not `run()`!

```java
thread.start();  //  Starts new thread
thread.run();    //  Just calls method, no new thread!
```

### Method 2: Implement Runnable Interface (Preferred)

```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Runnable: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class RunnableDemo {
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
```

**Why Runnable is better:**
- Java doesn't support multiple inheritance
- Can extend another class AND implement Runnable
- More flexible

### Method 3: Lambda Expression (Java 8+)

```java
public class LambdaThreadDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() → {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lambda Thread: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        thread.start();
    }
}
```

## Thread Methods

### `start()`
Starts thread, calls `run()` in new thread.

### `run()`
Contains code to execute.

### `sleep(milliseconds)`
Pauses thread for specified time.

```java
Thread.sleep(1000);  // Sleep 1 second
```

### `join()`
Wait for thread to finish.

```java
Thread t1 = new Thread(() → {
    System.out.println("Thread 1 running");
});
t1.start();
t1.join();  // Wait for t1 to finish before continuing
System.out.println("Thread 1 finished");
```

### `currentThread()`
Get reference to currently executing thread.

```java
Thread t = Thread.currentThread();
System.out.println(t.getName());  // Thread name
```

### `setName()` / `getName()`
```java
Thread t = new Thread(runnable);
t.setName("Worker Thread");
System.out.println(t.getName());  // Worker Thread
```

## Thread Priorities

Threads have priorities (1-10, default 5).

```java
Thread t1 = new Thread(runnable);
Thread t2 = new Thread(runnable);

t1.setPriority(Thread.MIN_PRIORITY);  // 1
t2.setPriority(Thread.MAX_PRIORITY);  // 10

t1.start();
t2.start();
```

**Note:** Priority is just a hint to OS scheduler.

## Multiple Threads Example

```java
public class MultiThreadDemo {
    public static void main(String[] args) {
        // Thread 1
        Thread t1 = new Thread(() → {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread 1: " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Thread 2
        Thread t2 = new Thread(() → {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread 2: " + i);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        t1.start();
        t2.start();
        
        // Main thread
        for (int i = 1; i <= 5; i++) {
            System.out.println("Main: " + i);
        }
    }
}
```

## Thread Synchronization

When multiple threads access same resource, problems can occur.

### Race Condition Example
```java
class Counter {
    int count = 0;
    
    void increment() {
        count++;  // Not thread-safe!
    }
}
```

Two threads incrementing simultaneously can cause issues!

### Synchronized Method
```java
class Counter {
    int count = 0;
    
    synchronized void increment() {  // Thread-safe
        count++;
    }
}
```

### Synchronized Block
```java
class Counter {
    int count = 0;
    Object lock = new Object();
    
    void increment() {
        synchronized (lock) {  // Only synchronized part
            count++;
        }
        // Other code not synchronized
    }
}
```

## Inter-Thread Communication

### wait(), notify(), notifyAll()

```java
class SharedResource {
    synchronized void produce() throws InterruptedException {
        while (isAvailable) {
            wait();  // Wait until consumed
        }
        // Produce item
        isAvailable = true;
        notify();  // Notify consumer
    }
    
    synchronized void consume() throws InterruptedException {
        while (!isAvailable) {
            wait();  // Wait until produced
        }
        // Consume item
        isAvailable = false;
        notify();  // Notify producer
    }
}
```

## Daemon Threads

Background threads that don't prevent JVM from exiting.

```java
Thread daemon = new Thread(() → {
    while (true) {
        System.out.println("Background task...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            break;
        }
    }
});

daemon.setDaemon(true);  // Set as daemon
daemon.start();
```

## Thread Safety Issues

1. **Race Condition**: Multiple threads modify shared data
2. **Deadlock**: Threads waiting for each other
3. **Starvation**: Thread never gets CPU time

## Key Takeaways

1. Thread = lightweight subprocess
2. Two ways: extend Thread or implement Runnable
3. Always call `start()`, not `run()`
4. Use `sleep()` to pause, `join()` to wait
5. Use `synchronized` for thread safety
6. Daemon threads run in background

## Practice

1. Create two threads printing different messages
2. Create thread that counts from 1 to 10 with delay
3. Implement producer-consumer with wait/notify
4. Create daemon thread for background task
5. Demonstrate race condition and fix with synchronized
