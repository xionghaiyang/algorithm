package com.sean.leetcode.LeetCode1703;

import java.util.ArrayList;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-19 09:39
 * @Description: https://leetcode.cn/problems/minimum-adjacent-swaps-for-k-consecutive-ones/
 * 1703. 得到连续 K 个 1 的最少相邻交换次数
 * 给你一个整数数组 nums 和一个整数 k 。 
 * nums 仅包含 0 和 1 。
 * 每一次移动，你可以选择 相邻 两个数字并将它们交换。
 * 请你返回使 nums 中包含 k 个 连续 1 的 最少 交换次数。
 */
public class Solution {

    public int minMoves(int[] nums, int k) {
        ArrayList<Integer> group = new ArrayList<>();
        ArrayList<Integer> preSum = new ArrayList<>();
        preSum.add(0);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                group.add(i - group.size());
                preSum.add(preSum.get(preSum.size() - 1) + group.get(group.size() - 1));
            }
        }
        int m = group.size(), res = Integer.MAX_VALUE;
        for (int i = 0; i <= m - k; i++) {
            int mid = i + k / 2;
            int r = group.get(mid);
            res = Math.min(res, (1 - k % 2) * r + (preSum.get(i + k) - preSum.get(mid + 1)) - (preSum.get(mid) - preSum.get(i)));
        }
        return res;
    }

}
