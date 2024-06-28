package com.sean.leetcode.LeetCode1349;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-26 12:27
 * @Description: https://leetcode.cn/problems/maximum-students-taking-exam/description/
 * 1349. 参加考试的最大学生数
 * 给你一个 m * n 的矩阵 seats 表示教室中的座位分布。
 * 如果座位是坏的（不可用），就用 '#' 表示；否则，用 '.' 表示。
 * 学生可以看到左侧、右侧、左上、右上这四个方向上紧邻他的学生的答卷，但是看不到直接坐在他前面或者后面的学生的答卷。
 * 请你计算并返回该考场可以容纳的同时参加考试且无法作弊的 最大 学生人数。
 * 学生必须坐在状况良好的座位上。
 */
public class Solution {

    Map<Integer, Integer> map = new HashMap<>();

    public int maxStudents(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;
        int res = 0;
        for (int i = 0; i < 1 << n; i++) {
            res = Math.max(res, dp(seats, m - 1, i));
        }
        return res;
    }

    private int dp(char[][] seats, int row, int status) {
        int n = seats[0].length;
        int key = (row << n) + status;
        if (!map.containsKey(key)) {
            if (!isSingleRowCompliant(seats, status, n, row)) {
                map.put(key, Integer.MIN_VALUE);
                return Integer.MIN_VALUE;
            }
            int students = Integer.bitCount(status);
            if (row == 0) {
                map.put(key, students);
                return students;
            }
            int max = 0;
            for (int upperRowStatus = 0; upperRowStatus < 1 << n; upperRowStatus++) {
                if (isCrossRowsCompliant(status, upperRowStatus, n)) {
                    max = Math.max(max, dp(seats, row - 1, upperRowStatus));
                }
            }
            map.put(key, students + max);
        }
        return map.get(key);
    }

    private boolean isSingleRowCompliant(char[][] seats, int status, int n, int row) {
        for (int i = 0; i < n; i++) {
            if (((status >> i) & 1) == 1) {
                if (seats[row][i] == '#') {
                    return false;
                }
                if (i > 0 && ((status >> (i - 1)) & 1) == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCrossRowsCompliant(int status, int upperRowStatus, int n) {
        for (int j = 0; j < n; j++) {
            if (((status >> j) & 1) == 1) {
                if (j > 0 && ((upperRowStatus >> (j - 1)) & 1) == 1) {
                    return false;
                }
                if (j < n - 1 && ((upperRowStatus >> (j + 1)) & 1) == 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
