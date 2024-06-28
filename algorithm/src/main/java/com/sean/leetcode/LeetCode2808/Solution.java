package com.sean.leetcode.LeetCode2808;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-30 12:58
 * @Description: https://leetcode.cn/problems/minimum-seconds-to-equalize-a-circular-array/
 * 2808. 使循环数组所有元素相等的最少秒数
 * 给你一个下标从 0 开始长度为 n 的数组 nums 。
 * 每一秒，你可以对数组执行以下操作：
 * 对于范围在 [0, n - 1] 内的每一个下标 i ，将 nums[i] 替换成 nums[i] ，nums[(i - 1 + n) % n] 或者 nums[(i + 1) % n] 三者之一。
 * 注意，所有元素会被同时替换。
 * 请你返回将数组 nums 中所有元素变成相等元素所需要的 最少 秒数。
 */
public class Solution {

    public int minimumSeconds(List<Integer> nums) {
        int n = nums.size();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums.get(i), k -> new ArrayList<>()).add(i);
        }
        int res = n;
        for (List<Integer> list : map.values()) {
            int size = list.size();
            int max = n - list.get(size - 1) + list.get(0);
            for (int i = 1; i < size; i++) {
                max = Math.max(max, list.get(i) - list.get(i - 1));
            }
            res = Math.min(res, max);
        }
        return res / 2;
    }

}
