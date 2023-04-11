package com.sean.leetcode.LeetCodeInterview1705;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-13 08:20
 * @Description: https://leetcode.cn/problems/find-longest-subarray-lcci/
 * 面试题 17.05.  字母与数字
 * 给定一个放有字母和数字的数组，找到最长的子数组，且包含的字母和数字的个数相同。
 * 返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
 */
public class Solution {

    public String[] findLongestSubarray(String[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int maxLength = 0;
        int startIndex = -1;
        int n = array.length;
        for (int i = 0; i < n; i++) {
            if (Character.isLetter(array[i].charAt(0))) {
                sum++;
            } else {
                sum--;
            }
            if (map.containsKey(sum)) {
                int firstIndex = map.get(sum);
                if (i - firstIndex > maxLength) {
                    maxLength = i - firstIndex;
                    startIndex = firstIndex + 1;
                }
            } else {
                map.put(sum, i);
            }
        }
        if (maxLength == 0) {
            return new String[0];
        }
        String[] res = new String[maxLength];
        System.arraycopy(array, startIndex, res, 0, maxLength);
        return res;
    }

}
