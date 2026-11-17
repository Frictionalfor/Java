# IDE Setup

## What is an IDE?

**IDE = Integrated Development Environment**

A fancy text editor + compiler + debugger + helper tools all in one package. Makes writing code easier and faster.

## Popular Java IDEs

### 1. IntelliJ IDEA (Most Recommended)

**Why students love it:**
- Smart code completion
- Excellent error detection
- Built-in tools for everything
- Great debugging

**Versions:**
- Community Edition: FREE, perfect for learning
- Ultimate Edition: Paid, for professional development

**Download:** https://www.jetbrains.com/idea/

### 2. Eclipse

**Why students use it:**
- Completely FREE
- Highly customizable
- Many plugins available
- Industry standard in some companies

**Download:** https://www.eclipse.org/

### 3. VS Code (Visual Studio Code)

**Why students love it:**
- Lightweight and fast
- FREE
- Great for multiple languages
- Growing Java support

**Download:** https://code.visualstudio.com/

## Setting Up IntelliJ IDEA (Recommended for Beginners)

### Step 1: Download and Install
1. Go to jetbrains.com/idea
2. Download Community Edition (FREE)
3. Install like any other software

### Step 2: Create Your First Project
1. Open IntelliJ
2. Click "New Project"
3. Select "Java" from the left menu
4. Choose your JDK version (e.g., Java 17)
5. Click "Create"

### Step 3: Write Your First Program
1. Right-click `src` folder → New → Java Class
2. Name it: `HelloWorld`
3. Type this code:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

4. Right-click in the editor → Run 'HelloWorld.main()'
5. See output in the bottom console!

## Setting Up VS Code

### Step 1: Install VS Code
1. Download from code.visualstudio.com
2. Install it

### Step 2: Install Java Extension Pack
1. Open VS Code
2. Go to Extensions (Ctrl+Shift+X)
3. Search for "Extension Pack for Java"
4. Click Install

### Step 3: Create and Run
1. Create a new file: `HelloWorld.java`
2. Write your code
3. Click the Run button () that appears

## Important: Install JDK First!

**Before setting up any IDE, make sure you have JDK installed:**

**Windows:**
1. Download JDK from Oracle or OpenJDK
2. Install it
3. Set environment variables

**Mac:**
```bash
brew install openjdk@17
```

**Linux:**
```bash
sudo apt install openjdk-17-jdk
```

## Verify Installation

Open terminal/command prompt and type:
```bash
java -version
javac -version
```

If you see version numbers, you're good to go!

## IDE Shortcuts (Time Savers!)

### IntelliJ IDEA
- `Ctrl + Space`: Auto-complete
- `Ctrl + B`: Go to definition
- `Ctrl + Alt + L`: Format code
- `Alt + Enter`: Quick fix error
- `Shift + Shift`: Search everywhere
- `Ctrl + Shift + F`: Find in files
- `Ctrl + R`: Replace

### VS Code
- `Ctrl + Space`: Auto-complete
- `Ctrl + B`: Go to definition
- `Shift + Alt + F`: Format code
- `Ctrl + P`: Quick file search
- `Ctrl + Shift + F`: Find in files

## My Recommendation

**For beginners:** Start with IntelliJ IDEA Community Edition
- Most beginner-friendly
- Best error messages
- Lots of learning resources online

**For advanced users:** VS Code or Eclipse
- More lightweight
- More customizable
- Better for web development

## Common Issues and Fixes

**Issue:** "JDK not found"
**Fix:** Configure JDK path in IDE settings

**Issue:** "Cannot resolve symbol"
**Fix:** Make sure you imported the right packages

**Issue:** Program runs but no output
**Fix:** Check if you're looking at the right console tab

## Pro Tips

1. Learn keyboard shortcuts - they save hours!
2. Use code formatting (auto-indent)
3. Enable auto-save
4. Install useful plugins (CheckStyle, SonarLint for code quality)
5. Learn to use the debugger (it's a superpower!)

## Next Steps

Once your IDE is set up:
1. Create a test project
2. Write a simple "Hello World" program
3. Run it successfully
4. Try debugging (set a breakpoint and step through code)

You're now ready to start learning Java properly!
