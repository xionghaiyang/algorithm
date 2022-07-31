package com.sean.lintcode;

import java.util.Scanner;

public class LintCode2334 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(process(n));
    }

    private static int process(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * process(n - 1);
    }

}
