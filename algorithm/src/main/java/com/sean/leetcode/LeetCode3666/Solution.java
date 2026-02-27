package com.sean.leetcode.LeetCode3666;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-02-27 07:54
 * @Description https://leetcode.cn/problems/minimum-operations-to-equalize-binary-string
 * 3666. 使二进制字符串全为 1 的最少操作次数
 * 给你一个二进制字符串 s 和一个整数 k。
 * 在一次操作中，你必须选择 恰好 k 个 不同的 下标，并将每个 '0' 翻转 为 '1'，每个 '1' 翻转为 '0'。
 * 返回使字符串中所有字符都等于 '1' 所需的 最少 操作次数。
 * 如果不可能，则返回 -1。
 * 1 <= s.length <= 10^5
 * s[i] 的值为 '0' 或 '1'。
 * 1 <= k <= s.length
 */
public class Solution {

    public int minOperations(String s, int k) {
        int n = s.length(), m = 0;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        TreeSet<Integer>[] sets = new TreeSet[2];
        Arrays.setAll(sets, i -> new TreeSet<>());
        for (int i = 0; i <= n; i++) {
            sets[i & 1].add(i);
            if (i < n && s.charAt(i) == '0') {
                m++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(m);
        dist[m] = 0;
        sets[m & 1].remove(m);
        while (!queue.isEmpty()) {
            m = queue.poll();
            int c1 = Math.max(k - n + m, 0), c2 = Math.min(m, k);
            int lnode = m + k - 2 * c2, rnode = m + k - 2 * c1;
            TreeSet<Integer> set = sets[lnode & 1];
            for (Integer m2 = set.ceiling(lnode); m2 != null && m2 <= rnode; m2 = set.ceiling(lnode)) {
                dist[m2] = dist[m] + 1;
                queue.offer(m2);
                set.remove(m2);
            }
        }
        return dist[0] == Integer.MAX_VALUE ? -1 : dist[0];
    }

}
