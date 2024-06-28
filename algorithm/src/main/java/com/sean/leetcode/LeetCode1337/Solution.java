package com.sean.leetcode.LeetCode1337;

import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-12 10:55
 * @Description: https://leetcode.cn/problems/the-k-weakest-rows-in-a-matrix/
 * 1337. 矩阵中战斗力最弱的 K 行
 * 给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
 * 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
 * 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
 * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
 */
public class Solution {

    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            } else {
                return b[1] - a[1];
            }
        });
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (mat[i][mid] == 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            pq.offer(new int[]{right + 1, i});
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] res = new int[pq.size()];
        for (int i = pq.size() - 1; i >= 0; i--) {
            res[i] = pq.poll()[1];
        }
        return res;
    }

}
