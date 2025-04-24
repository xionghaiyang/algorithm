package com.sean.leetcode.LeetCode2799;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-04-24 09:09
 * @Description https://leetcode.cn/problems/count-complete-subarrays-in-an-array
 * 2799. 统计完全子数组的数目
 * 给你一个由 正 整数组成的数组 nums 。
 * 如果数组中的某个子数组满足下述条件，则称之为 完全子数组 ：
 * 子数组中 不同 元素的数目等于整个数组不同元素的数目。
 * 返回数组中 完全子数组 的数目。
 * 子数组 是数组中的一个连续非空序列。
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2000
 */
public class Solution {

    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int res = 0;
        for (int left = 0, right = 0; left < n; left++) {
            while (right < n && map.size() < size) {
                map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
                right++;
            }
            if (map.size() == size) {
                res += n - right + 1;
            }
            map.put(nums[left], map.getOrDefault(nums[left], 0) - 1);
            if (map.get(nums[left]) == 0) {
                map.remove(nums[left]);
            }
        }
        return res;
    }

}
