package com.sean.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 * 两数之和 II - 输入有序数组
 */
public class LeetCode167 {

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                return new int[]{map.get(target - numbers[i]), i + 1};
            } else {
                map.put(numbers[i], i + 1);
            }
        }
        return null;
    }

}
