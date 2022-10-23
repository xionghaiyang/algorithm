package com.sean.base.chapter06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-19 19:37
 * @Description: 比较器
 * 1)比较器的实质就是重载比较运算符
 * 2)比较器可以很好的应用在特殊标准的排序上
 * 3)比较器可以很好的应用在根据特殊标准排序的结构上
 * 4)写代码变得异常容易，还用于范型编程
 */
public class Code01_Comparator {

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

    public static class AComp implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }

    }

    public static class IdShengAgeJiangOrder implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.id != o2.id ? (o1.id - o2.id) : (o2.age - o2.age);
        }

    }

    public static void main(String[] args) {
        Integer[] arr = {5, 4, 3, 2, 7, 9, 1, 0};
        Arrays.sort(arr, new AComp());
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("============================");
        Student student1 = new Student("A", 4, 40);
        Student student2 = new Student("B", 4, 21);
        Student student3 = new Student("C", 3, 12);
        Student student4 = new Student("D", 3, 62);
        Student student5 = new Student("E", 3, 42);
        Student[] students = {student1, student2, student3, student4, student5};
        Arrays.sort(students, new IdShengAgeJiangOrder());
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("===============================");
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.sort(new IdShengAgeJiangOrder());
        for (Student student : studentList) {
            System.out.println(student);
        }
        System.out.println("============================");
        TreeMap<Student, String> treeMap = new TreeMap<>((a, b) -> a.id != b.id ? a.id - b.id : a.age - b.age);
        treeMap.put(new Student("A", 4, 40), "我是学生1，我的名字叫A");
        treeMap.put(new Student("B", 4, 21), "我是学生2，我的名字叫B");
        treeMap.put(new Student("C", 4, 12), "我是学生3，我的名字叫C");
        treeMap.put(new Student("D", 4, 62), "我是学生4，我的名字叫D");
        treeMap.put(new Student("E", 4, 42), "我是学生5，我的名字叫E");
        for (Student student : treeMap.keySet()) {
            System.out.println(student);
        }
    }

}
