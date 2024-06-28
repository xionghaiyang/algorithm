package com.sean.leetcode.LeetCode1207;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 15:29
 * @Description: https://leetcode.cn/problems/unique-number-of-occurrences/?envType=study-plan-v2&envId=leetcode-75
 * 1207. 独一无二的出现次数
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 */
public class Solution {

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Set<Integer> set = new HashSet<>();
        for (int count : map.values()) {
            if (set.contains(count)) {
                return false;
            }
            set.add(count);
        }
        return true;
    }

}
