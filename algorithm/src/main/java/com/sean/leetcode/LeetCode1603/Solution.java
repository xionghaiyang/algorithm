package com.sean.leetcode.LeetCode1603;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-09-15 18:15
 * @Description https://leetcode.cn/problems/jump-game-iii
 * 1306. 跳跃游戏 III
 * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。
 * 当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
 * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
 * 注意，不管是什么情况下，你都无法跳到数组之外。
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 */
public class Solution {

    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (arr[u] == 0) {
                return true;
            } else {
                if (u + arr[u] < n && !visited[u + arr[u]]) {
                    queue.offer(u + arr[u]);
                    visited[u + arr[u]] = true;
                }
                if (u - arr[u] >= 0 && !visited[u - arr[u]]) {
                    queue.offer(u - arr[u]);
                    visited[u - arr[u]] = true;
                }
            }
        }
        return false;
    }

    public boolean canReach1(int[] arr, int start) {
        return dfs(arr, start, new boolean[arr.length]);
    }

    private boolean dfs(int[] arr, int i, boolean[] visited) {
        if (i < 0 || i >= arr.length || visited[i]) {
            return false;
        }
        visited[i] = true;
        if (arr[i] == 0) {
            return true;
        }
        return dfs(arr, i + arr[i], visited) || dfs(arr, i - arr[i], visited);
    }

}
