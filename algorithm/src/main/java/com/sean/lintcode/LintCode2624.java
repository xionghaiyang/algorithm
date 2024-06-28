package com.sean.lintcode;

public class LintCode2624 {

    public static class Animal {
        public void eat() {
            System.out.println("The animals have finished eating!");
        }
    }

    public static class Bird extends Animal {
        public void eat() {
            System.out.println("Birds eat worms");
        }

        public void sleep() {
            System.out.println("The animals are asleep!");
        }
    }

    public static void main(String[] args) {
        Animal animal = new Bird();
        animal.eat();
    }

}
