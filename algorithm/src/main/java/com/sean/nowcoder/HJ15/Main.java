package com.sean.nowcoder.HJ15;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int res = 0;
        while (num != 0) {
            if ((num & 1) != 0) {
                res++;
            }
            num >>>= 1;
        }
        System.out.println(res);
    }

}
