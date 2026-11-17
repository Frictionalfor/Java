// Package: com.student
package com.student;

public class Student {
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}
