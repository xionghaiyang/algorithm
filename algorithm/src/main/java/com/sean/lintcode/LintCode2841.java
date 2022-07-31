package com.sean.lintcode;

public class LintCode2841 {

    static class Parent {
        private String str1 = "Please connect the charger !!!";

        public void getName() {
            System.out.println(str1);
        }
    }

    static class Child extends Parent {
        private String str2 = "Insufficient power";

        // @Override
        public void getName() {
            //Write your code here
            System.out.println(str2);
            super.getName();
        }
    }

    public static void main(String[] args) {
        Child c = new Child();
        c.getName();
    }


}
