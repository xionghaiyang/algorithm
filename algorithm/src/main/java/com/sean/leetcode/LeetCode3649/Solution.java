package com.sean.leetcode.LeetCode3649;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-08-23 18:21
 * @Description https://leetcode.cn/problems/number-of-perfect-pairs
 * 3649. 完美对的数目
 * 给你一个整数数组 nums。
 * 如果一对下标 (i, j) 满足以下条件，则称其为 完美 的：
 * i < j
 * 令 a = nums[i]，b = nums[j]。那么：
 * min(|a - b|, |a + b|) <= min(|a|, |b|)
 * max(|a - b|, |a + b|) >= max(|a|, |b|)
 * 返回 不同 完美对 的数量。
 * 注意：绝对值 |x| 指的是 x 的 非负 值。
 * 2 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public long perfectPairs(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] *= -1;
            }
        }
        Arrays.sort(nums);
        long res = 0;
        for (int i = 0, j = 0; j < n; j++) {
            int b = nums[j];
            while (nums[i] * 2 < b) {
                i++;
            }
            res += j - i;
        }
        return res;
    }

}
