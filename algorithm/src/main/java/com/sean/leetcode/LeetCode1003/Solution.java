package com.sean.leetcode.LeetCode1003;

import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-04 16:58
 * @Description: https://leetcode.cn/problems/check-if-word-is-valid-after-substitutions/
 * 1003. 检查替换后的词是否有效
 * 给你一个字符串 s ，请你判断它是否 有效 。
 * 字符串 s 有效 需要满足：假设开始有一个空字符串 t = "" ，你可以执行 任意次 下述操作将 t 转换为 s ：
 * 将字符串 "abc" 插入到 t 中的任意位置。
 * 形式上，t 变为 tleft + "abc" + tright，其中 t == tleft + tright 。注意，tleft 和 tright 可能为 空 。
 * 如果字符串 s 有效，则返回 true；否则，返回 false。
 */
public class Solution {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int n = s.length();
        if (n % 3 != 0) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                stack.push(c);
            } else if (c == 'b') {
                if (stack.isEmpty() || stack.peek() != 'a') {
                    return false;
                } else {
                    stack.push(c);
                }
            } else if (c == 'c') {
                if (stack.isEmpty() || stack.peek() != 'b') {
                    return false;
                } else {
                    stack.pop();
                    stack.pop();
                }
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

}
