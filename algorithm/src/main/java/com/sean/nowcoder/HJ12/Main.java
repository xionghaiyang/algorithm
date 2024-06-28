package com.sean.nowcoder.HJ12;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder res = new StringBuilder(str);
        System.out.println(res.reverse().toString());
    }

}
