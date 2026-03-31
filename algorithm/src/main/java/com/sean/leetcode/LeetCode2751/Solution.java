package com.sean.leetcode.LeetCode2751;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-04-01 06:35
 * @Description https://leetcode.cn/problems/robot-collisions
 * 2751. 机器人碰撞
 * 现有 n 个机器人，编号从 1 开始，每个机器人包含在路线上的位置、健康度和移动方向。
 * 给你下标从 0 开始的两个整数数组 positions、healths 和一个字符串 directions（directions[i] 为 'L' 表示 向左 或 'R' 表示 向右）。
 * positions 中的所有整数 互不相同 。
 * 所有机器人以 相同速度 同时 沿给定方向在路线上移动。
 * 如果两个机器人移动到相同位置，则会发生 碰撞 。
 * 如果两个机器人发生碰撞，则将 健康度较低 的机器人从路线中 移除 ，并且另一个机器人的健康度 减少 1 。
 * 幸存下来的机器人将会继续沿着与之前 相同 的方向前进。
 * 如果两个机器人的健康度相同，则将二者都从路线中移除。
 * 请你确定全部碰撞后幸存下的所有机器人的 健康度 ，并按照原来机器人编号的顺序排列。
 * 即机器人 1 （如果幸存）的最终健康度，机器人 2 （如果幸存）的最终健康度等。
 * 如果不存在幸存的机器人，则返回空数组。
 * 在不再发生任何碰撞后，请你以数组形式，返回所有剩余机器人的健康度（按机器人输入中的编号顺序）。
 * 注意：位置  positions 可能是乱序的。
 * 1 <= positions.length == healths.length == directions.length == n <= 10^5
 * 1 <= positions[i], healths[i] <= 10^9
 * directions[i] == 'L' 或 directions[i] == 'R'
 * positions 中的所有值互不相同
 */
public class Solution {

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] robots = new Integer[n];
        for (int i = 0; i < n; i++) {
            robots[i] = i;
        }
        Arrays.sort(robots, (i, j) -> positions[i] - positions[j]);
        int[] stack = new int[n];
        int top = -1;
        for (int i : robots) {
            if (directions.charAt(i) == 'R') {
                stack[++top] = i;
                continue;
            }
            while (top >= 0) {
                int j = stack[top];
                if (healths[i] > healths[j]) {
                    healths[i]--;
                    healths[j] = 0;
                    top--;
                } else if (healths[i] < healths[j]) {
                    healths[i] = 0;
                    healths[j]--;
                    break;
                } else {
                    healths[i] = 0;
                    healths[j] = 0;
                    top--;
                    break;
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int health : healths) {
            if (health > 0) {
                res.add(health);
            }
        }
        return res;
    }

}
