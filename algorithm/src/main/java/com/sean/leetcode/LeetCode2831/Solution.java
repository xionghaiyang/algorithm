package com.sean.leetcode.LeetCode2831;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-23 07:38
 * @Description https://leetcode.cn/problems/find-the-longest-equal-subarray/
 * 2831. 找出最长等值子数组
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 * 如果子数组中所有元素都相等，则认为子数组是一个 等值子数组 。
 * 注意，空数组是 等值子数组 。
 * 从 nums 中删除最多 k 个元素后，返回可能的最长等值子数组的长度。
 * 子数组 是数组中一个连续且可能为空的元素序列。
 */
public class Solution {

    public int longestEqualSubarray(List<Integer> nums, int k) {
        int size = nums.size();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            if (!map.containsKey(nums.get(i))) {
                map.put(nums.get(i), new ArrayList<>());
            }
            map.get(nums.get(i)).add(i);
            //map.computeIfAbsent(nums.get(i), x -> new ArrayList<>()).add(i);
        }
        int res = 0;
        for (List<Integer> list : map.values()) {
            for (int right = 0, left = 0; right < list.size(); right++) {
                while (list.get(right) - list.get(left) - (right - left) > k) {
                    left++;
                }
                res = Math.max(res, right - left + 1);
            }
        }
        return res;
    }

    public int longestEqualSubarray1(List<Integer> nums, int k) {
        int size = nums.size();
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int left = 0, right = 0; right < size; right++) {
            map.put(nums.get(right), map.getOrDefault(nums.get(right), 0) + 1);
            while (right - left + 1 - map.get(nums.get(left)) > k) {
                map.put(nums.get(left), map.get(nums.get(left)) - 1);
                left++;
            }
            res = Math.max(res, map.get(nums.get(right)));
        }
        return res;
    }

}
