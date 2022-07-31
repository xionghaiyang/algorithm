package com.sean.leetcode;

public class LeetCode498 {

    public static int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] res = new int[m * n];
        int index = 0;
        for (int i = 0; i < m + n - 1; i++) {
            if (i % 2 == 1) {//由上至下
                int x = i < n ? 0 : i - n + 1;
                int y = i < n ? i : n - 1;
                while (x < m && y >= 0) {
                    res[index] = mat[x][y];
                    index++;
                    x++;
                    y--;
                }
            } else {//由下至上
                int x = i < m ? i : m - 1;
                int y = i < m ? 0 : i - m + 1;
                while (x >= 0 && y < n) {
                    res[index] = mat[x][y];
                    index++;
                    x--;
                    y++;
                }
            }
        }
        return res;
    }

}
