package com.sean.leetcode.LeetCode473;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-20 06:10
 * @Description: https://leetcode.cn/problems/matchsticks-to-square
 * 473. 火柴拼正方形
 * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。
 * 你要用 所有的火柴棍 拼成一个正方形。
 * 你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
 * 如果你能使这个正方形，则返回 true ，否则返回 false 。
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 10^8
 */
public class Solution {

    public boolean makesquare(int[] matchsticks) {
        int totalLen = 0;
        for (int matchstick : matchsticks) {
            totalLen += matchstick;
        }
        if (totalLen % 4 != 0) {
            return false;
        }
        Arrays.sort(matchsticks);
        return process(matchsticks, new int[4], totalLen / 4, matchsticks.length - 1);
    }

    private boolean process(int[] matchsticks, int[] squares, int len, int index) {
        if (index < 0) {
            return true;
        }
        for (int i = 0; i < squares.length; i++) {
            squares[i] += matchsticks[index];
            if (squares[i] <= len && process(matchsticks, squares, len, index - 1)) {
                return true;
            }
            squares[i] -= matchsticks[index];
        }
        return false;
    }

}
