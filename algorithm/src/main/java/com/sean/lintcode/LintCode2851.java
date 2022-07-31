package com.sean.lintcode;

public class LintCode2851 {

    public static class Animal {

        private String name;
        private String type;
        private int age;

        public void setAnimalMessage(String name, String type, int age) {
            this.name = name;
            this.type = type;
            this.age = age;
        }

        public void printAnimalMessage() {
            System.out.println(name + " is a " + type + " and is " + age + " years old this year.");
        }
    }

    public static void main(String[] args) {
        try {
            String name = args[0];
            String types = args[1];
            String str = args[2];

            int age = Integer.parseInt(str);
            Animal solution = new Animal();
            solution.setAnimalMessage(name, types, age);
            solution.printAnimalMessage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
