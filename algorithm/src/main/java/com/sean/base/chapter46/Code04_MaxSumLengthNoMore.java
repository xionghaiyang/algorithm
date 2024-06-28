package com.sean.base.chapter46;

import java.util.LinkedList;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 16:58
 * @Description: 给定一个数组arr，和一个正数M
 * 返回在子数组长度不大于M的情况下，最大的子数组累加和
 */
public class Code04_MaxSumLengthNoMore {

    //O(N^2)的解，暴力解，用作对数器
    public int test(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        for (int L = 0; L < N; L++) {
            int sum = 0;
            for (int R = L; R < N; R++) {
                if (R - L + 1 > M) {
                    break;
                }
                sum += arr[R];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    //O(N)的解法，最优解
    public int maxSum(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        int N = arr.length;
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int i = 0;
        int end = Math.min(M, N);
        for (; i < end; i++) {
            while (!qmax.isEmpty() && sum[qmax.peekLast()] <= sum[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
        }
        int max = sum[qmax.peekFirst()];
        int L = 0;
        for (; i < N; L++, i++) {
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            while (!qmax.isEmpty() && sum[qmax.peekLast()] <= sum[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            max = Math.max(max, sum[qmax.peekFirst()] - sum[L]);
        }
        for (; L < N - 1; L++) {
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            max = Math.max(max, sum[qmax.peekFirst()] - sum[L]);
        }
        return max;
    }

    public int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    public static void main(String[] args) {
        Code04_MaxSumLengthNoMore solution = new Code04_MaxSumLengthNoMore();
        int maxN = 50;
        int maxValue = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN);
            int M = (int) (Math.random() * maxN);
            int[] arr = solution.randomArray(N, maxValue);
            int res1 = solution.test(arr, M);
            int res2 = solution.maxSum(arr, M);
            if (res1 != res2) {
                System.out.println(res1);
                System.out.println(res2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}
