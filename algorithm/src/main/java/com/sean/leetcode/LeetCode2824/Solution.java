package com.sean.leetcode.LeetCode2824;

import java.util.Collections;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-11-24 10:44
 * @Description: https://leetcode.cn/problems/count-pairs-whose-sum-is-less-than-target
 * 2824. 统计和小于目标的下标对数目
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 target ，
 * 请你返回满足 0 <= i < j < n 且 nums[i] + nums[j] < target 的下标对 (i, j) 的数目。
 * 1 <= nums.length == n <= 50
 * -50 <= nums[i], target <= 50
 */
public class Solution {

    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int res = 0;
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            if (nums.get(left) + nums.get(right) < target) {
                res += right - left;
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

}
