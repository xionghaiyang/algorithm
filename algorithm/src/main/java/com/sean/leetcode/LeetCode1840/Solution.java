package com.sean.leetcode.LeetCode1840;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-20 06:24
 * @Description: https://leetcode.cn/problems/maximum-building-height
 * 1840. 最高建筑高度
 * 在一座城市里，你需要建 n 栋新的建筑。
 * 这些新的建筑会从 1 到 n 编号排成一列。
 * 这座城市对这些新建筑有一些规定：
 * 每栋建筑的高度必须是一个非负整数。
 * 第一栋建筑的高度 必须 是 0 。
 * 任意两栋相邻建筑的高度差 不能超过  1 。
 * 除此以外，某些建筑还有额外的最高高度限制。
 * 这些限制会以二维整数数组 restrictions 的形式给出，其中 restrictions[i] = [idi, maxHeighti] ，表示建筑 idi 的高度 不能超过 maxHeighti 。
 * 题目保证每栋建筑在 restrictions 中 至多出现一次 ，同时建筑 1 不会 出现在 restrictions 中。
 * 请你返回 最高 建筑能达到的 最高高度 。
 * 2 <= n <= 10^9
 * 0 <= restrictions.length <= min(n - 1, 10^5)
 * 2 <= idi <= n
 * idi 是 唯一的 。
 * 0 <= maxHeighti <= 10^9
 */
public class Solution {

    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;
        if (m == 0) {
            return n - 1;
        }
        //按照id从小到大排序
        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);
        //h[i]表示编号为id[i]的建筑的最大高度
        int[] h = new int[m];
        h[0] = Math.min(restrictions[0][0] - 1, restrictions[0][1]);
        for (int i = 1; i < m; i++) {
            h[i] = Math.min(h[i - 1] + restrictions[i][0] - restrictions[i - 1][0], restrictions[i][1]);
        }
        for (int i = m - 2; i >= 0; i--) {
            h[i] = Math.min(h[i], h[i + 1] + restrictions[i + 1][0] - restrictions[i][0]);
        }
        int res = Math.max((restrictions[0][0] - 1 + h[0]) / 2, h[m - 1] + n - restrictions[m - 1][0]);
        for (int i = 0; i < m - 1; i++) {
            res = Math.max(res, (restrictions[i + 1][0] - restrictions[i][0] + h[i] + h[i + 1]) / 2);
        }
        return res;
    }

}
