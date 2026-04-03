package com.sean.leetcode.LeetCode3661;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-04-03 06:20
 * @Description https://leetcode.cn/problems/maximum-walls-destroyed-by-robots
 * 3661. 可以被机器人摧毁的最大墙壁数目
 * 一条无限长的直线上分布着一些机器人和墙壁。
 * 给你整数数组 robots ，distance 和 walls：
 * robots[i] 是第 i 个机器人的位置。
 * distance[i] 是第 i 个机器人的子弹可以行进的 最大 距离。
 * walls[j] 是第 j 堵墙的位置。
 * 每个机器人有 一颗 子弹，可以向左或向右发射，最远距离为 distance[i] 米。
 * 子弹会摧毁其射程内路径上的每一堵墙。
 * 机器人是固定的障碍物：如果子弹在到达墙壁前击中另一个机器人，它会 立即 在该机器人处停止，无法继续前进。
 * 返回机器人可以摧毁墙壁的 最大 数量。
 * 注意：
 * 墙壁和机器人可能在同一位置；该位置的墙壁可以被该位置的机器人摧毁。
 * 机器人不会被子弹摧毁。
 * 1 <= robots.length == distance.length <= 10^5
 * 1 <= walls.length <= 10^5
 * 1 <= robots[i], walls[j] <= 10^9
 * 1 <= distance[i] <= 10^5
 * robots 中的所有值都是 互不相同 的
 * walls 中的所有值都是 互不相同 的
 */
public class Solution {

    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[][] robot = new int[n][2];
        for (int i = 0; i < n; i++) {
            robot[i][0] = robots[i];
            robot[i][1] = distance[i];
        }
        Arrays.sort(robot, (a, b) -> a[0] - b[0]);
        Arrays.sort(walls);
        int[][] memo = new int[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return process(robot, walls, memo, n - 1, 1);
    }

    //j = 0 表示机器人i+1往左射击，j = 1表示机器人i+1往右射击
    private int process(int[][] robot, int[] walls, int[][] memo, int i, int j) {
        if (i < 0) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int x = robot[i][0], d = robot[i][1];
        //往左射
        int leftX = x - d;
        if (i > 0) {
            leftX = Math.max(leftX, robot[i - 1][0] + 1);
        }
        int left = binarySearch(walls, leftX);
        int cur = binarySearch(walls, x + 1);
        int resLeft = process(robot, walls, memo, i - 1, 0) + cur - left;
        //往右射
        int rightX = x + d;
        if (i + 1 < robot.length) {
            int x2 = robot[i + 1][0];
            if (j == 0) {
                x2 -= robot[i + 1][1];
            }
            rightX = Math.min(rightX, x2 - 1);
        }
        int right = binarySearch(walls, rightX + 1);
        cur = binarySearch(walls, x);
        int resRight = process(robot, walls, memo, i - 1, 1) + right - cur;
        return memo[i][j] = Math.max(resLeft, resRight);
    }

    private int binarySearch(int[] nums, int target) {
        int res = nums.length, left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
