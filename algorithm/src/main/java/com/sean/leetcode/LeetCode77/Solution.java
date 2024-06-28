package com.sean.leetcode.LeetCode77;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-18 12:56
 * @Description: https://leetcode.cn/problems/combinations/
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 */
public class Solution {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        process(n, k, new ArrayList<>(), 1);
        return res;
    }

    private void process(int n, int k, List<Integer> list, int index) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <= n; i++) {
            list.add(i);
            process(n, k, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

}
