package com.sean.leetcode.LeetCode1792;

import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-20 08:44
 * @Description: https://leetcode.cn/problems/maximum-average-pass-ratio
 * 1792. 最大平均通过率
 * 一所学校里有一些班级，每个班级里有一些学生，现在每个班都会进行一场期末考试。
 * 给你一个二维数组 classes ，其中 classes[i] = [passi, totali] ，表示你提前知道了第 i 个班级总共有 totali 个学生，其中只有 passi 个学生可以通过考试。
 * 给你一个整数 extraStudents ，表示额外有 extraStudents 个聪明的学生，他们 一定 能通过任何班级的期末考。
 * 你需要给这 extraStudents 个学生每人都安排一个班级，使得 所有 班级的 平均 通过率 最大 。
 * 一个班级的 通过率 等于这个班级通过考试的学生人数除以这个班级的总人数。
 * 平均通过率 是所有班级的通过率之和除以班级数目。
 * 请你返回在安排这 extraStudents 个学生去对应班级后的 最大 平均通过率。
 * 与标准答案误差范围在 10^-5 以内的结果都会视为正确结果。
 * 1 <= classes.length <= 10^5
 * classes[i].length == 2
 * 1 <= passi <= totali <= 10^5
 * 1 <= extraStudents <= 10^5
 */
public class Solution {

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Long.compare((long) (b[1] - b[0]) * a[1] * (a[1] + 1), (long) (a[1] - a[0]) * b[1] * (b[1] + 1)));
        for (int[] c : classes) {
            heap.offer(c);
        }
        while (extraStudents-- > 0) {
            int[] top = heap.poll();
            top[0]++;
            top[1]++;
            heap.offer(top);
        }
        double res = 0;
        int n = heap.size();
        while (!heap.isEmpty()) {
            int[] top = heap.poll();
            res += 1.0 * top[0] / top[1];
        }
        return res / n;
    }

}
