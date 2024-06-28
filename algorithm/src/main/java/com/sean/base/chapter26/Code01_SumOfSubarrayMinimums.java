package com.sean.base.chapter26;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-18 20:25
 * @Description: TODO
 * subArrayMinSum1是暴力解
 * subArrayMinSum2是最优解的思路
 * sumSubarrayMins是最优解思路下的单调栈优化
 * Leetcode上不要提交subArrayMinSum1、subArrayMinSum2方法，因为没有考虑取摸
 * Leetcode上只提交sumSubarrayMins方法，时间复杂度O(N)，可以直接通过
 */
public class Code01_SumOfSubarrayMinimums {

    public int subArrayMinSum1(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = arr[i];
                for (int k = i + 1; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                }
                res += min;
            }
        }
        return res;
    }

    //没有用单调栈
    public int subArrayMinSum2(int[] arr) {
        //left[i]=x:arr[i]左边,离arr[i]最近<=arr[i]，位置在x
        int[] left = leftNearLessEqual2(arr);
        //right[i]=y:arr[i]右边，离arr[i]最近，<arr[i]的数，位置在y
        int[] right = rightNearLess2(arr);
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int start = i - left[i];
            int end = right[i] - i;
            res += start * end * arr[i];
        }
        return res;
    }

    private int[] leftNearLessEqual2(int[] arr) {
        int N = arr.length;
        int[] left = new int[N];
        for (int i = 0; i < N; i++) {
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

    private int[] rightNearLess2(int[] arr) {
        int N = arr.length;
        int[] right = new int[N];
        for (int i = 0; i < N; i++) {
            int res = N;
            for (int j = i + 1; j < N; j++) {
                if (arr[i] > arr[j]) {
                    res = j;
                    break;
                }
            }
            right[i] = res;
        }
        return right;
    }

    public int sumSubarrayMins(int[] arr) {
        int[] stack = new int[arr.length];
        int[] left = nearLessEqualLeft(arr, stack);
        int[] right = nearLessRight(arr, stack);
        long res = 0;
        for (int i = 0; i < arr.length; i++) {
            long start = i - left[i];
            long end = right[i] - i;
            res += start * end * (long) arr[i];
            res %= 1000000007;
        }
        return (int) res;
    }

    private int[] nearLessEqualLeft(int[] arr, int[] stack) {
        int N = arr.length;
        int[] left = new int[N];
        int size = 0;
        for (int i = N - 1; i >= 0; i--) {
            while (size != 0 && arr[i] <= arr[stack[size - 1]]) {
                left[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        while (size != 0) {
            left[stack[--size]] = -1;
        }
        return left;
    }

    private int[] nearLessRight(int[] arr, int[] stack) {
        int N = arr.length;
        int[] right = new int[N];
        int size = 0;
        for (int i = 0; i < N; i++) {
            while (size != 0 && arr[stack[size - 1]] > arr[i]) {
                right[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        while (size != 0) {
            right[stack[--size]] = N;
        }
        return right;
    }

    public int[] randomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = (int) (Math.random() * maxValue) + 1;
        }
        return res;
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code01_SumOfSubarrayMinimums solution = new Code01_SumOfSubarrayMinimums();
        int maxLen = 100;
        int maxValue = 50;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = solution.randomArray(len, maxValue);
            int res1 = solution.subArrayMinSum1(arr);
            int res2 = solution.subArrayMinSum2(arr);
            int res3 = solution.sumSubarrayMins(arr);
            if (res1 != res2 || res1 != res3) {
                solution.printArray(arr);
                System.out.println(res1);
                System.out.println(res2);
                System.out.println(res3);
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
