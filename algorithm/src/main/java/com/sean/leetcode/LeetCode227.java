package com.sean.leetcode;

import java.util.Stack;

public class LeetCode227 {

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        int length = s.length();
        char preSign = '+';
        for (int i = 0; i < length; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == length - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                num = 0;
                preSign = s.charAt(i);
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

}
