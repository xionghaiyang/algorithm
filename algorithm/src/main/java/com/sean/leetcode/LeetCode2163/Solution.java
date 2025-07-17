package com.sean.leetcode.LeetCode2163;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-07-18 05:26
 * @Description https://leetcode.cn/problems/minimum-difference-in-sums-after-removal-of-elements
 * 2163. 删除元素后和的最小差值
 * 给你一个下标从 0 开始的整数数组 nums ，它包含 3 * n 个元素。
 * 你可以从 nums 中删除 恰好 n 个元素，剩下的 2 * n 个元素将会被分成两个 相同大小 的部分。
 * 前面 n 个元素属于第一部分，它们的和记为 sumfirst 。
 * 后面 n 个元素属于第二部分，它们的和记为 sumsecond 。
 * 两部分和的 差值 记为 sumfirst - sumsecond 。
 * 比方说，sumfirst = 3 且 sumsecond = 2 ，它们的差值为 1 。
 * 再比方，sumfirst = 2 且 sumsecond = 3 ，它们的差值为 -1 。
 * 请你返回删除 n 个元素之后，剩下两部分和的 差值的最小值 是多少。
 * nums.length == 3 * n
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    public long minimumDifference(int[] nums) {
        int m = nums.length;
        int n = m / 3;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long sum = 0;
        for (int i = m - n; i < m; i++) {
            minHeap.offer(nums[i]);
            sum += nums[i];
        }
        //后缀最大和
        long[] sufMax = new long[m - n + 1];
        sufMax[m - n] = sum;
        for (int i = m - n - 1; i >= n; i--) {
            minHeap.offer(nums[i]);
            sum += nums[i] - minHeap.poll();
            sufMax[i] = sum;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        //前缀最小和
        long preMin = 0;
        for (int i = 0; i < n; i++) {
            maxHeap.offer(nums[i]);
            preMin += nums[i];
        }
        long res = preMin - sufMax[n];
        for (int i = n; i < m - n; i++) {
            maxHeap.offer(nums[i]);
            preMin += nums[i] - maxHeap.poll();
            res  = Math.min(res,preMin-sufMax[i+1]);
        }
        return res;
    }

}
