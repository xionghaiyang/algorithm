package com.sean.leetcode.LeetCode3588;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-06-25 12:27
 * @Description https://leetcode.cn/problems/find-maximum-area-of-a-triangle
 * 3588. 找到最大三角形面积
 * 给你一个二维数组 coords，大小为 n x 2，表示一个无限笛卡尔平面上 n 个点的坐标。
 * 找出一个 最大 三角形的 两倍 面积，其中三角形的三个顶点来自 coords 中的任意三个点，并且该三角形至少有一条边与 x 轴或 y 轴平行。
 * 严格地说，如果该三角形的最大面积为 A，则返回 2 * A。
 * 如果不存在这样的三角形，返回 -1。
 * 注意，三角形的面积 不能 为零。
 * 1 <= n == coords.length <= 10^5
 * 1 <= coords[i][0], coords[i][1] <= 10^6
 * 所有 coords[i] 都是 唯一 的。
 */
public class Solution {

    public long maxArea(int[][] coords) {
        long res = Math.max(process(coords, 0), process(coords, 1));
        return res > 0 ? res : -1;
    }

    private long process(int[][] coords, int i) {
        int minX = 1_000_000;
        int maxX = 1;
        Map<Integer, Integer> minY = new HashMap<>();
        Map<Integer, Integer> maxY = new HashMap<>();
        for (int[] coord : coords) {
            int x = coord[i];
            int y = coord[1 - i];
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY.put(x, Math.min(minY.getOrDefault(x, y), y));
            maxY.put(x, Math.max(maxY.getOrDefault(x, 1), y));
        }
        long res = 0;
        for (Map.Entry<Integer, Integer> entry : minY.entrySet()) {
            int x = entry.getKey();
            int y = entry.getValue();
            res = Math.max(res, (long) (maxY.get(x) - y) * Math.max(maxX - x, x - minX));
        }
        return res;
    }

}
