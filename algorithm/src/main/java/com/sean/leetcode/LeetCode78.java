package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/7/25 16:58
 */
public class LeetCode78 {

    /**
     * https://leetcode.cn/problems/subsets/
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        dfs(nums, 0, n);
        return res;
    }

    private void dfs(int[] nums, int index, int n) {
        if (index == n) {
            res.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[index]);
        dfs(nums, index + 1, n);
        temp.remove(temp.size() - 1);
        dfs(nums, index + 1, n);
    }

}
