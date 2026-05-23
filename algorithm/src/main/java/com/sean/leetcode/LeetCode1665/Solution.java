package com.sean.leetcode.LeetCode1665;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-05-12 06:01
 * @Description: https://leetcode.cn/problems/minimum-initial-energy-to-finish-tasks
 * 1665. 完成所有任务的最少初始能量
 * 给你一个任务数组 tasks ，其中 tasks[i] = [actuali, minimumi] ：
 * actuali 是完成第 i 个任务 需要耗费 的实际能量。
 * minimumi 是开始第 i 个任务前需要达到的最低能量。
 * 比方说，如果任务为 [10, 12] 且你当前的能量为 11 ，那么你不能开始这个任务。
 * 如果你当前的能量为 13 ，你可以完成这个任务，且完成它后剩余能量为 3 。
 * 你可以按照 任意顺序 完成任务。
 * 请你返回完成所有任务的 最少 初始能量。
 * 1 <= tasks.length <= 10^5
 * 1 <= actual​i <= minimumi <= 10^4
 */
public class Solution {

    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        int res = 0, s = 0;
        for (int[] task : tasks) {
            int actual = task[0], minimum = task[1];
            res = Math.max(res, s + minimum);
            s += actual;
        }
        return res;
    }

}
