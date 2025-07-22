package com.sean.leetcode.LeetCode77;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-18 12:56
 * @Description: https://leetcode.cn/problems/combinations
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        process(n, k, 1);
        return res;
    }

    private void process(int n, int k, int i) {
        if (temp.size() + (n - i + 1) < k) {
            return;
        }
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }
        process(n, k, i + 1);
        temp.add(i);
        process(n, k, i + 1);
        temp.remove(temp.size() - 1);
    }

}
