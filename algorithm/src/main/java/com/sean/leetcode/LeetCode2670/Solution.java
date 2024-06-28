package com.sean.leetcode.LeetCode2670;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-31 10:44
 * @Description: https://leetcode.cn/problems/find-the-distinct-difference-array/
 * 2670. 找出不同元素数目差数组
 * 给你一个下标从 0 开始的数组 nums ，数组长度为 n 。
 * nums 的 不同元素数目差 数组可以用一个长度为 n 的数组 diff 表示，其中 diff[i] 等于前缀 nums[0, ..., i] 中不同元素的数目 减去 后缀 nums[i + 1, ..., n - 1] 中不同元素的数目。
 * 返回 nums 的 不同元素数目差 数组。
 * 注意 nums[i, ..., j] 表示 nums 的一个从下标 i 开始到下标 j 结束的子数组（包含下标 i 和 j 对应元素）。
 * 特别需要说明的是，如果 i > j ，则 nums[i, ..., j] 表示一个空子数组。
 */
public class Solution {

    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            res[i] = map.size();
        }
        for (int i = 0; i < n; i++) {
            if (map.get(nums[i]) == 1) {
                map.remove(nums[i]);
            } else {
                map.put(nums[i], map.get(nums[i]) - 1);
            }
            res[i] -= map.size();
        }
        return res;
    }

}
