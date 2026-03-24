package com.sean.leetcode.LeetCodeInterview1622;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-03-24 14:35
 * @Description https://leetcode.cn/problems/langtons-ant-lcci
 * 面试题 16.22. 兰顿蚂蚁
 * 一只蚂蚁坐在由白色和黑色方格构成的无限网格上。
 * 开始时，网格全白，蚂蚁面向右侧。
 * 每行走一步，蚂蚁执行以下操作。
 * (1) 如果在白色方格上，则翻转方格的颜色，向右(顺时针)转 90 度，并向前移动一个单位。
 * (2) 如果在黑色方格上，则翻转方格的颜色，向左(逆时针方向)转 90 度，并向前移动一个单位。
 * 编写程序来模拟蚂蚁执行的前 K 个动作，并返回最终的网格。
 * 网格由数组表示，每个元素是一个字符串，代表网格中的一行，黑色方格由 'X' 表示，白色方格由 '_' 表示，蚂蚁所在的位置由 'L', 'U', 'R', 'D' 表示，分别表示蚂蚁 左、上、右、下 的朝向。
 * 只需要返回能够包含蚂蚁走过的所有方格的最小矩形。
 * K <= 100000
 */
public class Solution {

    public class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Position)) {
                return false;
            }
            Position position = (Position) obj;
            return x == position.x && y == position.y;
        }
    }

    public List<String> printKMoves(int K) {
        char[] direction = {'L', 'U', 'R', 'D'};
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        Position antPos = new Position(0, 0);
        int antDir = 2;
        Set<Position> black = new HashSet<>();
        while (K > 0) {
            Position t = new Position(antPos.x, antPos.y);
            if (black.add(t)) {
                antDir = (antDir + 1) % 4;
            } else {
                antDir = (antDir + 3) % 4;
                black.remove(t);
            }
            antPos.x += dirs[antDir][0];
            antPos.y += dirs[antDir][1];
            K--;
        }
        int left = antPos.x, right = antPos.x, top = antPos.y, bottom = antPos.y;
        for (Position position : black) {
            left = position.x < left ? position.x : left;
            right = position.x > right ? position.x : right;
            top = position.y > top ? position.y : top;
            bottom = position.y < bottom ? position.y : bottom;
        }
        char[][] grid = new char[top - bottom + 1][right - left + 1];
        for (char[] row : grid) {
            Arrays.fill(row, '_');
        }
        for (Position position : black) {
            grid[position.y - bottom][position.x - left] = 'X';
        }
        grid[antPos.y - bottom][antPos.x - left] = direction[antDir];
        List<String> res = new ArrayList<>();
        for (char[] row : grid) {
            res.add(String.valueOf(row));
        }
        return res;
    }

}
