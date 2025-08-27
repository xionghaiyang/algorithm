package com.sean.leetcode.LeetCode3446;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-08-28 07:16
 * @Description https://leetcode.cn/problems/sort-matrix-by-diagonals
 * 3446. 按对角线进行矩阵排序
 * 给你一个大小为 n x n 的整数方阵 grid。
 * 返回一个经过如下调整的矩阵：
 * 左下角三角形（包括中间对角线）的对角线按 非递增顺序 排序。
 * 右上角三角形 的对角线按 非递减顺序 排序。
 * grid.length == grid[i].length == n
 * 1 <= n <= 10
 * -10^5 <= grid[i][j] <= 10^5
 */
public class Solution {

    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        for (int k = 0; k < n * 2 - 1; k++) {
            int x = k < n ? n - 1 - k : 0;
            int y = k < n ? 0 : k - (n - 1);
            List<Integer> list = new ArrayList<>();
            for (int i = x, j = y; i < n && j < n; i++, j++) {
                list.add(grid[i][j]);
            }
            if (k < n) {
                list.sort(Collections.reverseOrder());
            } else {
                Collections.sort(list);
            }
            for (int i = x, j = y, index = 0; i < n && j < n && index < list.size(); i++, j++, index++) {
                grid[i][j] = list.get(index);
            }
        }
        return grid;
    }

}
