package com.sean.leetcode.LeetCode1512;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-06-12 06:38
 * @Description https://leetcode.cn/problems/number-of-good-pairs
 * 1512. 好数对的数目
 * 给你一个整数数组 nums 。
 * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
 * 返回好数对的数目。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (int cnt : map.values()) {
            if (cnt > 1) {
                res += C(cnt);
            }
        }
        return res;
    }

    private int C(int n) {
        return n * (n - 1) / 2;
    }

}
