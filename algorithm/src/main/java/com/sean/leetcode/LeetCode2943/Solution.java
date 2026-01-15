package com.sean.leetcode.LeetCode2943;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-01-15 12:32
 * @Description https://leetcode.cn/problems/maximize-area-of-square-hole-in-grid
 * 2943. 最大化网格图中正方形空洞的面积
 * 给你两个整数 n 和 m，以及两个整数数组 hBars 和 vBars。
 * 网格由 n + 2 条水平线和 m + 2 条竖直线组成，形成 1x1 的单元格。
 * 网格中的线条从 1 开始编号。
 * 你可以从 hBars 中 删除 一些水平线条，并从 vBars 中删除一些竖直线条。
 * 注意，其他线条是固定的，无法删除。
 * 返回一个整数表示移除一些线条（可以不移除任何线条）后，网格中 正方形空洞的最大面积 。
 * 1 <= n <= 10^9
 * 1 <= m <= 10^9
 * 1 <= hBars.length <= 100
 * 2 <= hBars[i] <= n + 1
 * 1 <= vBars.length <= 100
 * 2 <= vBars[i] <= m + 1
 * hBars 中所有值互不相同。
 * vBars 中所有值互不相同。
 */
public class Solution {

    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int side = Math.min(f(hBars), f(vBars)) + 1;
        return side * side;
    }

    private int f(int[] arr) {
        Arrays.sort(arr);
        int max = 1, cnt = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1] + 1) {
                cnt++;
                max = Math.max(max, cnt);
            } else {
                cnt = 1;
            }
        }
        return max;
    }

}
