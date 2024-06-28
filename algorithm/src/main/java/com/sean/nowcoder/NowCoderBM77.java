package com.sean.nowcoder;

import java.util.Stack;

public class NowCoderBM77 {

    public static int longestValidParentheses(String s) {
        int res = 0;
        int start = -1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    start = i;
                } else {
                    stack.pop();
                    if (!stack.isEmpty()) {
                        res = Math.max(res, i - stack.peek());
                    } else {
                        res = Math.max(res, i - start);
                    }
                }
            }
        }
        return res;
    }

}
