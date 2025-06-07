package com.sean.leetcode.LeetCode3170;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-06-07 09:10
 * @Description https://leetcode.cn/problems/lexicographically-minimum-string-after-removing-stars
 * 3170. 删除星号以后字典序最小的字符串
 * 给你一个字符串 s 。
 * 它可能包含任意数量的 '*' 字符。
 * 你的任务是删除所有的 '*' 字符。
 * 当字符串还存在至少一个 '*' 字符时，你可以执行以下操作：
 * 删除最左边的 '*' 字符，同时删除该星号字符左边一个字典序 最小 的字符。
 * 如果有多个字典序最小的字符，你可以删除它们中的任意一个。
 * 请你返回删除所有 '*' 字符以后，剩余字符连接而成的 字典序最小 的字符串。
 * 1 <= s.length <= 10^5
 * s 只含有小写英文字母和 '*' 字符。
 * 输入保证操作可以删除所有的 '*' 字符。
 */
public class Solution {

    public class Info {
        int c;
        LinkedList<Integer> indexes;

        public Info(int c) {
            this.c = c;
            indexes = new LinkedList<>();
        }
    }

    public String clearStars(String s) {
        int n = s.length();
        boolean[] deleted = new boolean[n];
        Info[] infos = new Info[26];
        for (int i = 0; i < 26; i++) {
            infos[i] = new Info(i);
        }
        PriorityQueue<Info> heap = new PriorityQueue<>((a, b) -> a.c - b.c);
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                deleted[i] = true;
                if (!heap.isEmpty()) {
                    Info info = heap.peek();
                    deleted[info.indexes.pollLast()] = true;
                    if (info.indexes.isEmpty()) {
                        heap.poll();
                    }
                }
            } else {
                if (!heap.contains(infos[s.charAt(i) - 'a'])) {
                    heap.offer(infos[s.charAt(i) - 'a']);
                }
                infos[s.charAt(i) - 'a'].indexes.addLast(i);
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!deleted[i]) {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

}
