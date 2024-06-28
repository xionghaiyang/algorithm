package com.sean.leetcode.LeetCodeLCP56;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-11 11:12
 * @Description: https://leetcode.cn/problems/6UEx57/
 * LCP 56. 信物传送
 * 欢迎各位勇者来到力扣城，本次试炼主题为「信物传送」。
 * 本次试炼场地设有若干传送带，matrix[i][j] 表示第 i 行 j 列的传送带运作方向，
 * "^","v","<",">" 这四种符号分别表示 上、下、左、右 四个方向。
 * 信物会随传送带的方向移动。
 * 勇者每一次施法操作，可临时变更一处传送带的方向，在物品经过后传送带恢复原方向。
 * 通关信物初始位于坐标 start处，勇者需要将其移动到坐标 end 处，请返回勇者施法操作的最少次数。
 * 注意：
 * start 和 end 的格式均为 [i,j]
 */
public class Solution {

    public int conveyorBelt(String[] matrix, int[] start, int[] end) {
        int n = matrix.length;
        int m = matrix[0].length();
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{start[0], start[1], 0});
        dp[start[0]][start[1]] = 0;
        int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        char[] c = {'<', '^', '>', 'v'};
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            if (arr[0] == end[0] && arr[1] == end[1]) {
                return arr[2];
            }
            for (int i = 0; i < 4; i++) {
                int x = arr[0] + dirs[i][0];
                int y = arr[1] + dirs[i][1];
                if (x >= 0 && x < n && y >= 0 && y < m) {
                    if (arr[2] < dp[x][y]) {
                        int cost = arr[2] + (matrix[arr[0]].charAt(arr[1]) == c[i] ? 0 : 1);
                        if (cost < dp[x][y]) {
                            dp[x][y] = cost;
                            queue.offer(new int[]{x, y, cost});
                        }
                    }
                }
            }
        }
        return 0;
    }

}
