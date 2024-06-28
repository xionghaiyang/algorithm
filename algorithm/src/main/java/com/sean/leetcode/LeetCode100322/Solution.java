package com.sean.leetcode.LeetCode100322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-06-02 20:45
 * @Description https://leetcode.cn/problems/lexicographically-minimum-string-after-removing-stars/
 * 100322. 删除星号以后字典序最小的字符串
 * 给你一个字符串 s 。
 * 它可能包含任意数量的 '*' 字符。你的任务是删除所有的 '*' 字符。
 * 当字符串还存在至少一个 '*' 字符时，你可以执行以下操作：
 * 删除最左边的 '*' 字符，同时删除该星号字符左边一个字典序 最小 的字符。
 * 如果有多个字典序最小的字符，你可以删除它们中的任意一个。
 * 请你返回删除所有 '*' 字符以后，剩余字符连接而成的字典序最小的字符串。
 */
public class Solution {

    public String clearStars(String s) {
        int n = s.length();
        List<Integer>[] st = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            st[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '*') {
                st[s.charAt(i) - 'a'].add(i);
                continue;
            }
            for (List<Integer> list : st) {
                if (!list.isEmpty()) {
                    list.remove(list.size() - 1);
                    break;
                }
            }
        }
        List<Integer> index = new ArrayList<>();
        for (List<Integer> list : st) {
            for (int num : list) {
                index.add(num);
            }
        }
        Collections.sort(index);
        StringBuilder res = new StringBuilder();
        for (int i : index) {
            res.append(s.charAt(i));
        }
        return res.toString();
    }

    public String clearStars1(String s) {
        int n = s.length();
        boolean[] del = new boolean[n];
        List<Integer>[] st = new ArrayList[26];
        Arrays.setAll(st, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '*') {
                st[s.charAt(i) - 'a'].add(i);
                continue;
            }
            for (List<Integer> list : st) {
                if (!list.isEmpty()) {
                    del[list.remove(list.size() - 1)] = true;
                    break;
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!del[i] && s.charAt(i) != '*') {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

}
