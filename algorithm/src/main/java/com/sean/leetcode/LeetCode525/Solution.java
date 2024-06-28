package com.sean.leetcode.LeetCode525;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-09 18:46
 * @Description https://leetcode.cn/problems/contiguous-array/
 * 525. 连续数组
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 */
public class Solution {

    public int findMaxLength(int[] nums) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int preSum = 0;
        map.put(preSum, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                preSum++;
            } else {
                preSum--;
            }
            if (map.containsKey(preSum)) {
                int prevIndex = map.get(preSum);
                res = Math.max(res, i - prevIndex);
            } else {
                map.put(preSum, i);
            }
        }
        return res;
    }

}
