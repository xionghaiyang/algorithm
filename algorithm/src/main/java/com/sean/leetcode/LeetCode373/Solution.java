package com.sean.leetcode.LeetCode373;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-08 09:57
 * @Description: https://leetcode.cn/problems/find-k-pairs-with-smallest-sums
 * 373. 查找和最小的 K 对数字
 * 给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 */
public class Solution {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int m = nums1.length, n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            heap.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }
        while (k-- > 0 && !heap.isEmpty()) {
            int[] arr = heap.poll();
            int u = arr[1], v = arr[2];
            List<Integer> list = new ArrayList<>();
            list.add(nums1[u]);
            list.add(nums2[v]);
            res.add(list);
            if (v + 1 < n) {
                heap.offer(new int[]{nums1[u] + nums2[v + 1], u, v + 1});
            }
        }
        return res;
    }

}
