package com.sean.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class LeetCode85 {

    public int maximalRectangle1(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int columns = matrix[0].length;
        int[][] left = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                int width = left[i][j];
                int area = width;
                for (int k = i - 1; k >= 0; k--) {
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, (i - k + 1) * width);
                }
                res = Math.max(res, area);
            }
        }
        return res;
    }

    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int columns = matrix[0].length;
        int[][] left = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }
        int res = 0;
        for (int j = 0; j < columns; j++) {
            int[] up = new int[rows];
            int[] down = new int[rows];
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < rows; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = rows - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? rows : stack.peek();
                stack.push(i);
            }
            for (int i = 0; i < rows; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                res = Math.max(res, area);
            }
        }
        return res;
    }

}
