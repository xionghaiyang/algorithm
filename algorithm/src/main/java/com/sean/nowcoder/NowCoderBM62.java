package com.sean.nowcoder;

public class NowCoderBM62 {

    public static int Fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        int res = 0;
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

}
