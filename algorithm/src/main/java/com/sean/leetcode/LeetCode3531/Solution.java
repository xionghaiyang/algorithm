package com.sean.leetcode.LeetCode3531;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-12-11 10:26
 * @Description https://leetcode.cn/problems/count-covered-buildings
 * 3531. 统计被覆盖的建筑
 * 给你一个正整数 n，表示一个 n x n 的城市，同时给定一个二维数组 buildings，其中 buildings[i] = [x, y] 表示位于坐标 [x, y] 的一个 唯一 建筑。
 * 如果一个建筑在四个方向（左、右、上、下）中每个方向上都至少存在一个建筑，则称该建筑 被覆盖 。
 * 返回 被覆盖 的建筑数量。
 * 2 <= n <= 10^5
 * 1 <= buildings.length <= 10^5
 * buildings[i] = [x, y]
 * 1 <= x, y <= n
 * buildings 中所有坐标均 唯一 。
 */
public class Solution {

    public int countCoveredBuildings(int n, int[][] buildings) {
        int[] rowMin = new int[n + 1];
        int[] rowMax = new int[n + 1];
        int[] colMin = new int[n + 1];
        int[] colMax = new int[n + 1];
        Arrays.fill(rowMin, n + 1);
        Arrays.fill(colMin, n + 1);
        for (int[] building : buildings) {
            int x = building[0], y = building[1];
            rowMin[y] = Math.min(rowMin[y], x);
            rowMax[y] = Math.max(rowMax[y], x);
            colMin[x] = Math.min(colMin[x], y);
            colMax[x] = Math.max(colMax[x], y);
        }
        int res = 0;
        for (int[] building : buildings) {
            int x = building[0], y = building[1];
            if (rowMin[y] < x && x < rowMax[y] && colMin[x] < y && y < colMax[x]) {
                res++;
            }
        }
        return res;
    }

}
