package com.sean.leetcode.LeetCode3075;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-12-25 10:30
 * @Description https://leetcode.cn/problems/maximize-happiness-of-selected-children
 * 3075. 幸福值最大化的选择方案
 * 给你一个长度为 n 的数组 happiness ，以及一个 正整数 k 。
 * n 个孩子站成一队，其中第 i 个孩子的 幸福值 是 happiness[i] 。
 * 你计划组织 k 轮筛选从这 n 个孩子中选出 k 个孩子。
 * 在每一轮选择一个孩子时，所有 尚未 被选中的孩子的 幸福值 将减少 1 。
 * 注意，幸福值 不能 变成负数，且只有在它是正数的情况下才会减少。
 * 选择 k 个孩子，并使你选中的孩子幸福值之和最大，返回你能够得到的 最大值 。
 * 1 <= n == happiness.length <= 2 * 10^5
 * 1 <= happiness[i] <= 10^8
 * 1 <= k <= n
 */
public class Solution {

    public long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;
        Arrays.sort(happiness);
        long res = 0;
        for (int i = n - 1, j = 0; i >= 0 && j < k && happiness[i] > j; i--, j++) {
            res += happiness[i] - j;
        }
        return res;
    }

}
