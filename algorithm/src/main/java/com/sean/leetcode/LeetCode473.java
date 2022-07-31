package com.sean.leetcode;

import java.util.Arrays;

public class LeetCode473 {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean makesquare(int[] matchsticks) {
        int totalLen = 0;
        for (int matchstick : matchsticks) {
            totalLen += matchstick;
        }
        if (totalLen % 4 != 0) {
            return false;
        }
        Arrays.sort(matchsticks);
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            swap(matchsticks, i, j);
        }
        int[] edges = new int[4];
        return dfs(0, matchsticks, edges, totalLen / 4);
    }

    public static boolean dfs(int index, int[] matchsticks, int[] edges, int len) {
        if (index == matchsticks.length) {
            return true;
        }
        for (int i = 0; i < edges.length; i++) {
            edges[i] += matchsticks[index];
            if (edges[i] <= len && dfs(index + 1, matchsticks, edges, len)) {
                return true;
            }
            edges[i] -= matchsticks[index];
        }
        return false;
    }

}
