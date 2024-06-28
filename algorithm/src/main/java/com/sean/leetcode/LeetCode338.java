package com.sean.leetcode;

/**
 * 比特位计数
 * https://leetcode-cn.com/problems/counting-bits/
 */
public class LeetCode338 {

    public static int[] countBits(int n) {
        int[] res = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            res[i] = res[i - highBit] + 1;
        }
        return res;
    }

    public static int[] countBits1(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = countOnes(i);
        }
        return res;
    }

    private static int countOnes(int x) {
        int res = 0;
        while (x > 0) {
            x &= (x - 1);
            res++;
        }
        return res;
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                System.out.print("[" + arr[i] + ",");
            } else if (i == arr.length - 1) {
                System.out.println(arr[i] + "]");
            }else{
                System.out.print(arr[i] + ",");
            }
        }
    }

    public static void main(String[] args) {
        printArr(countBits(2));
        printArr(countBits(5));

        printArr(countBits1(2));
        printArr(countBits1(5));
    }

}
