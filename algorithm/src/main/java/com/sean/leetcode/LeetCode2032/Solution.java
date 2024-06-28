package com.sean.leetcode.LeetCode2032;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 09:18
 * @Description: https://leetcode.cn/problems/two-out-of-three/
 * 2032. 至少在两个数组中出现的值
 * 给你三个整数数组 nums1、nums2 和 nums3 ，请你构造并返回一个 元素各不相同的 数组，
 * 且由 至少 在 两个 数组中出现的所有值组成。
 * 数组中的元素可以按 任意 顺序排列。
 */
public class Solution {

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, 1);
        }
        for (int num : nums2) {
            map.put(num, map.getOrDefault(num, 0) | 2);
        }
        for (int num : nums3) {
            map.put(num, map.getOrDefault(num, 0) | 4);
        }
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if ((value & (value - 1)) != 0) {
                res.add(key);
            }
        }
        return res;
    }

}
