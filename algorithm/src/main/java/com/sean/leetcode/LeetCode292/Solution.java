package com.sean.leetcode.LeetCode292;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-04 19:44
 * @Description: https://leetcode.cn/problems/nim-game/
 * 292. Nim 游戏
 * 你和你的朋友，两个人一起玩 Nim 游戏：
 * 桌子上有一堆石头。
 * 你们轮流进行自己的回合， 你作为先手 。
 * 每一回合，轮到的人拿掉 1 - 3 块石头。
 * 拿掉最后一块石头的人就是获胜者。
 * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
 */
public class Solution {

    public boolean canWinNim(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        process(dp, n);
        return dp[n] == 1;
    }

    private int process(int[] dp, int n) {
        if (dp[n] != -1) {
            return dp[n];
        }
        if (n <= 3) {
            return dp[n] = 1;
        }
        int res1 = process(dp, n - 1);
        int res2 = process(dp, n - 2);
        int res3 = process(dp, n - 3);
        if (res1 == 1 && res2 == 1 && res3 == 1) {
            dp[n] = 0;
        } else {
            dp[n] = 1;
        }
        return dp[n];
    }

    public boolean canWinNim1(int n) {
        return n % 4 != 0;
    }

}
