package com.sean.leetcode.LeetCode621;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-08 13:01
 * @Description: https://leetcode.cn/problems/task-scheduler
 * 621. 任务调度器
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表，用字母 A 到 Z 表示，以及一个冷却时间 n。
 * 每个周期或时间间隔允许完成一项任务。
 * 任务可以按任何顺序完成，但有一个限制：两个 相同种类 的任务之间必须有长度为 n 的冷却时间。
 * 返回完成所有任务所需要的 最短时间间隔 。
 * 1 <= tasks.length <= 10^4
 * tasks[i] 是大写英文字母
 * 0 <= n <= 100
 */
public class Solution {

    public int leastInterval(char[] tasks, int n) {
        int m = tasks.length;
        int[] counts = new int[26];
        for (int i = 0; i < m; i++) {
            counts[tasks[i] - 'A']++;
        }
        int maxF = 0;
        for (int count : counts) {
            if (count > maxF) {
                maxF = count;
            }
        }
        int num = 0;
        for (int count : counts) {
            if (count == maxF) {
                num++;
            }
        }
        return Math.max((maxF - 1) * (n + 1) + num, m);
    }

}
