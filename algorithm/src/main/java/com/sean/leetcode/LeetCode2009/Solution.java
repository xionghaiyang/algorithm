package com.sean.leetcode.LeetCode2009;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-08 13:58
 * @Description: https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-continuous/
 * 2009. 使数组连续的最少操作数
 * 给你一个整数数组 nums 。每一次操作中，你可以将 nums 中 任意 一个元素替换成 任意 整数。
 * 如果 nums 满足以下条件，那么它是 连续的 ：
 * nums 中所有元素都是 互不相同 的。
 * nums 中 最大 元素与 最小 元素的差等于 nums.length - 1 。
 * 比方说，nums = [4, 2, 5, 3] 是 连续的 ，但是 nums = [1, 2, 3, 5, 6] 不是连续的 。
 * 请你返回使 nums 连续 的 最少 操作次数。
 */
public class Solution {

    public int minOperations(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        int res = n;
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            int left = list.get(i);
            int right = left + n - 1;
            while (j < list.size() && list.get(j) <= right) {
                res = Math.min(res, n - (j - i + 1));
                j++;
            }
        }
        return res;
    }

}
