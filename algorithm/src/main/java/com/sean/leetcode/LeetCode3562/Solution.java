package com.sean.leetcode.LeetCode3562;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-12-16 08:31
 * @Description https://leetcode.cn/problems/maximum-profit-from-trading-stocks-with-discounts
 * 3562. 折扣价交易股票的最大利润
 * 给你一个整数 n，表示公司中员工的数量。
 * 每位员工都分配了一个从 1 到 n 的唯一 ID ，其中员工 1 是 CEO。
 * 另给你两个下标从 1 开始的整数数组 present 和 future，两个数组的长度均为 n，具体定义如下：
 * present[i] 表示第 i 位员工今天可以购买股票的 当前价格 。
 * future[i] 表示第 i 位员工明天可以卖出股票的 预期价格 。
 * 公司的层级关系由二维整数数组 hierarchy 表示，其中 hierarchy[i] = [ui, vi] 表示员工 ui 是员工 vi 的直属上司。
 * 此外，再给你一个整数 budget，表示可用于投资的总预算。
 * 公司有一项折扣政策：如果某位员工的直属上司购买了自己的股票，那么该员工可以以 半价 购买自己的股票（即 floor(present[v] / 2)）。
 * 请返回在不超过给定预算的情况下可以获得的 最大利润 。
 * 注意：
 * 每只股票最多只能购买一次。
 * 不能使用股票未来的收益来增加投资预算，购买只能依赖于 budget。
 * 1 <= n <= 160
 * present.length, future.length == n
 * 1 <= present[i], future[i] <= 50
 * hierarchy.length == n - 1
 * hierarchy[i] == [ui, vi]
 * 1 <= ui, vi <= n
 * ui != vi
 * 1 <= budget <= 160
 * 没有重复的边。
 * 员工 1 是所有员工的直接或间接上司。
 * 输入的图 hierarchy 保证 无环 。
 */
public class Solution {

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] e : hierarchy) {
            g[e[0] - 1].add(e[1] - 1);
        }
        int[][] f0 = dfs(0, g, present, future, budget);
        return f0[budget][0];
    }

    //返回f[j][k]，表示从子树x中能得到的最大利润之和
    //j为预算
    //k=0表示x不能半价购买,k=1表示x可以半价购买
    private int[][] dfs(int x, List<Integer>[] g, int[] present, int[] future, int budget) {
        //计算从x的所有儿子子树y中，能得到的最大利润之和
        int[][] subF = new int[budget + 1][2];
        for (int y : g[x]) {
            int[][] fy = dfs(y, g, present, future, budget);
            for (int j = budget; j >= 0; j--) {
                //枚举子树y的预算为jy
                for (int jy = 0; jy <= j; jy++) {
                    //k=0表示不买x,k=1表示买x
                    for (int k = 0; k < 2; k++) {
                        subF[j][k] = Math.max(subF[j][k], subF[j - jy][k] + fy[jy][k]);
                    }
                }
            }
        }
        //计算从子树x中，能得到的最大利润之和
        int[][] f = new int[budget + 1][2];
        for (int j = 0; j <= budget; j++) {
            for (int k = 0; k < 2; k++) {
                int cost = present[x] / (k + 1);
                if (j >= cost) {
                    f[j][k] = Math.max(subF[j][0], subF[j - cost][1] + future[x] - cost);
                } else {
                    f[j][k] = subF[j][0];
                }
            }
        }
        return f;
    }

}
