package com.sean.leetcode;

import java.util.Stack;

public class LeetCode1021 {

    public static String removeOuterParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                int start = stack.pop();
                if (stack.isEmpty()) {
                    stringBuilder.append(s.substring(start + 1, i));
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeOuterParentheses("(()())(())"));
    }

}
