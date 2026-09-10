package com.sean.leetcode.LeetCode3483;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-11 06:18
 * @Description: https://leetcode.cn/problems/unique-3-digit-even-numbers
 * 3483. 不同三位偶数的数目
 * 给你一个数字数组 digits，你需要从中选择三个数字组成一个三位偶数，你的任务是求出 不同 三位偶数的数量。
 * 注意：每个数字在三位偶数中都只能使用 一次 ，并且 不能 有前导零。
 * 3 <= digits.length <= 10
 * 0 <= digits[i] <= 9
 */
public class Solution {

    public int totalNumbers(int[] digits) {
        int n = digits.length;
        boolean[] vis = new boolean[1000];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j || (digits[k] & 1) != 0) {
                        continue;
                    }
                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                    if (!vis[num]) {
                        vis[num] = true;
                        res++;
                    }
                }
            }
        }
        return res;
    }

}
