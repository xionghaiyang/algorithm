package com.sean.nowcoder;

import java.util.Stack;

public class NowCoderBM83 {

    public static String trans(String s, int n) {
        Stack<Character> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) <= 'Z' && s.charAt(i) >= 'A') {
                stack.push((char) (s.charAt(i) - 'A' + 'a'));
            } else if (s.charAt(i) <= 'z' && s.charAt(i) >= 'a') {
                stack.push((char) (s.charAt(i) - 'a' + 'A'));
            } else {
                while (!stack.isEmpty()) {
                    res.append(stack.pop());
                }
                res.append(" ");
            }
        }
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }

    public static String trans1(String s, int n) {
        StringBuilder res = new StringBuilder();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) <= 'Z' && s.charAt(i) >= 'A') {
                word.append((char) (s.charAt(i) - 'A' + 'a'));
            } else if (s.charAt(i) <= 'z' && s.charAt(i) >= 'a') {
                word.append((char) (s.charAt(i) - 'a' + 'A'));
            } else {
                res.append(word.reverse().toString());
                word.setLength(0);
                res.append(" ");
            }
        }
        res.append(word.reverse().toString());
        return res.reverse().toString();
    }

}
