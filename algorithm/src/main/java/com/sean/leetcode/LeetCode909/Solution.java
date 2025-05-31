package com.sean.leetcode.LeetCode909;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-05-31 06:54
 * @Description https://leetcode.cn/problems/snakes-and-ladders
 * 909. 蛇梯棋
 * 给你一个大小为 n x n 的整数矩阵 board ，方格按从 1 到 n^2 编号，编号遵循 转行交替方式 ，
 * 从左下角开始 （即，从 board[n - 1][0] 开始）的每一行改变方向。
 * 你一开始位于棋盘上的方格  1。
 * 每一回合，玩家需要从当前方格 curr 开始出发，按下述要求前进：
 * 选定目标方格 next ，目标方格的编号在范围 [curr + 1, min(curr + 6, n^2)] 。
 * 该选择模拟了掷 六面体骰子 的情景，无论棋盘大小如何，玩家最多只能有 6 个目的地。
 * 传送玩家：如果目标方格 next 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。
 * 否则，玩家传送到目标方格 next 。
 * 当玩家到达编号 n^2 的方格时，游戏结束。
 * 如果 board[r][c] != -1 ，位于 r 行 c 列的棋盘格中可能存在 “蛇” 或 “梯子”。
 * 那个蛇或梯子的目的地将会是 board[r][c]。
 * 编号为 1 和 n^2 的方格不是任何蛇或梯子的起点。
 * 注意，玩家在每次掷骰的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，玩家也 不能 继续移动。
 * 举个例子，假设棋盘是 [[-1,4],[-1,3]] ，第一次移动，玩家的目标方格是 2 。
 * 那么这个玩家将会顺着梯子到达方格 3 ，但 不能 顺着方格 3 上的梯子前往方格 4 。
 * （简单来说，类似飞行棋，玩家掷出骰子点数后移动对应格数，遇到单向的路径（即梯子或蛇）可以直接跳到路径的终点，
 * 但如果多个路径首尾相连，也不能连续跳多个路径）
 * 返回达到编号为 n^2 的方格所需的最少掷骰次数，如果不可能，则返回 -1。
 * n == board.length == board[i].length
 * 2 <= n <= 20
 * board[i][j] 的值是 -1 或在范围 [1, n^2] 内
 * 编号为 1 和 n^2 的方格上没有蛇或梯子
 */
public class Solution {

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        visited[1] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 1; i <= 6; i++) {
                int next = cur[0] + i;
                if (next > n * n) {
                    break;
                }
                int[] rc = id2rc(next, n);
                if (board[rc[0]][rc[1]] > 0) {
                    next = board[rc[0]][rc[1]];
                }
                if (next == n * n) {
                    return cur[1] + 1;
                }
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, cur[1] + 1});
                }
            }
        }
        return -1;
    }

    private int[] id2rc(int id, int n) {
        int r = (id - 1) / n, c = (id - 1) % n;
        if ((r & 1) == 1) {
            c = n - 1 - c;
        }
        return new int[]{n - 1 - r, c};
    }

}
