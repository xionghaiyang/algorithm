package com.sean.leetcode.LeetCode1636;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-19 11:34
 * @Description: https://leetcode.cn/problems/sort-array-by-increasing-frequency/
 * 1636. 按照频率将数组升序排序
 * 给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。
 * 请你返回排序后的数组。
 */
public class Solution {

    public int[] frequencySort(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int count1 = map.get(o1);
                int count2 = map.get(o2);
                return count1 != count2 ? count1 - count2 : o2 - o1;
            }
        });
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
