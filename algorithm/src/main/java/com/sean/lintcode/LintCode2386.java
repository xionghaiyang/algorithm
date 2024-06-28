package com.sean.lintcode;

import java.util.Scanner;

public class LintCode2386 {

    public static class Main {

        public static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(pow(a, b));
        }

        public static int pow(int a, int b) {
            int ans = 1;
            for (int i = b; i > 0; i--) {
                ans *= a;
            }
            return ans;
        }
    }
}
