package com.sean.course03.lesson01;

import java.util.Stack;

/**
 * @Author xionghaiyang
 * @Date 2025-04-01 20:49
 * @Description 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 */
public class Code04_AllTimesMinToMax {

    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static int max2(int[] arr) {
        int n = arr.length;
        int[] preSum = new int[n];
        preSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        //小 -> 大
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? preSum[i - 1] : (preSum[i - 1] - preSum[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? preSum[n - 1] : (preSum[n - 1] - preSum[stack.peek()])) * arr[j]);
        }
        return max;
    }

    private static int[] gerenareRondomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (maxSize * Math.random()) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 20000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray(maxSize, maxValue);
            if (max1(arr) != max2(arr)) {
                System.out.println("ERROR!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
