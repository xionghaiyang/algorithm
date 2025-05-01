package com.sean.leetcode.LeetCode2071;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-05-01 08:25
 * @Description https://leetcode.cn/problems/maximum-number-of-tasks-you-can-assign
 * 2071. 你可以安排的最多任务数目
 * 给你 n 个任务和 m 个工人。
 * 每个任务需要一定的力量值才能完成，需要的力量值保存在下标从 0 开始的整数数组 tasks 中，第 i 个任务需要 tasks[i] 的力量才能完成。
 * 每个工人的力量值保存在下标从 0 开始的整数数组 workers 中，第 j 个工人的力量值为 workers[j] 。
 * 每个工人只能完成 一个 任务，且力量值需要 大于等于 该任务的力量要求值（即 workers[j] >= tasks[i] ）。
 * 除此以外，你还有 pills 个神奇药丸，可以给 一个工人的力量值 增加 strength 。
 * 你可以决定给哪些工人使用药丸，但每个工人 最多 只能使用 一片 药丸。
 * 给你下标从 0 开始的整数数组tasks 和 workers 以及两个整数 pills 和 strength ，请你返回 最多 有多少个任务可以被完成。
 * n == tasks.length
 * m == workers.length
 * 1 <= n, m <= 5 * 10^4
 * 0 <= pills <= m
 * 0 <= tasks[i], workers[j], strength <= 10^9
 */
public class Solution {

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int mn = Math.min(tasks.length, workers.length);
        int[] validTasks = new int[mn];
        int left = 0;
        int right = mn + 1;
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (check(tasks, workers, pills, strength, mid, validTasks)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    //用最强的k名工人，完成最简单的k个任务
    private boolean check(int[] tasks, int[] workers, int pills, int strength, int k, int[] validTasks) {
        int head = 0;
        int tail = 0;
        int i = 0;
        for (int j = workers.length - k; j < workers.length; j++) {
            int w = workers[j];
            //在吃药的情况下，把能完成的任务记录到validTasks
            while (i < k && tasks[i] <= w + strength) {
                validTasks[tail++] = tasks[i++];
            }
            //即使吃药也无法完成任务
            if (head == tail) {
                return false;
            }
            //无需吃药就能完成（最简单的）任务
            if (w >= validTasks[head]) {
                head++;
                continue;
            }
            //必须吃药
            if (pills == 0) {
                return false;
            }
            pills--;
            //完成（能完成的）最难的任务
            tail--;
        }
        return true;
    }

}
