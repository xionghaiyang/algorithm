package com.sean.leetcode.LeetCode2585;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-15 18:31
 * @Description: https://leetcode.cn/problems/number-of-ways-to-earn-points
 * 2585. 获得分数的方法数
 * 考试中有 n 种类型的题目。
 * 给你一个整数 target 和一个下标从 0 开始的二维整数数组 types ，其中 types[i] = [counti, marksi] 表示第 i 种类型的题目有 counti 道，每道题目对应 marksi 分。
 * 返回你在考试中恰好得到 target 分的方法数。
 * 由于答案可能很大，结果需要对 10^9 +7 取余。
 * 注意，同类型题目无法区分。
 * 比如说，如果有 3 道同类型题目，那么解答第 1 和第 2 道题目与解答第 1 和第 3 道题目或者第 2 和第 3 道题目是相同的。
 * 1 <= target <= 1000
 * n == types.length
 * 1 <= n <= 50
 * types[i].length == 2
 * 1 <= counti, marksi <= 50
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int waysToReachTarget(int target, int[][] types) {
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int[] type : types) {
            int count = type[0], marks = type[1];
            for (int j = target; j >= marks; j--) {
                for (int k = 1; k <= Math.min(count, j / marks); k++) {
                    f[j] = (f[j] + f[j - k * marks]) % MOD;
                }
            }
        }
        return f[target];
    }

}
