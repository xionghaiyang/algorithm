package com.sean.leetcode.LeetCode1914;

import java.util.ArrayList;
import java.util.List;

/**
 * @Autnor: xionghaiyang
 * @Date: 2026-05-09 21:25
 * @Description: https://leetcode.cn/problems/cyclically-rotating-a-grid
 * 1914. 循环轮转矩阵
 * 给你一个大小为 m x n 的整数矩阵 grid​​​ ，其中 m 和 n 都是 偶数 ；另给你一个整数 k 。
 * 矩阵由若干层组成，如下图所示，每种颜色代表一层：
 * 矩阵的循环轮转是通过分别循环轮转矩阵中的每一层完成的。
 * 在对某一层进行一次循环旋转操作时，层中的每一个元素将会取代其 逆时针 方向的相邻元素。
 * 轮转示例如下：
 * 返回执行 k 次循环轮转操作后的矩阵。
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 50
 * m 和 n 都是 偶数
 * 1 <= grid[i][j] <= 5000
 * 1 <= k <= 10^9
 */
public class Solution {

    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int layers = Math.min(m / 2, n / 2);
        for (int layer = 0; layer < layers; layer++) {
            List<Integer> r = new ArrayList<>();
            List<Integer> c = new ArrayList<>();
            List<Integer> val = new ArrayList<>();
            //左
            for (int i = layer; i < m - layer - 1; i++) {
                r.add(i);
                c.add(layer);
                val.add(grid[i][layer]);
            }
            //下
            for (int j = layer; j < n - layer - 1; j++) {
                r.add(m - layer - 1);
                c.add(j);
                val.add(grid[m - layer - 1][j]);
            }
            //右
            for (int i = m - layer - 1; i > layer; i--) {
                r.add(i);
                c.add(n - layer - 1);
                val.add(grid[i][n - layer - 1]);
            }
            //上
            for (int j = n - layer - 1; j > layer; j--) {
                r.add(layer);
                c.add(j);
                val.add(grid[layer][j]);
            }
            int total = val.size();
            int kk = k % total;
            for (int i = 0; i < total; i++) {
                int index = (i + total - kk) % total;
                grid[r.get(i)][c.get(i)] = val.get(index);
            }
        }
        return grid;
    }

}
