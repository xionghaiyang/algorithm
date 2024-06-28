package com.sean.leetcode.LeetCode2341;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-16 08:12
 * @Description: https://leetcode.cn/problems/maximum-number-of-pairs-in-array/
 * 2341. 数组能形成多少数对
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 在一步操作中，你可以执行以下步骤：
 * 从 nums 选出 两个 相等的 整数
 * 从 nums 中移除这两个整数，形成一个 数对
 * 请你在 nums 上多次执行此操作直到无法继续执行。
 * 返回一个下标从 0 开始、长度为 2 的整数数组 answer 作为答案，
 * 其中 answer[0] 是形成的数对数目，answer[1] 是对 nums 尽可能执行上述操作后剩下的整数数目。
 */
public class Solution {

    public int[] numberOfPairs1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] answer = new int[2];
        for (int value : map.values()) {
            answer[0] += value / 2;
            answer[1] += value % 2;
        }
        return answer;
    }

    public int[] numberOfPairs(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], !map.getOrDefault(nums[i], false));
            if (!map.get(nums[i])) {
                res++;
            }
        }
        return new int[]{res, n - 2 * res};
    }

}
