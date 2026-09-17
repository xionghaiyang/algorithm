package com.sean.leetcode.LeetCode1520;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-18 06:10
 * @Description: https://leetcode.cn/problems/maximum-number-of-non-overlapping-substrings
 * 1520. 最多的不重叠子字符串
 * 给你一个只包含小写字母的字符串 s ，你需要找到 s 中最多数目的非空子字符串，满足如下条件：
 * 这些字符串之间互不重叠，也就是说对于任意两个子字符串 s[i..j] 和 s[x..y] ，要么 j < x 要么 i > y 。
 * 如果一个子字符串包含字符 char ，那么 s 中所有 char 字符都应该在这个子字符串中。
 * 请你找到满足上述条件的最多子字符串数目。
 * 如果有多个解法有相同的子字符串数目，请返回这些子字符串总长度最小的一个解。
 * 可以证明最小总长度解是唯一的。
 * 请注意，你可以以 任意 顺序返回最优解的子字符串。
 * 1 <= s.length <= 10^5
 * s 只包含小写英文字母。
 */
public class Solution {

    private int left;
    private int right;

    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        List<Integer>[] pos = new ArrayList[26];
        Arrays.setAll(pos, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            pos[s.charAt(i) - 'a'].add(i);
        }
        List<Integer>[] g = new ArrayList[26];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < 26; i++) {
            if (pos[i].isEmpty()) {
                continue;
            }
            List<Integer> p = pos[i];
            int l = p.get(0), r = p.get(p.size() - 1);
            for (int j = 0; j < 26; j++) {
                if (j == i) {
                    continue;
                }
                List<Integer> q = pos[j];
                int k = lowerBound(q, l);
                //[l,r]包含第j个小写字母
                if (k < q.size() && q.get(k) <= r) {
                    g[i].add(j);
                }
            }
        }
        List<int[]> intervals = new ArrayList<>();
        boolean[] vis = new boolean[26];
        for (int i = 0; i < 26; i++) {
            if (pos[i].isEmpty()) {
                continue;
            }
            Arrays.fill(vis, false);
            left = n;
            right = 0;
            dfs(pos, g, vis, i);
            intervals.add(new int[]{left, right});
        }
        List<String> res = new ArrayList<>();
        intervals.sort((a, b) -> a[1] - b[1]);
        int preR = -1;
        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            if (l > preR) {
                res.add(s.substring(l, r + 1));
                preR = r;
            }
        }
        return res;
    }

    //> target的第一个
    private int lowerBound(List<Integer> list, int target) {
        int res = list.size();
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (list.get(mid) > target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private void dfs(List<Integer>[] pos, List<Integer>[] g, boolean[] vis, int x) {
        vis[x] = true;
        List<Integer> p = pos[x];
        left = Math.min(left, p.get(0));
        right = Math.max(right, p.get(p.size() - 1));
        for (int y : g[x]) {
            if (!vis[y]) {
                dfs(pos, g, vis, y);
            }
        }
    }

}
