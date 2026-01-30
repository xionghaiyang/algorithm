package com.sean.leetcode.LeetCode2977;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-01-30 09:34
 * @Description https://leetcode.cn/problems/minimum-cost-to-convert-string-ii
 * 2977. 转换字符串的最小成本 II
 * 给你两个下标从 0 开始的字符串 source 和 target ，它们的长度均为 n 并且由 小写 英文字母组成。
 * 另给你两个下标从 0 开始的字符串数组 original 和 changed ，以及一个整数数组 cost ，其中 cost[i] 代表将字符串 original[i] 更改为字符串 changed[i] 的成本。
 * 你从字符串 source 开始。
 * 在一次操作中，如果 存在 任意 下标 j 满足 cost[j] == z  、original[j] == x 以及 changed[j] == y ，你就可以选择字符串中的 子串 x 并以 z 的成本将其更改为 y 。
 * 你可以执行 任意数量 的操作，但是任两次操作必须满足 以下两个 条件 之一 ：
 * 在两次操作中选择的子串分别是 source[a..b] 和 source[c..d] ，满足 b < c  或 d < a 。
 * 换句话说，两次操作中选择的下标 不相交 。
 * 在两次操作中选择的子串分别是 source[a..b] 和 source[c..d] ，满足 a == c 且 b == d 。
 * 换句话说，两次操作中选择的下标 相同 。
 * 返回将字符串 source 转换为字符串 target 所需的 最小 成本。
 * 如果不可能完成转换，则返回 -1 。
 * 注意，可能存在下标 i 、j 使得 original[j] == original[i] 且 changed[j] == changed[i] 。
 * 1 <= source.length == target.length <= 1000
 * source、target 均由小写英文字母组成
 * 1 <= cost.length == original.length == changed.length <= 100
 * 1 <= original[i].length == changed[i].length <= source.length
 * original[i]、changed[i] 均由小写英文字母组成
 * original[i] != changed[i]
 * 1 <= cost[i] <= 10^6
 */
public class Solution {

    public class Node {
        Node[] children = new Node[26];
        int sid = -1;
    }

    private Node root = new Node();
    private int sid = 0;
    private int[][] dis;
    private char[] s;
    private char[] t;
    private long[] memo;

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int m = cost.length;
        dis = new int[m * 2][m * 2];
        for (int i = 0; i < dis.length; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE / 2);
            dis[i][i] = 0;
        }
        for (int i = 0; i < cost.length; i++) {
            int u = put(original[i]), v = put(changed[i]), w = cost[i];
            dis[u][v] = Math.min(dis[u][v], w);
        }
        for (int k = 0; k < sid; k++) {
            for (int i = 0; i < sid; i++) {
                if (dis[i][k] == Integer.MAX_VALUE / 2) {
                    continue;
                }
                for (int j = 0; j < sid; j++) {
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }
        s = source.toCharArray();
        t = target.toCharArray();
        memo = new long[s.length];
        Arrays.fill(memo, -1);
        long res = dfs(0);
        return res < Long.MAX_VALUE / 2 ? res : -1;
    }

    private int put(String s) {
        Node cur = root;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            if (cur.children[i] == null) {
                cur.children[i] = new Node();
            }
            cur = cur.children[i];
        }
        if (cur.sid < 0) {
            cur.sid = sid++;
        }
        return cur.sid;
    }

    private long dfs(int i) {
        if (i >= s.length) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        long res = Long.MAX_VALUE / 2;
        if (s[i] == t[i]) {
            res = dfs(i + 1);
        }
        Node p = root, q = root;
        for (int j = i; j < s.length; j++) {
            p = p.children[s[j] - 'a'];
            q = q.children[t[j] - 'a'];
            if (p == null || q == null) {
                break;
            }
            if (p.sid < 0 || q.sid < 0) {
                continue;
            }
            int d = dis[p.sid][q.sid];
            if (d < Integer.MAX_VALUE / 2) {
                res = Math.min(res, d + dfs(j + 1));
            }
        }
        return memo[i] = res;
    }

}
