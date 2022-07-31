package com.sean.learning01.class06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code07_ShowComparator {

    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + "," + id + "," + age;
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printStudents(Student[] students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static class AgeComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 1, 4, 1, 6, 8, 4, 1, 5, 8, 2, 3, 0};
        printArray(arr);
        Arrays.sort(arr);
        printArray(arr);
        Arrays.sort(arr);
        System.out.println("===========================");

        Student s1 = new Student("张三", 4, 27);
        Student s2 = new Student("李四", 2, 28);
        Student s3 = new Student("王五", 3, 29);
        Student s4 = new Student("赵六", 1, 30);
        Student s5 = new Student("田七", 5, 31);
        Student[] students = {s1, s2, s3, s4, s5};
        printStudents(students);
        System.out.println("====================");

        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.id - o2.id;
            }
        });
        printStudents(students);
        System.out.println("====================");

        Arrays.sort(students, new AgeComparator());
        printStudents(students);
        System.out.println("====================");

        PriorityQueue<Student> heap = new PriorityQueue<Student>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.id - o2.id;
            }
        });
        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

    }

}
