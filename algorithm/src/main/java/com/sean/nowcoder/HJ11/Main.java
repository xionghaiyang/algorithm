package com.sean.nowcoder.HJ11;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        if (num == 0) {
            System.out.println(0);
            return;
        }
        StringBuffer res = new StringBuffer();
        while (num != 0) {
            res.append(num % 10);
            num /= 10;
        }
        System.out.println(res.toString());
    }

}
