package com.sean.leetcode.LeetCode1640;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-22 08:25
 * @Description: https://leetcode.cn/problems/check-array-formation-through-concatenation/
 * 1640. 能否连接形成数组
 * 给你一个整数数组 arr ，数组中的每个整数 互不相同 。
 * 另有一个由整数数组构成的数组 pieces，其中的整数也 互不相同 。
 * 请你以 任意顺序 连接 pieces 中的数组以形成 arr 。
 * 但是，不允许 对每个数组 pieces[i] 中的整数重新排序。
 * 如果可以连接 pieces 中的数组形成 arr ，返回 true ；否则，返回 false 。
 */
public class Solution {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        return dfs(arr, 0, pieces, new boolean[pieces.length]);
    }

    private boolean dfs(int[] arr, int index, int[][] pieces, boolean[] visited) {
        int m = arr.length;
        if (index == m) {
            return true;
        }
        int n = pieces.length;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                boolean flag = true;
                if (index + pieces[i].length - 1 >= m) {
                    flag = false;
                }
                if (flag) {
                    for (int j = 0; j < pieces[i].length; j++) {
                        if (arr[index + j] != pieces[i][j]) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    visited[i] = true;
                    if (dfs(arr, index + pieces[i].length, pieces, visited)) {
                        return true;
                    }
                    visited[i] = false;
                }
            }
        }
        return false;
    }

}
