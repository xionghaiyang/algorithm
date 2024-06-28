package com.sean.leetcode.LeetCode1799;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-26 15:29
 * @Description: https://leetcode.cn/problems/maximize-score-after-n-operations/
 * 1799. N 次操作后的最大分数和
 * 给你 nums ，它是一个大小为 2 * n 的正整数数组。
 * 你必须对这个数组执行 n 次操作。
 * 在第 i 次操作时（操作编号从 1 开始），你需要：
 * 选择两个元素 x 和 y 。
 * 获得分数 i * gcd(x, y) 。
 * 将 x 和 y 从 nums 中删除。
 * 请你返回 n 次操作后你能获得的分数和最大为多少。
 * 函数 gcd(x, y) 是 x 和 y 的最大公约数。
 */
public class Solution {

    public int maxScore(int[] nums) {
        int n = nums.length;
        int[] dp = new int[1 << n];
        int[][] gcd = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                gcd[i][j] = gcd(nums[i], nums[j]);
            }
        }
        for (int s = 1; s < (1 << n); s++) {
            int t = Integer.bitCount(s);
            if ((t & 1) != 0) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                if ((s & (1 << i)) != 0) {
                    for (int j = i + 1; j < n; j++) {
                        if ((s & (1 << j)) != 0) {
                            dp[s] = Math.max(dp[s], dp[s ^ (1 << i) ^ (1 << j)] + t / 2 * gcd[i][j]);
                        }
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }

    private int gcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1;
            num1 = num2;
            num2 = temp % num2;
        }
        return num1;
    }

}
