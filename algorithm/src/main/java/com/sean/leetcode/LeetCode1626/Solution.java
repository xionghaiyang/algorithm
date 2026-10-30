package com.sean.leetcode.LeetCode1626;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2023-03-22 08:17
 * @Description: https://leetcode.cn/problems/best-team-with-no-conflicts
 * 1626. 无矛盾的最佳球队
 * 假设你是球队的经理。
 * 对于即将到来的锦标赛，你想组合一支总体得分最高的球队。
 * 球队的得分是球队中所有球员的分数 总和 。
 * 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。
 * 如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。
 * 同龄球员之间不会发生矛盾。
 * 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。
 * 请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
 * 1 <= scores.length, ages.length <= 1000
 * scores.length == ages.length
 * 1 <= scores[i] <= 10^6
 * 1 <= ages[i] <= 1000
 */
public class Solution {

    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        Arrays.sort(index, (i, j) -> scores[i] != scores[j] ? scores[i] - scores[j] : ages[i] - ages[j]);
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (ages[index[j]] <= ages[index[i]]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += scores[index[i]];
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
