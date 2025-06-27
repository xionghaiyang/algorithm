package com.sean.leetcode.LeetCode2099;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-06-28 06:00
 * @Description https://leetcode.cn/problems/find-subsequence-of-length-k-with-the-largest-sum
 * 2099. 找到和最大的长度为 K 的子序列
 * 给你一个整数数组 nums 和一个整数 k 。
 * 你需要找到 nums 中长度为 k 的 子序列 ，且这个子序列的 和最大 。
 * 请你返回 任意 一个长度为 k 的整数子序列。
 * 子序列 定义为从一个数组里删除一些元素后，不改变剩下元素的顺序得到的数组。
 * 1 <= nums.length <= 1000
 * -10^5 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 */
public class Solution {

    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);
        for (int i = 0; i < n; i++) {
            heap.offer(i);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        PriorityQueue<Integer> heap2 = new PriorityQueue<>();
        while (!heap.isEmpty()) {
            heap2.offer(heap.poll());
        }
        int[] res = new int[heap2.size()];
        int index = 0;
        while (!heap2.isEmpty()) {
            res[index++] = nums[heap2.poll()];
        }
        return res;
    }

    public int[] maxSubsequence1(int[] nums, int k) {
        int n = nums.length;
        Integer[] index = new Integer[n];
        Arrays.setAll(index, i -> i);
        Arrays.sort(index, (i, j) -> nums[j] - nums[i]);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = index[i];
        }
        Arrays.sort(res);
        for (int i = 0; i < k; i++) {
            res[i] = nums[res[i]];
        }
        return res;
    }

}
