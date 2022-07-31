package com.sean.leetcode;

import java.util.Stack;

/**
 * 棒球比赛
 * https://leetcode-cn.com/problems/baseball-game/
 */
public class LeetCode682 {

    public static int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String s : ops) {
            if ("+".equals(s)) {
                Integer top = stack.pop();
                int newTop = stack.peek() + top;
                stack.push(top);
                stack.push(newTop);
            } else if ("D".equals(s)) {
                stack.push(stack.peek() * 2);
            } else if ("C".equals(s)) {
                stack.pop();
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(calPoints(new String[]{"5", "2", "C", "D", "+"}));
        System.out.println(calPoints(new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"}));
        System.out.println(calPoints(new String[]{"1"}));
    }

}
