package com.sean.leetcode.LeetCode90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-02-05 09:18
 * @Description https://leetcode.cn/problems/subsets-ii/
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。
 * 返回的解集中，子集可以按 任意顺序 排列。
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class Solution {

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        process(nums, false, 0);
        return res;
    }

    private void process(int[] nums, boolean choosePre, int cur) {
        if (cur == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        process(nums, false, cur + 1);
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        temp.add(nums[cur]);
        process(nums, true, cur + 1);
        temp.remove(temp.size() - 1);
    }

}
