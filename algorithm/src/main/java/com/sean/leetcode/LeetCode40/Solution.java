package com.sean.leetcode.LeetCode40;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2023-12-18 15:44
 * @Description: https://leetcode.cn/problems/combination-sum-ii
 * 40. 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> temp = new ArrayList<>();
    private int n;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        n = candidates.length;
        if (n == 0) {
            return res;
        }
        process(candidates, target, 0);
        return res;
    }

    private void process(int[] candidates, int target, int index) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < n; i++) {
            if (target < candidates[i]) {
                break;
            }
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            temp.add(candidates[i]);
            process(candidates, target - candidates[i], i + 1);
            temp.remove(temp.size() - 1);
        }
    }

}
