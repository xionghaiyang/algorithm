package com.sean.leetcode.LeetCode1345;

import java.util.*;

/**
 * @Autnor: xionghaiyang
 * @Date: 2026-05-18 05:51
 * @Description: https://leetcode.cn/problems/jump-game-iv
 * 1345. 跳跃游戏 IV
 * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
 * 每一步，你可以从下标 i 跳到下标 i + 1 、i - 1 或者 j ：
 * i + 1 需满足：i + 1 < arr.length
 * i - 1 需满足：i - 1 >= 0
 * j 需满足：arr[i] == arr[j] 且 i != j
 * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
 * 注意：任何时候你都不能跳到数组外面。
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 */
public class Solution {

    public int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        int res = 0;
        out:
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int i = queue.poll();
                if (i == n - 1) {
                    break out;
                }
                if (i - 1 >= 0 && !visited[i - 1]) {
                    queue.offer(i - 1);
                    visited[i - 1] = true;
                }
                if (i + 1 < n && !visited[i + 1]) {
                    queue.offer(i + 1);
                    visited[i + 1] = true;
                }
                if (map.containsKey(arr[i])) {
                    for (int j : map.get(arr[i])) {
                        if (!visited[j]) {
                            queue.offer(j);
                            visited[j] = true;
                        }
                    }
                    map.remove(arr[i]);
                }
            }
            res++;
        }
        return res;
    }

}
