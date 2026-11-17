# JDK, JRE, and JVM Architecture

## What's the Difference?

Think of it like building and running a house:

- **JDK (Java Development Kit)**: Your toolbox + construction manual. Everything you need to BUILD Java programs.
- **JRE (Java Runtime Environment)**: The finished house + utilities. Everything you need to RUN Java programs.
- **JVM (Java Virtual Machine)**: The engine inside the house that actually makes everything work.

## JDK - Java Development Kit

**Contains:**
- JRE (so you can run programs)
- Development tools (javac compiler, java debugger, etc.)
- Libraries and APIs
- Documentation tools

**You need JDK when:** You want to write and compile Java code.

## JRE - Java Runtime Environment

**Contains:**
- JVM
- Core libraries (java.lang, java.util, etc.)
- Supporting files

**You need JRE when:** You only want to run existing Java programs.

## JVM - Java Virtual Machine

**What it does:**
1. Loads `.class` files (bytecode)
2. Verifies bytecode for security
3. Executes bytecode on your operating system
4. Manages memory (heap, stack)

**Why it matters:**
- Write Once, Run Anywhere (WORA)
- Java bytecode runs on any machine with JVM
- Platform independence!

## The Flow

```
.java file (source code)
    v [javac compiler]
.class file (bytecode)
    v [JVM]
Machine code (runs on your OS)
```

## Quick Comparison

| Feature | JDK | JRE | JVM |
|---------|-----|-----|-----|
| Development | Yes | No | No |
| Running Java | Yes | Yes | Yes |
| Compiler included | Yes | No | No |
| Platform specific | Yes | Yes | Yes |
| Purpose | Develop & Run | Run only | Execute bytecode |

## Key Takeaways

- JDK = JRE + Development Tools
- JRE = JVM + Libraries
- JVM is the engine that runs Java bytecode
- You need JDK to develop, JRE to just run
- JVM makes Java platform-independent

## Pro Tip

When installing Java, always download JDK (not just JRE) because:
- It includes JRE anyway
- You'll need the compiler for development
- Modern IDEs require JDK
