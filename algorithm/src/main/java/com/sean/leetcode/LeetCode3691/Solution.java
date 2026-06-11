package com.sean.leetcode.LeetCode3691;

import java.util.PriorityQueue;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-11 19:03
 * @Description: https://leetcode.cn/problems/maximum-total-subarray-value-ii
 * 3691. 最大子数组总值 II
 * 给你一个长度为 n 的整数数组 nums 和一个整数 k。
 * 你必须从 nums 中选择 恰好 k 个 不同 的非空子数组 nums[l..r]。
 * 子数组可以重叠，但同一个子数组（相同的 l 和 r）不能 被选择超过一次。
 * 子数组 nums[l..r] 的 值 定义为：max(nums[l..r]) - min(nums[l..r])。
 * 总值 是所有被选子数组的 值 之和。
 * 返回你能实现的 最大 可能总值。
 * 子数组 是数组中连续的 非空 元素序列。
 * 1 <= n == nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 10^9
 * 1 <= k <= min(10^5, n * (n + 1) / 2)
 */
public class Solution {

    public class ST {
        private int[][] stMin;
        private int[][] stMax;

        public ST(int[] nums) {
            int n = nums.length;
            int w = 32 - Integer.numberOfLeadingZeros(n);
            stMin = new int[w][n];
            stMax = new int[w][n];
            for (int j = 0; j < n; j++) {
                stMin[0][j] = nums[j];
                stMax[0][j] = nums[j];
            }
            for (int i = 1; i < w; i++) {
                for (int j = 0; j + (1 << i) <= n; j++) {
                    stMin[i][j] = Math.min(stMin[i - 1][j], stMin[i - 1][j + (1 << (i - 1))]);
                    stMax[i][j] = Math.max(stMax[i - 1][j], stMax[i - 1][j + (1 << (i - 1))]);
                }
            }
        }

        //[left,right)
        public int query(int left, int right) {
            int k = 31 - Integer.numberOfLeadingZeros(right - left);
            int min = Math.min(stMin[k][left], stMin[k][right - (1 << k)]);
            int max = Math.max(stMax[k][left], stMax[k][right - (1 << k)]);
            return max - min;
        }
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        ST st = new ST(nums);
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < n; i++) {
            heap.offer(new int[]{st.query(i, n), i, n});
        }
        long res = 0;
        while (k-- > 0 && heap.peek()[0] > 0) {
            int[] top = heap.poll();
            res += top[0];
            top[2]--;
            top[0] = st.query(top[1], top[2]);
            heap.offer(top);
        }
        return res;
    }

}
