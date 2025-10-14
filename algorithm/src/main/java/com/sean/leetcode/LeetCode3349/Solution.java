package com.sean.leetcode.LeetCode3349;

import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-10-14 17:46
 * @Description https://leetcode.cn/problems/adjacent-increasing-subarrays-detection-i
 * 3349. 检测相邻递增子数组 I
 * 给你一个由 n 个整数组成的数组 nums 和一个整数 k，请你确定是否存在 两个 相邻 且长度为 k 的 严格递增 子数组。
 * 具体来说，需要检查是否存在从下标 a 和 b (a < b) 开始的 两个 子数组，并满足下述全部条件：
 * 这两个子数组 nums[a..a + k - 1] 和 nums[b..b + k - 1] 都是 严格递增 的。
 * 这两个子数组必须是 相邻的，即 b = a + k。
 * 如果可以找到这样的 两个 子数组，请返回 true；否则返回 false。
 * 子数组 是数组中的一个连续 非空 的元素序列。
 * 2 <= nums.length <= 100
 * 1 <= 2 * k <= nums.length
 * -1000 <= nums[i] <= 1000
 */
public class Solution {

    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        int cnt = 1, preCnt = 0, res = 0;
        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                cnt++;
            } else {
                preCnt = cnt;
                cnt = 1;
            }
            res = Math.max(res, Math.min(preCnt, cnt));
            res = Math.max(res, cnt / 2);
        }
        return res >= k;
    }

}
