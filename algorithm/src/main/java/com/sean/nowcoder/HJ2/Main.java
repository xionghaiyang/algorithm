package com.sean.nowcoder.HJ2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().toLowerCase();
        String ch = sc.nextLine().toLowerCase();
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch.charAt(0)) {
                count++;
            }
        }
        System.out.println(count);
    }

}
