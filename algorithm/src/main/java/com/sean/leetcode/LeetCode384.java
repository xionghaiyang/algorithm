package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 打乱数组
 * https://leetcode-cn.com/problems/shuffle-an-array/
 */
public class LeetCode384 {

    private static class Solution1 {

        private int[] array;
        private int[] original;
        private Random rand = new Random();

        private List<Integer> getArrayCopy() {
            List<Integer> asList = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                asList.add(array[i]);
            }
            return asList;
        }

        public Solution1(int[] nums) {
            array = nums;
            original = nums.clone();
        }

        public int[] reset() {
            array = original;
            original = original.clone();
            return array;
        }

        public int[] shuffle() {
            List<Integer> arrayCopy = getArrayCopy();
            for (int i = 0; i < array.length; i++) {
                int removeIndex = rand.nextInt(arrayCopy.size());
                array[i] = arrayCopy.get(removeIndex);
                arrayCopy.remove(removeIndex);
            }
            return array;
        }
    }

    class Solution {

        private int[] array;
        private int[] original;

        Random rand = new Random();

        private int randRange(int min, int max) {
            return rand.nextInt(max - min) + min;
        }

        private void swapAt(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        public Solution(int[] nums) {
            array = nums;
            original = nums.clone();
        }

        public int[] reset() {
            array = original;
            original = original.clone();
            return original;
        }

        public int[] shuffle() {
            for (int i = 0; i < array.length; i++) {
                swapAt(i, randRange(i, array.length));
            }
            return array;
        }
    }
}
