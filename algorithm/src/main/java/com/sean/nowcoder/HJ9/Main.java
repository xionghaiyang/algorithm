package com.sean.nowcoder.HJ9;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        Set<Integer> set = new HashSet<>();
        int res = 0;
        while (num != 0) {
            int i = num % 10;
            if (!set.contains(i)) {
                set.add(i);
                res = res * 10 + i;
            }
            num /= 10;
        }
        System.out.println(res);
    }

}
