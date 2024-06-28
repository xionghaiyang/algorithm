package com.sean.leetcode;

public class LeetCode1252 {

    public int oddCells(int m, int n, int[][] indices) {
        int[] rows = new int[m];
        int[] colums = new int[n];
        for (int i = 0; i < indices.length; i++) {
            rows[indices[i][0]]++;
            colums[indices[i][1]]++;
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((rows[i] + colums[j]) % 2 == 1) {
                    res++;
                }
            }
        }
        return res;
    }

}
