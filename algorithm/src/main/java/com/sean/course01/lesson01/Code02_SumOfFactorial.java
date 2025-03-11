package com.sean.course01.lesson01;

/**
 * @Author xionghaiyang
 * @Date 2025-03-11 13:40
 * @Description 给定一个参数N，返回：1! + 2! + 3! + 4! + … + N!的结果
 */
public class Code02_SumOfFactorial {

    public static long f1(int N) {
        long res = 0;
        for (int i = 1; i <= N; i++) {
            res += factorial(i);
        }
        return res;
    }

    private static long factorial(int N) {
        long res = 1;
        for (int i = 1; i <= N; i++) {
            res *= i;
        }
        return res;
    }

    public static long f2(int N) {
        long res = 0;
        long cur = 1;
        for (int i = 1; i <= N; i++) {
            cur *= i;
            res += cur;
        }
        return res;
    }

    public static void main(String[] args) {
        int N = 10;
        System.out.println(f1(N));
        System.out.println(f2(N));
    }

}
