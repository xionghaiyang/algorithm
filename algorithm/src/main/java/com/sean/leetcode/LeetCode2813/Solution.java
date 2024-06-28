package com.sean.leetcode.LeetCode2813;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-06-13 08:23
 * @Description https://leetcode.cn/problems/maximum-elegance-of-a-k-length-subsequence/
 * 2813. 子序列最大优雅度
 * 给你一个长度为 n 的二维整数数组 items 和一个整数 k 。
 * items[i] = [profiti, categoryi]，其中 profiti 和 categoryi 分别表示第 i 个项目的利润和类别。
 * 现定义 items 的 子序列 的 优雅度 可以用 total_profit + distinct_categories^2 计算，
 * 其中 total_profit 是子序列中所有项目的利润总和，distinct_categories 是所选子序列所含的所有类别中不同类别的数量。
 * 你的任务是从 items 所有长度为 k 的子序列中，找出 最大优雅度 。
 * 用整数形式表示并返回 items 中所有长度恰好为 k 的子序列的最大雅度。
 * 注意：数组的子序列是经由原数组删除一些元素（可能不删除）而产生的新数组，且删除不改变其余元素相对顺序。
 */
public class Solution {

    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (a, b) -> b[0] - a[0]);
        int n = items.length;
        Deque<Integer> stack = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        long profit = 0, res = 0;
        for (int i = 0; i < n; i++) {
            if (i < k) {
                profit += items[i][0];
                if (!set.add(items[i][1])) {
                    stack.push(items[i][0]);
                }
            } else if (!stack.isEmpty() && set.add(items[i][1])) {
                profit += items[i][0] - stack.pop();
            }
            res = Math.max(res, profit + (long) set.size() * set.size());
        }
        return res;
    }

}
