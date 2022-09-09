package com.sean.leetcode.LeetCode217;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-08 21:13
 * @Description: https://leetcode.cn/problems/contains-duplicate/?plan=data-structures&plan_progress=zquui9s
 * 217. 存在重复元素
 * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 */
public class Solution {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }

}
