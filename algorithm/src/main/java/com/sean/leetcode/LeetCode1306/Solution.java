package com.sean.leetcode.LeetCode1306;

/**
 * @Autnor: xionghaiyang
 * @Date: 2026-05-17 06:29
 * @Description: https://leetcode.cn/problems/jump-game-iii
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
        return process(arr, new boolean[n], start);
    }

    private boolean process(int[] arr, boolean[] visited, int i) {
        visited[i] = true;
        if (arr[i] == 0) {
            return true;
        }
        if ((i - arr[i] >= 0 && !visited[i - arr[i]] && process(arr, visited, i - arr[i]))
                || (i + arr[i] < arr.length && !visited[i + arr[i]] && process(arr, visited, i + arr[i]))) {
            return true;
        }
        return false;
    }

}
