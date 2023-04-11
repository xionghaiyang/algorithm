package com.sean.leetcode.LeetCode2488;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-16 08:00
 * @Description: https://leetcode.cn/problems/count-subarrays-with-median-k/
 * 2488. 统计中位数为 K 的子数组
 * 给你一个长度为 n 的数组 nums ，该数组由从 1 到 n 的 不同 整数组成。
 * 另给你一个正整数 k 。
 * 统计并返回 nums 中的 中位数 等于 k 的非空子数组的数目。
 * 注意：
 * 数组的中位数是按 递增 顺序排列后位于 中间 的那个元素，如果数组长度为偶数，则中位数是位于中间靠 左 的那个元素。
 * 例如，[2,3,1,4] 的中位数是 2 ，[8,4,3,5,1] 的中位数是 4 。
 * 子数组是数组中的一个连续部分。
 */
public class Solution {

    public int countSubarrays(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int kIndex = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                kIndex = i;
                break;
            }
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += sign(nums[i] - k);
            if (i < kIndex) {
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            } else {
                int prev0 = map.getOrDefault(sum, 0);
                int prev1 = map.getOrDefault(sum - 1, 0);
                res += prev0 + prev1;
            }
        }
        return res;
    }

    private int sign(int num) {
        if (num == 0) {
            return 0;
        }
        return num > 0 ? 1 : -1;
    }

}
