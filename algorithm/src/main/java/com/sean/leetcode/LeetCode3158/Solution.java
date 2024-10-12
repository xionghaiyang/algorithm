package com.sean.leetcode.LeetCode3158;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2024-10-12 17:26
 * @Description https://leetcode.cn/problems/find-the-xor-of-numbers-which-appear-twice/
 * 3158. 求出出现两次数字的 XOR 值
 * 给你一个数组 nums ，数组中的数字 要么 出现一次，要么 出现两次。
 * 请你返回数组中所有出现两次数字的按位 XOR 值，如果没有数字出现过两次，返回 0 。
 */
public class Solution {

    public int duplicateNumbersXOR(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
            } else {
                res ^= num;
            }
        }
        return res;
    }

}
