package com.sean.leetcode.LeetCode464;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-11 17:32
 * @Description: https://leetcode.cn/problems/can-i-win
 * 464. 我能赢吗
 * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和 达到或超过  100 的玩家，即为胜者。
 * 如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
 * 给定两个整数 maxChoosableInteger （整数池中可选择的最大数）和 desiredTotal（累计和），若先出手的玩家能稳赢则返回 true ，否则返回 false 。
 * 假设两位玩家游戏时都表现 最佳 。
 * 1 <= maxChoosableInteger <= 20
 * 0 <= desiredTotal <= 300
 */
public class Solution {

    private int maxChoosableInteger;
    private int desiredTotal;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        int[] memo = new int[1 << maxChoosableInteger];
        this.maxChoosableInteger = maxChoosableInteger;
        this.desiredTotal = desiredTotal;
        return process(memo, 0, 0);
    }

    private boolean process(int[] memo, int cur, int mask) {
        if (memo[mask] != 0) {
            return memo[mask] == 1;
        }
        int res = -1;
        for (int i = 0; i < maxChoosableInteger; i++) {
            if (((mask >> i) & 1) == 0) {
                if (i + 1 + cur >= desiredTotal) {
                    res = 1;
                    break;
                }
                if (!process(memo, cur + i + 1, mask | (1 << i))) {
                    res = 1;
                    break;
                }
            }
        }
        memo[mask] = res;
        return res == 1;
    }

}
