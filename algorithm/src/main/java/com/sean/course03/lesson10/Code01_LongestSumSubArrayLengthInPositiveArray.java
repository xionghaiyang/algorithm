package com.sean.course03.lesson10;

/**
 * @Author xionghaiyang
 * @Date 2025-12-09 17:09
 * @Description 给定一个正整数组成的无序数组arr，给定一个正整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
 * 返回其长度
 */
public class Code01_LongestSumSubArrayLengthInPositiveArray {

    public static int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int res = 0;
        while (right < arr.length) {
            if (sum == k) {
                res = Math.max(res, right - left + 1);
                sum -= arr[left++];
            } else if (sum < k) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left++];
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

    private static int[] generatePositiveArray(int size, int value) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = (int) (value * Math.random()) + 1;
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
            int[] arr = generatePositiveArray(size, value);
            int k = (int) (Math.random() * value) + 1;
            int res1 = getMaxLength(arr, k);
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
