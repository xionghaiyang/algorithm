package com.sean.leetcode.LeetCodeInterview1723;

/**
 * @Author xionghaiyang
 * @Date 2026-03-26 19:05
 * @Description https://leetcode.cn/problems/max-black-square-lcci
 * 面试题 17.23. 最大黑方阵
 * 给定一个方阵，其中每个单元(像素)非黑即白。
 * 设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。
 * 返回一个数组 [r, c, size] ，其中 r, c 分别代表子方阵左上角的行号和列号，size 是子方阵的边长。
 * 若有多个满足条件的子方阵，返回 r 最小的，若 r 相同，返回 c 最小的子方阵。
 * 若无满足条件的子方阵，返回空数组。
 * matrix.length == matrix[0].length <= 200
 */
public class Solution {

    private int n;

    public int[] findSquare(int[][] matrix) {
        n = matrix.length;
        int[][][] arr = new int[n][n][2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    arr[i][j][0] = j < n - 1 ? arr[i][j + 1][0] + 1 : 1;
                    arr[i][j][1] = i < n - 1 ? arr[i + 1][j][1] + 1 : 1;
                }
            }
        }
        int[] res = {0, 0, 0};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    int min = Math.min(arr[i][j][0], arr[i][j][1]);
                    if (min > res[2]) {
                        for (int size = min; size >= res[2] + 1; size--) {
                            if (check(arr, i, j, size)) {
                                res = new int[]{i, j, size};
                                break;
                            }
                        }
                    }
                }
            }
        }
        return res[2] == 0 ? new int[0] : res;
    }

    private boolean check(int[][][] arr, int x1, int y1, int size) {
        int x2 = x1, y2 = y1 + size - 1;
        int x3 = x1 + size - 1, y3 = y1;
        return x3 < n && y2 < n && arr[x2][y2][1] >= size && arr[x3][y3][0] >= size;
    }

}
