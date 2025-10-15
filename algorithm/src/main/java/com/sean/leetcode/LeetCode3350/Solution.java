package com.sean.leetcode.LeetCode3350;

import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-10-15 12:27
 * @Description https://leetcode.cn/problems/adjacent-increasing-subarrays-detection-ii
 * 3350. 检测相邻递增子数组 II
 * 给你一个由 n 个整数组成的数组 nums ，请你找出 k 的 最大值，使得存在 两个 相邻 且长度为 k 的 严格递增 子数组。
 * 具体来说，需要检查是否存在从下标 a 和 b (a < b) 开始的 两个 子数组，并满足下述全部条件：
 * 这两个子数组 nums[a..a + k - 1] 和 nums[b..b + k - 1] 都是 严格递增 的。
 * 这两个子数组必须是 相邻的，即 b = a + k。
 * 返回 k 的 最大可能 值。
 * 子数组 是数组中的一个连续 非空 的元素序列。
 * 2 <= nums.length <= 2 * 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public int maxIncreasingSubarrays(List<Integer> nums) {
        int size = nums.size();
        int res = 0;
        for (int i = 0, cnt = 0, preCnt = 0; i < nums.size(); i++) {
            cnt++;
            if (i == nums.size() - 1 || nums.get(i) >= nums.get(i + 1)) {
                res = Math.max(res, Math.max(cnt / 2, Math.min(preCnt, cnt)));
                preCnt = cnt;
                cnt = 0;
            }
        }
        return res;
    }

}
