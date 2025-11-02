package com.sean.leetcode.LeetCode3731;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-11-02 19:12
 * @Description https://leetcode.cn/problems/find-missing-elements
 * 3731. 找出缺失的元素
 * 给你一个整数数组 nums ，数组由若干 互不相同 的整数组成。
 * 数组 nums 原本包含了某个范围内的 所有整数 。
 * 但现在，其中可能 缺失 部分整数。
 * 该范围内的 最小 整数和 最大 整数仍然存在于 nums 中。
 * 返回一个 有序 列表，包含该范围内缺失的所有整数，并 按从小到大排序。
 * 如果没有缺失的整数，返回一个 空 列表。
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public List<Integer> findMissingElements(int[] nums) {
        boolean[] seen = new boolean[101];
        int min = 101;
        int max = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            seen[num] = true;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = min + 1; i < max; i++) {
            if (!seen[i]) {
                res.add(i);
            }
        }
        return res;
    }

}
