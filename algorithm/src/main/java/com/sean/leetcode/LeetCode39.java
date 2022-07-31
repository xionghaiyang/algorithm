package com.sean.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode39 {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, int index) {
        if (index == candidates.length) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        dfs(candidates, target, index + 1);
        if (target >= candidates[index]) {
            temp.add(candidates[index]);
            dfs(candidates, target - candidates[index], index);
            temp.remove(temp.size() - 1);
        }
    }

}
