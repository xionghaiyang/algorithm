package com.sean.course02.lesson06;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-05-01 20:57
 * @Description 比较器
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
            return name + "," + id + "," + age;
        }
    }

    public static class IdShengAgeJiangOrder implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.id != o2.id ? (o1.id - o2.id) : (o2.age - o1.age);
        }
    }

    public static class AComp implements Comparator<Integer> {
        //如果返回负数，认为第一个参数应该排在前面
        //如果返回正数，认为第二个参数应该排在前面
        //如果返回0，认为谁放前面都行
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 4, 3, 2, 7, 9, 10};
        Arrays.sort(arr, new AComp());
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("===============");
        Student student1 = new Student("A", 4, 40);
        Student student2 = new Student("B", 4, 21);
        Student student3 = new Student("C", 3, 12);
        Student student4 = new Student("D", 3, 62);
        Student student5 = new Student("E", 3, 42);
        Student[] students = {student1, student2, student3, student4, student5};
        Arrays.sort(students, new IdShengAgeJiangOrder());
        System.out.println("第一条打印");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("第二条打印");
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.sort(new IdShengAgeJiangOrder());
        for (Student student : studentList) {
            System.out.println(student);
        }
        //N * logN
        System.out.println("第三条打印");
        student1 = new Student("A", 4, 40);
        student2 = new Student("B", 4, 21);
        student3 = new Student("C", 4, 12);
        student4 = new Student("D", 4, 62);
        student5 = new Student("E", 4, 42);
        TreeMap<Student, String> treeMap = new TreeMap<>((a, b) -> a.id - b.id);
        treeMap.put(student1, "我是学生1，我的名字叫A");
        treeMap.put(student2, "我是学生2，我的名字叫B");
        treeMap.put(student3, "我是学生3，我的名字叫C");
        treeMap.put(student4, "我是学生4，我的名字叫D");
        treeMap.put(student5, "我是学生5，我的名字叫E");
        for (Student student : treeMap.keySet()) {
            System.out.println(student);
        }
    }

}
