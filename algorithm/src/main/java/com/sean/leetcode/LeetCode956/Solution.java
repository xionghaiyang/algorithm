package com.sean.leetcode.LeetCode956;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-10 20:07
 * @Description: https://leetcode.cn/problems/tallest-billboard
 * 956. 最高的广告牌
 * 你正在安装一个广告牌，并希望它高度最大。
 * 这块广告牌将有两个钢制支架，两边各一个。
 * 每个钢支架的高度必须相等。
 * 你有一堆可以焊接在一起的钢筋 rods。
 * 举个例子，如果钢筋的长度为 1、2 和 3，则可以将它们焊接在一起形成长度为 6 的支架。
 * 返回 广告牌的最大可能安装高度 。
 * 如果没法安装广告牌，请返回 0 。
 * 0 <= rods.length <= 20
 * 1 <= rods[i] <= 1000
 * sum(rods[i]) <= 5000
 */
public class Solution {

    public int tallestBillboard(int[] rods) {
        int sum = 0;
        for (int x : rods) {
            sum += x;
        }
        int half = sum >> 1;
        //dp[diff]表示高度差为diff时，较高支架的最大高度
        int[] dp = new int[half + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int x : rods) {
            int[] prev = dp.clone();
            //枚举所有可能的高度差
            for (int d = 0; d <= half; d++) {
                if (prev[d] == -1) {
                    continue;
                }
                //焊接到较高一侧
                if (d + x <= half) {
                    dp[d + x] = Math.max(dp[d + x], prev[d] + x);
                }
                //焊接到较低一侧
                int newDiff = Math.abs(d - x);
                if (newDiff <= half) {
                    //新的较高高度=原较高高度与焊接后较低侧高度的最大值
                    int h = Math.max(prev[d], prev[d] - d + x);
                    dp[newDiff] = Math.max(dp[newDiff], h);
                }
            }
        }
        return dp[0];
    }

}
