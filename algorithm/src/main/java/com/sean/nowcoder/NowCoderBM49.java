package com.sean.nowcoder;

import java.util.Stack;

public class NowCoderBM49 {

    public static int[] process(String s, int index) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char op = '+';
        int i;
        for (i = index; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + (s.charAt(i) - '0');
                if (i != s.length() - 1) {
                    continue;
                }
            }
            if (s.charAt(i) == '(') {
                int[] ans = process(s, i + 1);
                num = ans[0];
                i = ans[1];
                if (i != s.length() - 1) {
                    continue;
                }
            }
            switch (op) {
                case '+':
                    stack.push(num);
                    break;
                case '-':
                    stack.push(-num);
                    break;
                case '*':
                    int temp = stack.pop();
                    stack.push(temp * num);
                    break;
            }
            num = 0;
            if (s.charAt(i) == ')') {
                break;
            } else {
                op = s.charAt(i);
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        int[] ans = {sum, i};
        return ans;
    }

    public static int solve(String s) {
        int[] ans = process(s, 0);
        return ans[0];
    }

}
