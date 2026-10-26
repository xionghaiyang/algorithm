package com.sean.leetcode.LeetCode3963;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-27 06:17
 * @Description: https://leetcode.cn/problems/create-grid-with-exactly-one-path
 * 3963. 构造恰好一条路径的网格
 * 给你两个整数 m 和 n，分别表示网格的行数和列数。
 * 请你构造 任意 一个只包含字符 '.' 和 '#' 的 m x n 网格，其中：
 * '.' 表示空单元格。
 * '#' 表示障碍物单元格。
 * 有效路径 是满足以下条件的空单元格序列：
 * 从左上角单元格 (0, 0) 开始。
 * 在右下角单元格 (m - 1, n - 1) 结束。
 * 只能向：
 * 右移动，从 (i, j) 到 (i, j + 1)，或者
 * 下移动，从 (i, j) 到 (i + 1, j)。
 * 返回任意一个从左上角到右下角 恰好只有一条有效路径 的网格。
 * 1 <= m, n <= 25
 */
public class Solution {

    public String[] createGrid(int m, int n) {
        String[] res = new String[m];
        char[] str = new char[n];
        Arrays.fill(str, '.');
        res[0] = String.valueOf(str);
        Arrays.fill(str, 0, n - 1, '#');
        String s = String.valueOf(str);
        for (int i = 1; i < m; i++) {
            res[i] = s;
        }
        return res;
    }

}
