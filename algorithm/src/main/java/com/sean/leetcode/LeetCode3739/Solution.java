package com.sean.leetcode.LeetCode3739;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-26 11:09
 * @Description: https://leetcode.cn/problems/count-subarrays-with-majority-element-ii
 * 3739. 统计主要元素子数组数目 II
 * 给你一个整数数组 nums 和一个整数 target。
 * 返回数组 nums 中满足 target 是 主要元素 的 子数组 的数目。
 * 一个子数组的 主要元素 是指该元素在该子数组中出现的次数 严格大于 其长度的 一半 。
 * 子数组 是数组中的一段连续且 非空 的元素序列。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10​​​​​​​^9
 * 1 <= target <= 10^9
 */
public class Solution {

    public long countMajoritySubarrays(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        long res = 0;
        int s = 0, f = 0;
        for (int num : nums) {
            if (num == target) {
                f += map.getOrDefault(s, 0);
                s++;
            } else {
                s--;
                f -= map.getOrDefault(s, 0);
            }
            res += f;
            map.merge(s, 1, Integer::sum);
        }
        return res;
    }

}
