package com.sean.leetcode;

public class LeetCodeOfferII041 {

    class MovingAverage {

        int[] array;
        int capacity;
        int cur;
        int sum;
        int size;

        public MovingAverage(int size) {
            this.capacity = size;
            this.array = new int[size];
            this.cur = 0;
            this.sum = 0;
            this.size = 0;
        }

        public double next(int val) {
            sum += val - array[cur];
            array[cur] = val;
            cur = (cur + 1) % capacity;
            size++;
            return (double) sum / Math.min(size, capacity);
        }

    }

}
