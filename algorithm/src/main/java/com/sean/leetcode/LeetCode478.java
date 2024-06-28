package com.sean.leetcode;

import java.util.Random;

public class LeetCode478 {

    class Solution {

        private Random random;
        private double radius;
        private double x_center;
        private double y_center;

        public Solution(double radius, double x_center, double y_center) {
            this.random = new Random();
            this.radius = radius;
            this.x_center = x_center;
            this.y_center = y_center;
        }

        public double[] randPoint() {
            while (true) {
                double x = 2 * radius * random.nextDouble() - radius;
                double y = 2 * radius * random.nextDouble() - radius;
                if (x * x + y * y <= radius * radius) {
                    return new double[]{x_center + x, y_center + y};
                }
            }
        }
    }

}
