package com.sean.leetcode.LeetCode874;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-04-06 08:11
 * @Description https://leetcode.cn/problems/walking-robot-simulation
 * 874. 模拟行走机器人
 * 机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。
 * 该机器人可以接收以下三种类型的命令 commands ：
 * -2 ：向左转 90 度
 * -1 ：向右转 90 度
 * 1 <= x <= 9 ：向前移动 x 个单位长度
 * 在网格上有一些格子被视为障碍物 obstacles 。
 * 第 i 个障碍物位于网格点  obstacles[i] = (xi, yi) 。
 * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，并继续执行下一个命令。
 * 返回机器人距离原点的 最大欧式距离 的 平方 。（即，如果距离为 5 ，则返回 25 ）
 * 北方表示 +Y 方向。
 * 东方表示 +X 方向。
 * 南方表示 -Y 方向。
 * 西方表示 -X 方向。
 * 原点 [0,0] 可能会有障碍物。
 * 1 <= commands.length <= 10^4
 * commands[i] 的值可以取 -2、-1 或者是范围 [1, 9] 内的一个整数。
 * 0 <= obstacles.length <= 10^4
 * -3 * 10^4 <= xi, yi <= 3 * 10^4
 * 答案保证小于 2^31
 */
public class Solution {

    public int robotSim(int[] commands, int[][] obstacles) {
        Map<Integer, List<Integer>> xMap = new HashMap<>();
        Map<Integer, List<Integer>> yMap = new HashMap<>();
        for (int[] obstacle : obstacles) {
            int x = obstacle[0], y = obstacle[1];
            if (!xMap.containsKey(x)) {
                xMap.put(x, new ArrayList<>());
            }
            if (!yMap.containsKey(y)) {
                yMap.put(y, new ArrayList<>());
            }
            xMap.get(x).add(y);
            yMap.get(y).add(x);
        }
        for (List<Integer> list : xMap.values()) {
            Collections.sort(list);
        }
        for (List<Integer> list : yMap.values()) {
            Collections.sort(list);
        }
        int x = 0, y = 0, direction = 0, res = 0;
        for (int command : commands) {
            if (command == -1) {
                direction = (direction + 1) % 4;
            } else if (command == -2) {
                direction = (direction + 3) % 4;
            } else {
                if (direction == 0) {
                    List<Integer> list = xMap.get(x);
                    //求大于y的第一个障碍物
                    int i = f1(list, y);
                    if (i == -1 || list.get(i) > y + command) {
                        y += command;
                    } else {
                        y = list.get(i) - 1;
                    }
                } else if (direction == 1) {
                    List<Integer> list = yMap.get(y);
                    //求大于x的第一个障碍物
                    int i = f1(list, x);
                    if (i == -1 || list.get(i) > x + command) {
                        x += command;
                    } else {
                        x = list.get(i) - 1;
                    }
                } else if (direction == 2) {
                    List<Integer> list = xMap.get(x);
                    //求小于y的第一个障碍物
                    int i = f2(list, y);
                    if (i == -1 || list.get(i) < y - command) {
                        y -= command;
                    } else {
                        y = list.get(i) + 1;
                    }
                } else {
                    List<Integer> list = yMap.get(y);
                    //求小于x的第一个障碍物
                    int i = f2(list, x);
                    if (i == -1 || list.get(i) < x - command) {
                        x -= command;
                    } else {
                        x = list.get(i) + 1;
                    }
                }
                res = Math.max(res, x * x + y * y);
            }
        }
        return res;
    }

    //大于target的第一个障碍物
    private int f1(List<Integer> list, int target) {
        if (list == null) {
            return -1;
        }
        int left = 0, right = list.size() - 1, res = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (list.get(mid) > target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    //小于target的第一个障碍物
    private int f2(List<Integer> list, int target) {
        if (list == null) {
            return -1;
        }
        int left = 0, right = list.size() - 1, res = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (list.get(mid) < target) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

}
