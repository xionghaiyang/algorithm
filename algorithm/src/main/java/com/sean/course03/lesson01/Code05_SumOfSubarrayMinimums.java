package com.sean.course03.lesson01;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author xionghaiyang
 * @Date 2025-04-01 22:34
 * @Description https://leetcode.cn/problems/sum-of-subarray-minimums
 * 907. 子数组的最小值之和
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 3 * 10^4
 */
public class Code05_SumOfSubarrayMinimums {

    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int res = 0;
        int mod = 1_000_000_007;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int min = arr[i];
                for (int k = i + 1; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                }
                res += min;
                res %= mod;
            }
        }
        return res;
    }

    public static int sumSubarrayMins1(int[] arr) {
        int[] left = leftNearLessEqual1(arr);
        int[] right = rightNearLess1(arr);
        int n = arr.length;
        long res = 0;
        int mod = 1_000_000_007;
        for (int i = 0; i < n; i++) {
            int start = i - left[i];
            int end = right[i] - i;
            res += (long) start * (long) end * (long) arr[i];
            res %= mod;
        }
        return (int) res;
    }

    private static int[] leftNearLessEqual1(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            int res = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i]) {
                    res = j;
                    break;
                }
            }
            left[i] = res;
        }
        return left;
    }

    private static int[] rightNearLess1(int[] arr) {
        int n = arr.length;
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            int res = n;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    res = j;
                    break;
                }
            }
            right[i] = res;
        }
        return right;
    }

    public static int sumSubarrayMins2(int[] arr) {
        int[] left = nearLessEqualLeft(arr);
        int[] right = nearLessRight(arr);
        long res = 0;
        int n = arr.length;
        int mod = 1_000_000_007;
        for (int i = 0; i < n; i++) {
            long start = i - left[i];
            long end = right[i] - i;
            res += start * end * (long) arr[i];
            res %= mod;
        }
        return (int) res;
    }

    private static int[] nearLessEqualLeft(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            left[stack.pop()] = -1;
        }
        return left;
    }

    private static int[] nearLessRight(int[] arr) {
        int n = arr.length;
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            right[stack.pop()] = n;
        }
        return right;
    }

    private static int[] randomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 50;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxSize, maxValue);
            int res1 = sumSubarrayMins(arr);
            int res2 = sumSubarrayMins1(arr);
            int res3 = sumSubarrayMins2(arr);
            if (res1 != res2 || res1 != res3) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
