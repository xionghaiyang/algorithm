package com.sean.leetcode.LeetCode697;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-09-28 19:51
 * @Description https://leetcode.cn/problems/degree-of-an-array
 * 697. 数组的度
 * 给定一个非空且只包含非负数的整数数组 nums，数组的 度 的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * nums.length 在 1 到 50,000 范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数。
 */
public class Solution {

    public int findShortestSubArray(int[] nums) {
        int n = nums.length;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        int maxCnt = 0;
        int res = 0;
        for (int[] arr : map.values()) {
            if (arr[0] > maxCnt) {
                maxCnt = arr[0];
                res = arr[2] - arr[1] + 1;
            } else if (arr[0] == maxCnt) {
                res = Math.min(res, arr[2] - arr[1] + 1);
            }
        }
        return res;
    }

}
