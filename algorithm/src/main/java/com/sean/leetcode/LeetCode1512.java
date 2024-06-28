package com.sean.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 好数对的数目
 * https://leetcode-cn.com/problems/number-of-good-pairs/
 */
public class LeetCode1512 {

    public static int numIdenticalPairs(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer v = entry.getValue();
            res += v * (v - 1) / 2;
        }
        return res;
    }

    public static int numIdenticalPairs1(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(numIdenticalPairs(new int[]{1, 2, 3, 1, 1, 3}));
        System.out.println(numIdenticalPairs(new int[]{1, 1, 1, 1}));
        System.out.println(numIdenticalPairs(new int[]{1, 2, 3}));

        System.out.println(numIdenticalPairs1(new int[]{1, 2, 3, 1, 1, 3}));
        System.out.println(numIdenticalPairs1(new int[]{1, 1, 1, 1}));
        System.out.println(numIdenticalPairs1(new int[]{1, 2, 3}));
    }

}
