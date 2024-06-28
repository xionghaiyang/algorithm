package com.sean.lintcode;

public class LintCode2848 {

    public static class Solution {
        public String name;
        public int age;

        public void show() {
            System.out.println("Name: " + name + ", age: " + age);
        }
    }

    public static void main(String[] args) {
        Solution s1 = new Solution();
        s1.name = "Lemon";
        s1.age = 20;
        s1.show();
    }
}
