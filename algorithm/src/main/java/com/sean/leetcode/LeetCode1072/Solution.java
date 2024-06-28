package com.sean.leetcode.LeetCode1072;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-15 08:12
 * @Description: https://leetcode.cn/problems/flip-columns-for-maximum-number-of-equal-rows/
 * 1072. 按列翻转得到最大值等行数
 * 给定 m x n 矩阵 matrix 。
 * 你可以从中选出任意数量的列并翻转其上的 每个 单元格。
 * （即翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。）
 * 返回 经过一些翻转后，行与行之间所有值都相等的最大行数 。
 */
public class Solution {

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            char[] arr = new char[n];
            for (int j = 0; j < n; j++) {
                //matrix[i][0]为1时，翻转第i行
                arr[j] = (char) ('0' + (matrix[i][j] ^ matrix[i][0]));
            }
            String s = new String(arr);
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        int res = 0;
        for (int value : map.values()) {
            res = Math.max(res, value);
        }
        return res;
    }

}
