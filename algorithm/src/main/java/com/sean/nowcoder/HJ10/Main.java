package com.sean.nowcoder.HJ10;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.next();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < line.length(); i++) {
            set.add(line.charAt(i));
        }
        System.out.println(set.size());
    }

}
