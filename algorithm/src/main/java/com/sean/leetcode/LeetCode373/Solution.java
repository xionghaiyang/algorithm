package com.sean.leetcode.LeetCode373;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-08 09:57
 * @Description: https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/
 * 373. 查找和最小的 K 对数字
 * 给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 */
public class Solution {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]]);
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] pair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[pair[0]]);
            list.add(nums2[pair[1]]);
            res.add(list);
            if (pair[1] + 1 < n) {
                pq.offer(new int[]{pair[0], pair[1] + 1});
            }
        }
        return res;
    }

}
