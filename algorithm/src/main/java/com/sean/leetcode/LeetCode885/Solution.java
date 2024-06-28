package com.sean.leetcode.LeetCode885;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-29 11:43
 * @Description: https://leetcode.cn/problems/spiral-matrix-iii/description/
 * 885. 螺旋矩阵 III
 * 在 rows x cols 的网格上，你从单元格 (rStart, cStart) 面朝东面开始。
 * 网格的西北角位于第一行第一列，网格的东南角位于最后一行最后一列。
 * 你需要以顺时针按螺旋状行走，访问此网格中的每个位置。
 * 每当移动到网格的边界之外时，需要继续在网格之外行走（但稍后可能会返回到网格边界）。
 * 最终，我们到过网格的所有 rows x cols 个空间。
 * 按照访问顺序返回表示网格位置的坐标列表。
 */
public class Solution {

    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        int index = 0;
        res[index++] = new int[]{r0, c0};
        int step = 0;
        int x = r0, y = c0;
        while (index < R * C) {
            step++;
            //向右
            for (int i = 0; i < step; i++) {
                y++;
                if (x >= 0 && x < R && y >= 0 && y < C) {
                    res[index++] = new int[]{x, y};
                }
            }
            //向下
            for (int i = 0; i < step; i++) {
                x++;
                if (x >= 0 && x < R && y >= 0 && y < C) {
                    res[index++] = new int[]{x, y};
                }
            }
            step++;
            //向左
            for (int i = 0; i < step; i++) {
                y--;
                if (x >= 0 && x < R && y >= 0 && y < C) {
                    res[index++] = new int[]{x, y};
                }
            }
            //向上
            for (int i = 0; i < step; i++) {
                x--;
                if (x >= 0 && x < R && y >= 0 && y < C) {
                    res[index++] = new int[]{x, y};
                }
            }
        }
        return res;
    }

}
