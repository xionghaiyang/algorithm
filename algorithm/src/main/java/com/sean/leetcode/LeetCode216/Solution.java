package com.sean.leetcode.LeetCode216;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-23 16:09
 * @Description: https://leetcode.cn/problems/combination-sum-iii/description/
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。
 * 该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 */
public class Solution {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        process(k, n, 1);
        return res;
    }

    private void process(int k, int n, int m) {
        if (k == 0) {
            if (n == 0) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        if (m > 9 || n < 0) {
            return;
        }
        process(k, n, m + 1);
        temp.add(m);
        process(k - 1, n - m, m + 1);
        temp.remove(temp.size() - 1);
    }

}
