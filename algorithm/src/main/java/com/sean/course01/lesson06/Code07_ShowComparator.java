package com.sean.course01.lesson06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-03-24 22:12
 * @Description 比较器
 */
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

    public static class IdComparator implements Comparator<Student> {
        //如果返回负数，认为第一个参数应该排在前面
        //如果返回正数，认为第二个参数应该排在前面
        //如果返回0，认为谁放前面无所谓
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.id < o2.id) {
                return 1;
            } else if (o2.id < o1.id) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static class AgeComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o2.age - o1.age;
        }
    }

    public static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
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

    public static void main(String[] args) {
        int[] arr = {8, 1, 4, 1, 6, 8, 4, 1, 5, 8, 2, 3, 0};
        printArray(arr);
        Arrays.sort(arr);
        printArray(arr);
        System.out.println("===============================");

        Student s1 = new Student("张三", 4, 27);
        Student s2 = new Student("李四", 2, 28);
        Student s3 = new Student("王五", 3, 29);
        Student s4 = new Student("赵六", 1, 30);
        Student s5 = new Student("田七", 5, 31);
        Student[] students = {s1, s2, s3, s4, s5};
        printStudents(students);
        System.out.println("===============================");
        Arrays.sort(students, new IdComparator());
        printStudents(students);
        System.out.println("===============================");
        Arrays.sort(students, new AgeComparator());
        printStudents(students);
        System.out.println("===============================");

        PriorityQueue<Student> heap = new PriorityQueue<>((a, b) -> a.id - b.id);
        heap.add(s1);
        heap.add(s3);
        heap.add(s2);
        heap.add(s4);
        heap.add(s5);
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
        System.out.println("============================");

        ArrayList<Student> arrList = new ArrayList<>();
        arrList.add(s1);
        arrList.add(s2);
        arrList.add(s3);
        arrList.add(s4);
        arrList.add(s5);
        for (Student student : arrList) {
            System.out.println(student);
        }
        System.out.println("===================");
        arrList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.age - o1.age;
            }
        });
        for (Student student : arrList) {
            System.out.println(student);
        }
        System.out.println("===================");
    }

}
