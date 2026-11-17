class Student {
    private String name;
    private int rollNumber;
    private double marks;

    // Constructor
    public Student(String name, int rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void display() {
        System.out.println("Name: " + name + ", Roll: " + rollNumber + ", Marks: " + marks);
    }
}

public class ClassesObjects {
    public static void main(String[] args) {
        Student s1 = new Student("Alice", 101, 95.5);
        Student s2 = new Student("Bob", 102, 87.3);

        s1.display();
        s2.display();
    }
}
