package com.sean.leetcode;

import jdk.nashorn.internal.ir.IdentNode;

import java.util.*;

public class LeetCode398 {

    class Solution {

        Map<Integer, List<Integer>> map;
        Random random;

        public Solution(int[] nums) {
            map = new HashMap<Integer, List<Integer>>();
            random = new Random();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) {
                    map.get(nums[i]).add(i);
                } else {
                    map.put(nums[i], new ArrayList<Integer>());
                    map.get(nums[i]).add(i);
                }
            }
        }

        public int pick(int target) {
            List<Integer> list = map.get(target);
            return list.get(random.nextInt(list.size()));
        }
    }

}
