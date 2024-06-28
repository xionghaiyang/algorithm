package com.sean.leetcode.LeetCode1595;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-20 17:02
 * @Description: https://leetcode.cn/problems/minimum-cost-to-connect-two-groups-of-points/
 * 1595. 连通两组点的最小成本
 * 给你两组点，其中第一组中有 size1 个点，第二组中有 size2 个点，且 size1 >= size2 。
 * 任意两点间的连接成本 cost 由大小为 size1 x size2 矩阵给出，
 * 其中 cost[i][j] 是第一组中的点 i 和第二组中的点 j 的连接成本。
 * 如果两个组中的每个点都与另一组中的一个或多个点连接，则称这两组点是连通的。
 * 换言之，第一组中的每个点必须至少与第二组中的一个点连接，且第二组中的每个点必须至少与第一组中的一个点连接。
 * 返回连通两组点所需的最小成本。
 */
public class Solution {

    public int connectTwoGroups1(List<List<Integer>> cost) {
        int size1 = cost.size();
        int size2 = cost.get(0).size();
        int m = 1 << size2;
        //dp[i][j]表示第一组的前i个点（0,1,...,i-1）与第二组的最小连通成本
        int[][] dp = new int[size1 + 1][m];
        for (int i = 0; i <= size1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= size1; i++) {
            for (int s = 0; s < m; s++) {
                for (int k = 0; k < size2; k++) {
                    if ((s & (1 << k)) == 0) {
                        continue;
                    }
                    dp[i][s] = Math.min(dp[i][s], dp[i][s ^ (1 << k)] + cost.get(i - 1).get(k));
                    dp[i][s] = Math.min(dp[i][s], dp[i - 1][s] + cost.get(i - 1).get(k));
                    dp[i][s] = Math.min(dp[i][s], dp[i - 1][s ^ (1 << k)] + cost.get(i - 1).get(k));
                }
            }
        }
        return dp[size1][m - 1];
    }

    public int connectTwoGroups(List<List<Integer>> cost) {
        int size1 = cost.size();
        int size2 = cost.get(0).size();
        int m = 1 << size2;
        int[] dp1 = new int[m];
        Arrays.fill(dp1, Integer.MAX_VALUE / 2);
        dp1[0] = 0;
        int[] dp2 = new int[m];
        for (int i = 1; i <= size1; i++) {
            for (int s = 0; s < m; s++) {
                dp2[s] = Integer.MAX_VALUE / 2;
                for (int k = 0; k < size2; k++) {
                    if ((s & (1 << k)) == 0) {
                        continue;
                    }
                    dp2[s] = Math.min(dp2[s], dp2[s ^ (1 << k)] + cost.get(i - 1).get(k));
                    dp2[s] = Math.min(dp2[s], dp1[s] + cost.get(i - 1).get(k));
                    dp2[s] = Math.min(dp2[s], dp1[s ^ (1 << k)] + cost.get(i - 1).get(k));
                }
            }
            System.arraycopy(dp2, 0, dp1, 0, m);
        }
        return dp1[m - 1];
    }

}
