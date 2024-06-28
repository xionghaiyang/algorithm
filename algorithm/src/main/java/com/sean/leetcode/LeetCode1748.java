package com.sean.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 唯一元素的和
 * https://leetcode-cn.com/problems/sum-of-unique-elements/
 */
public class LeetCode1748 {

    public static int sumOfUnique(int[] nums) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Iterator<Map.Entry<Integer, Integer>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = entryIterator.next();
            if (entry.getValue() == 1) {
                res += entry.getKey();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(sumOfUnique(new int[]{1, 2, 3, 2}));
        System.out.println(sumOfUnique(new int[]{1, 1, 1, 1, 1}));
        System.out.println(sumOfUnique(new int[]{1, 2, 3, 4, 5}));
    }

}
