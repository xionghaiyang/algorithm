package com.sean.leetcode.LeetCode1806;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-09 09:23
 * @Description: https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/
 * 1806. 还原排列的最少操作步数
 * 给你一个偶数 n​，已知存在一个长度为 n 的排列 perm ，其中 perm[i] == i​（下标 从 0 开始 计数）。
 * 一步操作中，你将创建一个新数组 arr ，对于每个 i ：
 * 如果 i % 2 == 0 ，那么 arr[i] = perm[i / 2]
 * 如果 i % 2 == 1 ，那么 arr[i] = perm[n / 2 + (i - 1) / 2]
 * 然后将 arr​​ 赋值​​给 perm 。
 * 要想使 perm 回到排列初始值，至少需要执行多少步操作？返回最小的 非零 操作步数。
 */
public class Solution {

    public int reinitializePermutation(int n) {
        int[] perm = new int[n];
        int[] target = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
            target[i] = i;
        }
        int step = 0;
        while (true) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                if ((i & 1) != 0) {
                    arr[i] = perm[n / 2 + (i - 1) / 2];
                } else {
                    arr[i] = perm[i / 2];
                }
            }
            perm = arr;
            step++;
            if (valid(perm)) {
                break;
            }
        }
        return step;
    }

    private boolean valid(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] != i) {
                return false;
            }
        }
        return true;
    }

}
