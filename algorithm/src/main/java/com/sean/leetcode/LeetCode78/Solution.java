package com.sean.leetcode.LeetCode78;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-06-12 07:00
 * @Description https://leetcode.cn/problems/subsets
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。
 * 返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。
 * 你可以按 任意顺序 返回解集。
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> temp = new ArrayList<>();
    private int n;

    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        process(nums, 0);
        return res;
    }

    private void process(int[] nums, int i) {
        if (i == n) {
            res.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[i]);
        process(nums, i + 1);
        temp.remove(temp.size() - 1);
        process(nums, i + 1);
    }

}
