package com.sean.lintcode;

public class LintCode2231 {

    public abstract class Shape {
        public abstract double area();
    }

    public class Circle extends Shape {
        public int radius;

        public Circle(int radius) {
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    public class Square extends Shape {
        public int sideLength;

        public Square(int sideLength) {
            this.sideLength = sideLength;
        }

        @Override
        public double area() {
            return sideLength * sideLength;
        }
    }

}
