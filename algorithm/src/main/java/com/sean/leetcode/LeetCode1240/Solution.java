package com.sean.leetcode.LeetCode1240;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-08 08:01
 * @Description: https://leetcode.cn/problems/tiling-a-rectangle-with-the-fewest-squares/
 * 1240. 铺瓷砖
 * 你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。
 * 房子的客厅大小为 n x m，为保持极简的风格，需要使用尽可能少的 正方形 瓷砖来铺盖地面。
 * 假设正方形瓷砖的规格不限，边长都是整数。
 * 请你帮设计师计算一下，最少需要用到多少块方形瓷砖？
 */
public class Solution {

    int res;

    public int tilingRectangle(int n, int m) {
        res = Math.max(n, m);
        boolean[][] grid = new boolean[n][m];
        process(0, 0, grid, 0);
        return res;
    }

    private void process(int x, int y, boolean[][] grid, int cnt) {
        int n = grid.length;
        int m = grid[0].length;
        if (cnt >= res) {
            return;
        }
        if (x >= n) {
            res = cnt;
            return;
        }
        //检测下一行
        if (y >= m) {
            process(x + 1, 0, grid, cnt);
            return;
        }
        //如当前已经被覆盖，则直接尝试下一个位置
        if (grid[x][y]) {
            process(x, y + 1, grid, cnt);
            return;
        }
        for (int k = Math.min(n - x, m - y); k >= 1 && isAvailable(grid, x, y, k); k--) {
            //将长度为k的正方形区域标记覆盖
            fillUp(grid, x, y, k, true);
            //跳过k个位置开始检测
            process(x, y + k, grid, cnt + 1);
            fillUp(grid, x, y, k, false);
        }
    }

    private boolean isAvailable(boolean[][] grid, int x, int y, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (grid[x + i][y + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void fillUp(boolean[][] grid, int x, int y, int k, boolean val) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                grid[x + i][y + j] = val;
            }
        }
    }

}
