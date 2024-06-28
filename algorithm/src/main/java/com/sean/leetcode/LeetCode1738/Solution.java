package com.sean.leetcode.LeetCode1738;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-05-26 18:45
 * @Description https://leetcode.cn/problems/find-kth-largest-xor-coordinate-value/
 * 1738. 找出第 K 大的异或坐标值
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 */
public class Solution {

    public int kthLargestValue(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                pq.offer(pre[i][j]);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        return pq.peek();
    }

    public int kthLargestValue1(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                list.add(pre[i][j]);
            }
        }
        Collections.sort(list, (a, b) -> b - a);
        return list.get(k - 1);
    }

    public int kthLargestValue2(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                list.add(pre[i][j]);
            }
        }
        nthElement(list, 0, k - 1, list.size() - 1);
        return list.get(k - 1);
    }

    private void nthElement(List<Integer> list, int left, int kth, int right) {
        if (left == right) {
            return;
        }
        int pivot = (int) (left + Math.random() * (right - left + 1));
        swap(list, pivot, right);
        int sepl = left - 1, sepr = left - 1;
        for (int i = left; i <= right; i++) {
            if (list.get(i) > list.get(right)) {
                swap(list, ++sepr, i);
                swap(list, ++sepl, sepr);
            } else if (list.get(i) == list.get(right)) {
                swap(list, ++sepr, i);
            }
        }
        if (sepl < left + kth && left + kth <= sepr) {
            return;
        } else if (left + kth <= sepl) {
            nthElement(list, left, kth, sepl);
        } else {
            nthElement(list, sepr + 1, kth - (sepr - left + 1), right);
        }
    }

    private void swap(List<Integer> list, int index1, int index2) {
        int temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

}
