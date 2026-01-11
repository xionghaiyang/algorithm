package com.sean.leetcode.LeetCode85;

/**
 * @Author xionghaiyang
 * @Date 2026-01-11 10:09
 * @Description https://leetcode.cn/problems/maximal-rectangle
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * rows == matrix.length
 * cols == matrix[0].length
 * 1 <= rows, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 */
public class Solution {

    public int maximalRectangle(char[][] matrix) {
        int n = matrix[0].length;
        int[] heights = new int[n + 1];
        int res = 0;
        for (char[] row : matrix) {
            for (int j = 0; j < n; j++) {
                if (row[j] == '0') {
                    //柱子高度为0
                    heights[j] = 0;
                } else {
                    //柱子高度加1
                    heights[j]++;
                }
            }
            res = Math.max(res, largestRectangleArea(heights));
        }
        return res;
    }

    private int largestRectangleArea(int[] heights) {
        int n = heights.length;
        //用数组模拟栈
        int[] stack = new int[n];
        //栈顶下标
        int top = -1;
        //在栈中只有一个数的时候，栈顶的[下面那个数]是-1,对应left[i]=-1的情况
        stack[++top] = -1;
        int res = 0;
        for (int right = 0; right < n; right++) {
            int h = heights[right];
            while (top > 0 && heights[stack[top]] >= h) {
                //矩形的高（的下标）
                int i = stack[top--];
                //栈顶下面那个数就是left
                int left = stack[top];
                res = Math.max(res, heights[i] * (right - left - 1));
            }
            stack[++top] = right;
        }
        return res;
    }

}
