package com.sean.leetcode;

import java.util.Stack;

/**
 * 比较含退格的字符串
 * https://leetcode-cn.com/problems/backspace-string-compare/
 */
public class LeetCode844 {

    public static boolean backspaceCompare(String s, String t) {
        Stack<Character> sStack = createStack(s);
        Stack<Character> tStack = createStack(t);
        while (!sStack.isEmpty() && !tStack.isEmpty()) {
            if (sStack.pop() != tStack.pop()) {
                return false;
            }
        }
        if (!sStack.isEmpty() || !tStack.isEmpty()) {
            return false;
        }
        return true;
    }

    private static Stack<Character> createStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(ch);
            }
        }
        return stack;
    }

    public static void main(String[] args) {
        System.out.println(backspaceCompare("ab#c","ad#c"));
        System.out.println(backspaceCompare("ab##","c#d#"));
        System.out.println(backspaceCompare("a##c","#a#c"));
        System.out.println(backspaceCompare("a#c","b"));
    }

}
