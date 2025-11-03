package com.sean.leetcode.LeetCode1578;

/**
 * @Author xionghaiyang
 * @Date 2025-11-03 12:10
 * @Description https://leetcode.cn/problems/minimum-time-to-make-rope-colorful
 * 1578. 使绳子变成彩色的最短时间
 * Alice 把 n 个气球排列在一根绳子上。
 * 给你一个下标从 0 开始的字符串 colors ，其中 colors[i] 是第 i 个气球的颜色。
 * Alice 想要把绳子装扮成 五颜六色的 ，且她不希望两个连续的气球涂着相同的颜色，所以她喊来 Bob 帮忙。
 * Bob 可以从绳子上移除一些气球使绳子变成 彩色 。
 * 给你一个 下标从 0 开始 的整数数组 neededTime ，其中 neededTime[i] 是 Bob 从绳子上移除第 i 个气球需要的时间（以秒为单位）。
 * 返回 Bob 使绳子变成 彩色 需要的 最少时间 。
 * n == colors.length == neededTime.length
 * 1 <= n <= 10^5
 * 1 <= neededTime[i] <= 10^4
 * colors 仅由小写英文字母组成
 */
public class Solution {

    public int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        int res = 0;
        for (int i = 1, last = 0; i < n; i++) {
            char c = colors.charAt(i);
            if (c == colors.charAt(last)) {
                if (neededTime[i] > neededTime[last]) {
                    res += neededTime[last];
                    last = i;
                } else {
                    res += neededTime[i];
                }
            } else {
                last = i;
            }
        }
        return res;
    }

}
