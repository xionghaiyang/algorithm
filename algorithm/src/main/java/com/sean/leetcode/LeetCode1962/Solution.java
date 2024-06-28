package com.sean.leetcode.LeetCode1962;

import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-23 15:52
 * @Description: https://leetcode.cn/problems/remove-stones-to-minimize-the-total/
 * 1962. 移除石子使总数最小
 * 给你一个整数数组 piles ，数组 下标从 0 开始 ，其中 piles[i] 表示第 i 堆石子中的石子数量。
 * 另给你一个整数 k ，请你执行下述操作 恰好 k 次：
 * 选出任一石子堆 piles[i] ，并从中 移除 floor(piles[i] / 2) 颗石子。
 * 注意：你可以对 同一堆 石子多次执行此操作。
 * 返回执行 k 次操作后，剩下石子的 最小 总数。
 * floor(x) 为 小于 或 等于 x 的 最大 整数。（即，对 x 向下取整）。
 */
public class Solution {

    public int minStoneSum(int[] piles, int k) {
        int res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int n = piles.length;
        for (int i = 0; i < n; i++) {
            pq.offer(piles[i]);
        }
        for (int i = 0; i < k; i++) {
            int cur = pq.poll();
            cur -= cur / 2;
            pq.offer(cur);
        }
        while (!pq.isEmpty()) {
            res += pq.poll();
        }
        return res;
    }

}
