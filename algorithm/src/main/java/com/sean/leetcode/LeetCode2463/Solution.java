package com.sean.leetcode.LeetCode2463;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-04-14 08:27
 * @Description https://leetcode.cn/problems/minimum-total-distance-traveled
 * 2463. 最小移动总距离
 * X 轴上有一些机器人和工厂。
 * 给你一个整数数组 robot ，其中 robot[i] 是第 i 个机器人的位置。
 * 再给你一个二维整数数组 factory ，其中 factory[j] = [positionj, limitj] ，表示第 j 个工厂的位置在 positionj ，且第 j 个工厂最多可以修理 limitj 个机器人。
 * 每个机器人所在的位置 互不相同 。
 * 每个工厂所在的位置也 互不相同 。
 * 注意一个机器人可能一开始跟一个工厂在 相同的位置 。
 * 所有机器人一开始都是坏的，他们会沿着设定的方向一直移动。
 * 设定的方向要么是 X 轴的正方向，要么是 X 轴的负方向。
 * 当一个机器人经过一个没达到上限的工厂时，这个工厂会维修这个机器人，且机器人停止移动。
 * 任何时刻，你都可以设置 部分 机器人的移动方向。
 * 你的目标是最小化所有机器人总的移动距离。
 * 请你返回所有机器人移动的最小总距离。
 * 测试数据保证所有机器人都可以被维修。
 * 所有机器人移动速度相同。
 * 如果两个机器人移动方向相同，它们永远不会碰撞。
 * 如果两个机器人迎面相遇，它们也不会碰撞，它们彼此之间会擦肩而过。
 * 如果一个机器人经过了一个已经达到上限的工厂，机器人会当作工厂不存在，继续移动。
 * 机器人从位置 x 到位置 y 的移动距离为 |y - x| 。
 */
public class Solution {

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);
        int n = robot.size(), m = factory.length;
        long[][] memo = new long[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return process(robot, factory, memo, n - 1, m - 1);
    }

    private long process(List<Integer> robot, int[][] factory, long[][] memo, int i, int j) {
        if (i < 0) {
            return 0;
        }
        if (j < 0) {
            return Long.MAX_VALUE / 2;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        long res = process(robot, factory, memo, i, j - 1);
        int position = factory[j][0], limit = factory[j][1];
        long sum = 0;
        for (int k = 1; k <= Math.min(i + 1, limit); k++) {
            sum += Math.abs(robot.get(i - k + 1) - position);
            res = Math.min(res, process(robot, factory, memo, i - k, j - 1) + sum);
        }
        return memo[i][j] = res;
    }

}
