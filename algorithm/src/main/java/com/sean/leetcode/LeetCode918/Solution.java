package com.sean.leetcode.LeetCode918;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-14 21:18
 * @Description: https://leetcode.cn/problems/maximum-sum-circular-subarray
 * 918. 环形子数组的最大和
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 * 环形数组 意味着数组的末端将会与开头相连呈环状。
 * 形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。
 * 形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 * n == nums.length
 * 1 <= n <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4​​​​​​​
 */
public class Solution {

    public int maxSubarraySumCircular(int[] nums) {
        int preMax = 0, maxRes = Integer.MIN_VALUE, preMin = 0, minRes = 0, sum = 0;
        for (int num : nums) {
            preMax = Math.max(preMax, 0) + num;
            maxRes = Math.max(maxRes, preMax);
            preMin = Math.min(preMin, 0) + num;
            minRes = Math.min(minRes, preMin);
            sum += num;
        }
        return maxRes < 0 ? maxRes : Math.max(maxRes, sum - minRes);
    }

}
