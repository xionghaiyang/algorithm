package com.sean.leetcode.LeetCode2610;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-03-19 08:55
 * @Description https://leetcode.cn/problems/convert-an-array-into-a-2d-array-with-conditions
 * 2610. 转换二维数组
 * 给你一个整数数组 nums 。
 * 请你创建一个满足以下条件的二维数组：
 * 二维数组应该 只 包含数组 nums 中的元素。
 * 二维数组中的每一行都包含 不同 的整数。
 * 二维数组的行数应尽可能 少 。
 * 返回结果数组。
 * 如果存在多种答案，则返回其中任何一种。
 * 请注意，二维数组的每一行上可以存在不同数量的元素。
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= nums.length
 */
public class Solution {

    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        while (!map.isEmpty()) {
            List<Integer> list = new ArrayList<>(map.keySet());
            res.add(list);
            for (int num : list) {
                int c = map.get(num) - 1;
                if (c == 0) {
                    map.remove(num);
                } else {
                    map.put(num, c);
                }
            }
        }
        return res;
    }

    public List<List<Integer>> findMatrix1(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        int[] cnt = new int[201];
        int most = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            cnt[nums[i]]++;
            most = Math.max(most, cnt[nums[i]]);
            max = Math.max(max, nums[i]);
        }
        while (most > 0) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= max; i++) {
                if (cnt[i] > 0) {
                    list.add(i);
                    cnt[i]--;
                }
            }
            res.add(list);
            most--;
        }
        return res;
    }

}
