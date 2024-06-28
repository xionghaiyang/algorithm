package com.sean.leetcode.LeetCode378;

import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-08 10:33
 * @Description: https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/
 * 378. 有序矩阵中第 K 小的元素
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 * 你必须找到一个内存复杂度优于 O(n2) 的解决方案。
 */
public class Solution {

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] arr = pq.poll();
            if (arr[2] < n - 1) {
                pq.offer(new int[]{matrix[arr[1]][arr[2] + 1], arr[1], arr[2] + 1});
            }
        }
        return pq.peek()[0];
    }

}
