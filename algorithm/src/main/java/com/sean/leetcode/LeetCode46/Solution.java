package com.sean.leetcode.LeetCode46;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-15 16:53
 * @Description: https://leetcode.cn/problems/permutations/
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。
 * 你可以 按任意顺序 返回答案。
 */
public class Solution {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    int n;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        n = nums.length;
        boolean[] visit = new boolean[n];
        dfs(nums, visit, 0);
        return res;
    }

    private void dfs(int[] nums, boolean[] visit, int size) {
        if (size == n) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            temp.add(nums[i]);
            dfs(nums, visit, size + 1);
            visit[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

}
