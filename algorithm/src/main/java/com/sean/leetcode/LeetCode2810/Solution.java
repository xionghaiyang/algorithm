package com.sean.leetcode.LeetCode2810;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-01 11:49
 * @Description: https://leetcode.cn/problems/faulty-keyboard/
 * 2810. 故障键盘
 * 你的笔记本键盘存在故障，每当你在上面输入字符 'i' 时，它会反转你所写的字符串。而输入其他字符则可以正常工作。
 * 给你一个下标从 0 开始的字符串 s ，请你用故障键盘依次输入每个字符。
 * 返回最终笔记本屏幕上输出的字符串。
 */
public class Solution {

    public String finalString(String s) {
        int n = s.length();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'i') {
                res.reverse();
            } else {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

    public String finalString1(String s) {
        Deque<Character> q = new ArrayDeque<>();
        boolean head = false;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != 'i') {
                if (head) {
                    q.offerFirst(c);
                } else {
                    q.offerLast(c);
                }
            } else {
                head = !head;
            }
        }
        StringBuilder res = new StringBuilder();
        if (head) {
            while (!q.isEmpty()) {
                res.append(q.pollLast());
            }
        } else {
            while (!q.isEmpty()) {
                res.append(q.pollFirst());
            }
        }
        return res.toString();
    }

}
