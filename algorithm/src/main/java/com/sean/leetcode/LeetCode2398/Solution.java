package com.sean.leetcode.LeetCode2398;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author xionghaiyang
 * @Date 2024-09-13 10:48
 * @Description https://leetcode.cn/problems/maximum-number-of-robots-within-budget/
 * 2398. 预算内的最多机器人数目
 * 你有 n 个机器人，给你两个下标从 0 开始的整数数组 chargeTimes 和 runningCosts ，两者长度都为 n 。
 * 第 i 个机器人充电时间为 chargeTimes[i] 单位时间，花费 runningCosts[i] 单位时间运行。
 * 再给你一个整数 budget 。
 * 运行 k 个机器人 总开销 是 max(chargeTimes) + k * sum(runningCosts) ，
 * 其中 max(chargeTimes) 是这 k 个机器人中最大充电时间，
 * sum(runningCosts) 是这 k 个机器人的运行时间之和。
 * 请你返回在 不超过 budget 的前提下，你 最多 可以 连续 运行的机器人数目为多少。
 */
public class Solution {

    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int res = 0;
        int n = chargeTimes.length;
        long runningCostSum = 0;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n; i++) {
            runningCostSum += runningCosts[i];
            while (!q.isEmpty() && chargeTimes[q.peekLast()] <= chargeTimes[i]) {
                q.pollLast();
            }
            q.addLast(i);
            while (j <= i && (i - j + 1) * runningCostSum + chargeTimes[q.peekFirst()] > budget) {
                if (!q.isEmpty() && q.peekFirst() == j) {
                    q.pollFirst();
                }
                runningCostSum -= runningCosts[j];
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

}
