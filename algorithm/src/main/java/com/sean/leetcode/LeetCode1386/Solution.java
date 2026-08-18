package com.sean.leetcode.LeetCode1386;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-19 05:55
 * @Description: https://leetcode.cn/problems/cinema-seat-allocation
 * 1386. 安排电影院座位
 * 如上图所示，电影院的观影厅中有 n 行座位，行编号从 1 到 n ，且每一行内总共有 10 个座位，列编号从 1 到 10 。
 * 给定一个二维数组 reservedSeats ，其中 reservedSeats[i] = [rowi, seati] 表示第 rowi 行的座位 seati 已经被预定。
 * 四人小组必须被安排在同一排的四个座位上。
 * 该小组可以坐在以下座位块之一：
 * 座位 2, 3, 4, 5
 * 座位 4, 5, 6, 7
 * 座位 6, 7, 8, 9
 * 只有当该块中的所有座位都 没有 被预订时，才能使用该块。
 * 每个座位 最多 只能分配给一个小组。
 * 返回一个整数，表示可以分配的 最大 四人小组数量。
 * 1 <= n <= 10^9
 * 1 <= reservedSeats.length <= min(10 * n, 10^4)
 * reservedSeats[i] == [rowi, seati]
 * 1 <= rowi <= n
 * 1 <= seati <= 10
 * 所有 reservedSeats[i] 都是互不相同的。
 */
public class Solution {

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> seats = new HashMap<>();
        for (int[] r : reservedSeats) {
            int row = r[0], seat = r[1];
            if (2 <= seat && seat <= 9) {
                seats.merge(row, 1 << (seat - 2), (a, b) -> a | b);
            }
        }
        int res = (n - seats.size()) * 2;
        for (int status : seats.values()) {
            if ((status & 0b1111) == 0 || (status & 0b111100) == 0 || (status & 0b11110000) == 0) {
                res++;
            }
        }
        return res;
    }

}
