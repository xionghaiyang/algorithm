package com.sean.leetcode.LeetCode1041;

import java.util.Objects;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-11 08:10
 * @Description: https://leetcode.cn/problems/robot-bounded-in-circle/
 * 1041. 困于环中的机器人
 * 在无限的平面上，机器人最初位于 (0, 0) 处，面朝北方。注意:
 * 北方向 是y轴的正方向。
 * 南方向 是y轴的负方向。
 * 东方向 是x轴的正方向。
 * 西方向 是x轴的负方向。
 * 机器人可以接受下列三条指令之一：
 * "G"：直走 1 个单位
 * "L"：左转 90 度
 * "R"：右转 90 度
 * 机器人按顺序执行指令 instructions，并一直重复它们。
 * 只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 false。
 */
public class Solution {

    class Robot {
        int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x;
        int y;
        int directIndex;

        public Robot(int x, int y, int directIndex) {
            this.x = x;
            this.y = y;
            this.directIndex = directIndex;
        }

        public void go() {
            x += direct[directIndex][0];
            y += direct[directIndex][1];
        }

        public void left() {
            directIndex = (directIndex + 3) % 4;
        }

        public void right() {
            directIndex = (directIndex + 1) % 4;
        }
    }

    public boolean isRobotBounded1(String instructions) {
        Robot robot = new Robot(0, 0, 0);
        int n = instructions.length();
        for (int j = 0; j < n; j++) {
            char c = instructions.charAt(j);
            if (c == 'G') {
                robot.go();
            } else if (c == 'L') {
                robot.left();
            } else if (c == 'R') {
                robot.right();
            }
        }
        return robot.directIndex != 0 || (robot.x == 0 && robot.y == 0);
    }

    public boolean isRobotBounded(String instructions) {
        int x = 0;
        int y = 0;
        int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directIndex = 0;
        int n = instructions.length();
        for (int i = 0; i < n; i++) {
            char c = instructions.charAt(i);
            if (c == 'G') {
                x += direct[directIndex][0];
                y += direct[directIndex][1];
            } else if (c == 'L') {
                directIndex = (directIndex + 3) % 4;
            } else if (c == 'R') {
                directIndex = (directIndex + 1) % 4;
            }
        }
        return directIndex != 0 || (x == 0 && y == 0);
    }

}
