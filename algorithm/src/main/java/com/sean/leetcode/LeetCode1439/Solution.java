package com.sean.leetcode.LeetCode1439;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-29 08:52
 * @Description: https://leetcode.cn/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/
 * 1439. 有序矩阵中的第 k 个最小数组和
 * 给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。
 * 你可以从每一行中选出 1 个元素形成一个数组。
 * 返回所有可能数组中的第 k 个 最小 数组和。
 */
public class Solution {

    public int kthSmallest1(int[][] mat, int k) {
        int m = mat.length;
        int[] prev = mat[0];
        for (int i = 1; i < m; i++) {
            prev = merge(prev, mat[i], k);
        }
        return prev[k - 1];
    }

    private int[] merge(int[] f, int[] g, int k) {
        if (g.length > f.length) {
            return merge(g, f, k);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < g.length; i++) {
            pq.offer(new int[]{0, i, f[0] + g[i]});
        }
        List<Integer> list = new ArrayList<>();
        while (k > 0 && !pq.isEmpty()) {
            int[] entry = pq.poll();
            list.add(entry[2]);
            if (entry[0] + 1 < f.length) {
                pq.offer(new int[]{entry[0] + 1, entry[1], f[entry[0] + 1] + g[entry[1]]});
            }
            k--;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int[] prev = mat[0];
        for (int i = 1; i < m; i++) {
            prev = merge1(prev, mat[i], k);
        }
        return prev[k - 1];
    }

    private int[] merge1(int[] f, int[] g, int k) {
        int left = f[0] + g[0];
        int right = f[f.length - 1] + g[g.length - 1];
        int thres = 0;
        k = Math.min(k, f.length * g.length);
        while (left <= right) {
            int mid = (left + right) / 2;
            int r = g.length - 1;
            int cnt = 0;
            for (int l = 0; l < f.length; l++) {
                while (r >= 0 && f[l] + g[r] > mid) {
                    r--;
                }
                cnt += r + 1;
            }
            if (cnt >= k) {
                thres = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < g.length; j++) {
                int sum = f[i] + g[j];
                if (sum < thres) {
                    list.add(sum);
                } else {
                    break;
                }
            }
        }
        while (list.size() < k) {
            list.add(thres);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        Arrays.sort(res);
        return res;
    }

}
