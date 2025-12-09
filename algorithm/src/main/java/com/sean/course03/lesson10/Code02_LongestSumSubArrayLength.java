package com.sean.course03.lesson10;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-12-09 17:59
 * @Description 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 * 给定一个整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
 * 返回其长度
 */
public class Code02_LongestSumSubArrayLength {

    public static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = 0, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                res = Math.max(res, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return res;
    }

    public static int right(int[] arr, int k) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, k)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    private static boolean valid(int[] arr, int left, int right, int k) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += arr[i];
        }
        return sum == k;
    }

    private static int[] generateRandomArray(int size, int value) {
        int[] res = new int[(int) (size * Math.random()) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (value * Math.random()) - (int) (value * Math.random());
        }
        return res;
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 50;
        int value = 100;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(size, value);
            int k = (int) (value * Math.random()) - (int) (value * Math.random());
            int res1 = maxLength(arr, k);
            int res2 = right(arr, k);
            if (res1 != res2) {
                System.out.println("error");
                printArray(arr);
                System.out.println("k = " + k);
                System.out.println(res1);
                System.out.println(res2);
                break;
            }
        }
        System.out.println("test end");
    }

}
