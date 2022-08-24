package com.sean.leetcode.LeetCode1460;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-24 08:46
 * @Description: https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-sub-arrays/
 * 1460. 通过翻转子数组使两个数组相等
 * 给你两个长度相同的整数数组 target 和 arr 。每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
 * 如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。
 */
public class Solution {

    public boolean canBeEqual1(int[] target, int[] arr) {
        Map<Integer, Integer> count1 = new HashMap<>();
        Map<Integer, Integer> count2 = new HashMap<>();
        for (int num : target) {
            count1.put(num, count1.getOrDefault(num, 0) + 1);
        }
        for (int num : arr) {
            count2.put(num, count2.getOrDefault(num, 0) + 1);
        }
        if (count1.size() != count2.size()) {
            return false;
        }
        for (Map.Entry<Integer, Integer> entry : count1.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (!count2.containsKey(key) || count2.get(key) != value) {
                return false;
            }
        }
        return true;
    }

    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : target) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) - 1);
            if (map.get(num) == 0) {
                map.remove(num);
            } else if (map.get(num) < 0) {
                return false;
            }
        }
        return map.size() == 0;
    }

}
