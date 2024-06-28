package com.sean.base.chapter40;

import javafx.scene.chart.ValueAxis;

import java.util.HashMap;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-20 19:41
 * @Description: TODO
 */
public class Code02_LongestSumSubArrayLength {

    public int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        //key:前缀和
        //value:0~value这个前缀和是最早出现key这个值的
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int len = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(i - map.get(sum - k), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

    public int right(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, K)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    private boolean valid(int[] arr, int L, int R, int K) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    public int[] generateRandomArray(int size, int value) {
        int[] res = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        return res;
    }

    public void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code02_LongestSumSubArrayLength solution = new Code02_LongestSumSubArrayLength();
        int len = 50;
        int value = 100;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = solution.generateRandomArray(len, value);
            int K = (int) (Math.random() * value) - (int) (Math.random() * value);
            int res1 = solution.maxLength(arr, K);
            int res2 = solution.right(arr, K);
            if (res1 != res2) {
                System.out.println("Oops!");
                solution.printArray(arr);
                System.out.println("K:" + K);
                System.out.println(res1);
                System.out.println(res2);
                break;
            }
        }
        System.out.println("test end");
    }

}
