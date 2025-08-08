package com.sean.leetcode.LeetCode3629;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-08-08 20:35
 * @Description https://leetcode.cn/problems/minimum-jumps-to-reach-end-via-prime-teleportation
 * 3629. 通过质数传送到达终点的最少跳跃次数
 * 给你一个长度为 n 的整数数组 nums。
 * 你从下标 0 开始，目标是到达下标 n - 1。
 * 在任何下标 i 处，你可以执行以下操作之一：
 * 移动到相邻格子：跳到下标 i + 1 或 i - 1，如果该下标在边界内。
 * 质数传送：如果 nums[i] 是一个质数 p，你可以立即跳到任何满足 nums[j] % p == 0 的下标 j 处，且下标 j != i 。
 * 返回到达下标 n - 1 所需的 最少 跳跃次数。
 * 质数 是一个大于 1 的自然数，只有两个因子，1 和它本身。
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 */
public class Solution {

    private static final int MAX = 1_000_001;
    private static final List<Integer>[] primeFactors = new ArrayList[MAX];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        Arrays.setAll(primeFactors, i -> new ArrayList<>());
        for (int i = 2; i < MAX; i++) {
            if (primeFactors[i].isEmpty()) {
                for (int j = i; j < MAX; j += i) {
                    primeFactors[j].add(i);
                }
            }
        }
    }

    public int minJumps(int[] nums) {
        init();
        int n = nums.length;
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int p : primeFactors[nums[i]]) {
                groups.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
            }
        }
        int res = 0;
        boolean[] visited = new boolean[n];
        visited[0] = true;
        List<Integer> q = new ArrayList<>();
        q.add(0);
        while (true) {
            List<Integer> tmp = q;
            q = new ArrayList<>();
            for (int i : tmp) {
                if (i == n - 1) {
                    return res;
                }
                List<Integer> index = groups.computeIfAbsent(nums[i], k -> new ArrayList<>());
                index.add(i + 1);
                if (i > 0) {
                    index.add(i - 1);
                }
                for (int j : index) {
                    if (!visited[j]) {
                        visited[j] = true;
                        q.add(j);
                    }
                }
                index.clear();
            }
            res++;
        }
    }

}
