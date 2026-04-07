package com.sean.leetcode.LeetCode2069;

/**
 * @Author xionghaiyang
 * @Date 2026-04-07 06:27
 * @Description https://leetcode.cn/problems/walking-robot-simulation-ii
 * 2069. 模拟行走机器人 II
 * 给你一个在 XY 平面上的 width x height 的网格图，左下角 的格子为 (0, 0) ，右上角 的格子为 (width - 1, height - 1) 。
 * 网格图中相邻格子为四个基本方向之一（"North"，"East"，"South" 和 "West"）。
 * 一个机器人 初始 在格子 (0, 0) ，方向为 "East" 。
 * 机器人可以根据指令移动指定的 步数 。
 * 每一步，它可以执行以下操作。
 * 沿着当前方向尝试 往前一步 。
 * 如果机器人下一步将到达的格子 超出了边界 ，机器人会 逆时针 转 90 度，然后再尝试往前一步。
 * 如果机器人完成了指令要求的移动步数，它将停止移动并等待下一个指令。
 * 请你实现 Robot 类：
 * Robot(int width, int height) 初始化一个 width x height 的网格图，机器人初始在 (0, 0) ，方向朝 "East" 。
 * void step(int num) 给机器人下达前进 num 步的指令。
 * int[] getPos() 返回机器人当前所处的格子位置，用一个长度为 2 的数组 [x, y] 表示。
 * String getDir() 返回当前机器人的朝向，为 "North" ，"East" ，"South" 或者 "West" 。
 * 2 <= width, height <= 100
 * 1 <= num <= 10^5
 * step ，getPos 和 getDir 总共 调用次数不超过 10^4 次。
 */
public class Robot {

    private int width;
    private int height;
    private int x;
    private int y;
    private int k;
    private static final String[] directions = {"East", "North", "West", "South"};
    private int dirIndex;
    private boolean moved;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        x = 0;
        y = 0;
        moved = false;
        dirIndex = 3;
    }

    public void step(int num) {
        moved = true;
        num %= ((width + height - 2) * 2);
        while (num > 0) {
            changeDirection();
            if (dirIndex == 0) {
                k = width - 1 - x;
                if (num >= k) {
                    x = width - 1;
                    num -= k;
                } else {
                    x += num;
                    num = 0;
                }
            } else if (dirIndex == 1) {
                k = height - 1 - y;
                if (num >= k) {
                    y = height - 1;
                    num -= k;
                } else {
                    y += num;
                    num = 0;
                }
            } else if (dirIndex == 2) {
                k = x;
                if (num >= k) {
                    x = 0;
                    num -= k;
                } else {
                    x -= num;
                    num = 0;
                }
            } else {
                k = y;
                if (num >= k) {
                    y = 0;
                    num -= k;
                } else {
                    y -= num;
                    num = 0;
                }
            }
        }
    }

    private void changeDirection() {
        if ((dirIndex == 3 && x == 0 && y == 0)
                || (dirIndex == 0 && x == width - 1 && y == 0)
                || (dirIndex == 1 && x == width - 1 && y == height - 1)
                || (dirIndex == 2 && x == 0 && y == height - 1)) {
            dirIndex = (dirIndex + 1) % 4;
        }
    }

    public int[] getPos() {
        return new int[]{x, y};
    }

    public String getDir() {
        if (!moved) {
            return directions[0];
        }
        return directions[dirIndex];
    }

}
