package com.sean.nowcoder.HJ5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Character, Integer> map = new HashMap<Character, Integer>() {
            {
                put('0', 0);
                put('1', 1);
                put('2', 2);
                put('3', 3);
                put('4', 4);
                put('5', 5);
                put('6', 6);
                put('7', 7);
                put('8', 8);
                put('9', 9);
                put('A', 10);
                put('B', 11);
                put('C', 12);
                put('D', 13);
                put('E', 14);
                put('F', 15);
                put('a', 10);
                put('b', 11);
                put('c', 12);
                put('d', 13);
                put('e', 14);
                put('f', 15);
            }
        };
        while (sc.hasNext()) {
            String str = sc.next().substring(2);
            int res = 0;
            for (int i = 0; i < str.length(); i++) {
                res = res * 16 + map.get(str.charAt(i));
            }
            System.out.println(res);
        }
    }

}
