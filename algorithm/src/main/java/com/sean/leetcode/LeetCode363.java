package com.sean.leetcode;

import java.util.TreeSet;

public class LeetCode363 {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int res = Integer.MIN_VALUE;
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            int[] sum = new int[columns];
            for (int j = i; j < rows; j++) {
                for (int c = 0; c < columns; c++) {
                    sum[c] += matrix[j][c];
                }
                TreeSet<Integer> sumSet = new TreeSet<>();
                sumSet.add(0);
                int s = 0;
                for (int v : sum) {
                    s += v;
                    Integer ceil = sumSet.ceiling(s - k);
                    if (ceil != null) {
                        res = Math.max(res, s - ceil);
                    }
                    sumSet.add(s);
                }
            }
        }
        return res;
    }

}
