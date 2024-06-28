package com.sean.learning02.class06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class Code01_Comparator {

    public static class AComp implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

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
            return "Student{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", age=" + age +
                    '}';
        }
    }

    public static class IdShengAgeJiangOrder implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.id != o2.id ? (o1.id - o2.id) : (o2.age - o1.age);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 44, 3, 2, 7, 9, 1, 10};
        Arrays.sort(arr, new AComp());
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
        System.out.println("=======================");
        Student student1 = new Student("A", 4, 40);
        Student student2 = new Student("B", 4, 21);
        Student student3 = new Student("C", 3, 12);
        Student student4 = new Student("D", 3, 62);
        Student student5 = new Student("E", 3, 42);
        Student[] students = {student1, student2, student3, student4, student5};
        System.out.println("第一条打印");
        Arrays.sort(students, new IdShengAgeJiangOrder());
        for (int i = 0; i < students.length; i++) {
            Student s = students[i];
            System.out.println(s);
        }
        System.out.println("第二条打印");
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.sort(new IdShengAgeJiangOrder());
        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            System.out.println(s);
        }
        System.out.println("第三条打印");
        student1 = new Student("A", 4, 40);
        student2 = new Student("B", 1, 21);
        student3 = new Student("C", 2, 12);
        student4 = new Student("D", 5, 62);
        student5 = new Student("E", 3, 42);
        TreeMap<Student, String> treeMap = new TreeMap<>((a, b) -> (a.id - b.id));
        treeMap.put(student1, "我是学生1，我的名字叫A");
        treeMap.put(student2, "我是学生2，我的名字叫B");
        treeMap.put(student3, "我是学生3，我的名字叫C");
        treeMap.put(student4, "我是学生4，我的名字叫D");
        treeMap.put(student5, "我是学生5，我的名字叫E");
        for (Student s : treeMap.keySet()) {
            System.out.println(s);
        }
    }
}
