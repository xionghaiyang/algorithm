package com.sean.leetcode.LeetCode2824;

import java.util.Collections;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-11-24 10:44
 * @Description: https://leetcode.cn/problems/count-pairs-whose-sum-is-less-than-target/
 * 2824. 统计和小于目标的下标对数目
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 target ，
 * 请你返回满足 0 <= i < j < n 且 nums[i] + nums[j] < target 的下标对 (i, j) 的数目。
 */
public class Solution {

    public int countPairs(List<Integer> nums, int target) {
        int res = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) < target) {
                    res++;
                }
            }
        }
        return res;
    }

    public int countPairs1(List<Integer> nums, int target) {
        Collections.sort(nums);
        int res = 0;
        for (int i = 1; i < nums.size(); i++) {
            res += binarySearch(nums, 0, i - 1, target - nums.get(i));
        }
        return res;
    }

    private int binarySearch(List<Integer> nums, int low, int high, int target) {
        int res = high + 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums.get(mid) >= target) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    public int countPairs2(List<Integer> nums, int target) {
        Collections.sort(nums);
        int res = 0;
        for (int i = 0, j = nums.size() - 1; i < j; i++) {
            while (i < j && nums.get(i) + nums.get(j) >= target) {
                j--;
            }
            res += j - i;
        }
        return res;
    }

}
