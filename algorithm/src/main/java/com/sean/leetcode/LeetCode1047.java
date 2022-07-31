package com.sean.leetcode;

import java.util.Stack;

/**
 * 删除字符串中的所有相邻重复项
 * https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
 */
public class LeetCode1047 {

    public static String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && stack.peek() == s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (!stack.isEmpty()) {
            stringBuffer.append(stack.pop());
        }
        return stringBuffer.reverse().toString();
    }

}
