package com.sean.leetcode.LeetCode3568;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-01 05:51
 * @Description: https://leetcode.cn/problems/minimum-moves-to-clean-the-classroom
 * 3568. 清理教室的最少移动
 * 给你一个 m x n 的网格图 classroom，其中一个学生志愿者负责清理散布在教室里的垃圾。
 * 网格图中的每个单元格是以下字符之一：
 * 'S' ：学生的起始位置
 * 'L' ：必须收集的垃圾（收集后，该单元格变为空白）
 * 'R' ：重置区域，可以将学生的能量恢复到最大值，无论学生当前的能量是多少（可以多次使用）
 * 'X' ：学生无法通过的障碍物
 * '.' ：空白空间
 * 同时给你一个整数 energy，表示学生的最大能量容量。
 * 学生从起始位置 'S' 开始，带着 energy 的能量出发。
 * 每次移动到相邻的单元格（上、下、左或右）会消耗 1 单位能量。
 * 如果能量为 0，学生此时只有处在 'R' 格子时可以继续移动，此区域会将能量恢复到 最大 能量值 energy。
 * 返回收集所有垃圾所需的 最少 移动次数，如果无法完成，返回 -1。
 * 1 <= m == classroom.length <= 20
 * 1 <= n == classroom[i].length <= 20
 * classroom[i][j] 是 'S'、'L'、'R'、'X' 或 '.' 之一
 * 1 <= energy <= 50
 * 网格图中恰好有 一个 'S'。
 * 网格图中 最多 有 10 个 'L' 单元格。
 */
public class Solution {

    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public class Info {
        private int x;
        private int y;
        private int e;
        private int mask;

        public Info(int x, int y, int e, int mask) {
            this.x = x;
            this.y = y;
            this.e = e;
            this.mask = mask;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        int[][] index = new int[m][n];
        int sx = 0, sy = 0, cntL = 0;
        for (int i = 0; i < m; i++) {
            String row = classroom[i];
            for (int j = 0; j < n; j++) {
                char c = row.charAt(j);
                if (c == 'L') {
                    index[i][j] = 1 << cntL++;
                } else if (c == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }
        int u = 1 << cntL;
        boolean[][][][] vis = new boolean[m][n][energy + 1][u];
        vis[sx][sy][energy][0] = true;
        List<Info> list = new ArrayList<>();
        list.add(new Info(sx, sy, energy, 0));
        for (int res = 0; !list.isEmpty(); res++) {
            List<Info> t = list;
            list = new ArrayList<>();
            for (Info info : t) {
                if (info.mask == u - 1) {
                    return res;
                }
                if (info.e == 0) {
                    continue;
                }
                for (int[] dir : DIRS) {
                    int x = info.x + dir[0], y = info.y + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && classroom[x].charAt(y) != 'X') {
                        int newE = classroom[x].charAt(y) == 'R' ? energy : info.e - 1;
                        int newMask = info.mask | index[x][y];
                        if (!vis[x][y][newE][newMask]) {
                            vis[x][y][newE][newMask] = true;
                            list.add(new Info(x,y,newE,newMask));
                        }
                    }
                }
            }
        }
        return -1;
    }

}
