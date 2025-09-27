package com.sean.leetcode.LeetCode2127;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-27 20:23
 * @Description https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting
 * 2127. 参加会议的最多员工数
 * 一个公司准备组织一场会议，邀请名单上有 n 位员工。
 * 公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工。
 * 员工编号为 0 到 n - 1 。
 * 每位员工都有一位 喜欢 的员工，每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议。
 * 每位员工喜欢的员工 不会 是他自己。
 * 给你一个下标从 0 开始的整数数组 favorite ，其中 favorite[i] 表示第 i 位员工喜欢的员工。
 * 请你返回参加会议的 最多员工数目 。
 * n == favorite.length
 * 2 <= n <= 10^5
 * 0 <= favorite[i] <= n - 1
 * favorite[i] != i
 */
public class Solution {

    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] inDeg = new int[n];
        for (int f : favorite) {
            inDeg[f]++;
        }
        //反向建图
        List<Integer>[] rg = new ArrayList[n];
        Arrays.setAll(rg, e -> new ArrayList<>());
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            int v = favorite[u];
            rg[v].add(u);
            if (--inDeg[v] == 0) {
                queue.offer(v);
            }
        }
        int maxRingSize = 0, sumChainSize = 0;
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                continue;
            }
            inDeg[i] = 0;
            int ringSize = 1;
            for (int x = favorite[i]; x != i; x = favorite[x]) {
                inDeg[x] = 0;
                ringSize++;
            }
            if (ringSize == 2) {
                sumChainSize += rdfs(i, rg) + rdfs(favorite[i], rg);
            } else {
                maxRingSize = Math.max(maxRingSize, ringSize);
            }
        }
        return Math.max(maxRingSize, sumChainSize);
    }

    private int rdfs(int u, List<Integer>[] rg) {
        int maxDepth = 1;
        for (int v : rg[u]) {
            maxDepth = Math.max(maxDepth, rdfs(v, rg) + 1);
        }
        return maxDepth;
    }

}
