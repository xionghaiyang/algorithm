package com.sean.leetcode.LeetCode1798;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-06 09:37
 * @Description: https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make/
 * 1798. 你能构造出连续值的最大数目
 * 给你一个长度为 n 的整数数组 coins ，它代表你拥有的 n 个硬币。
 * 第 i 个硬币的值为 coins[i] 。
 * 如果你从这些硬币中选出一部分硬币，它们的和为 x ，那么称，你可以 构造 出 x 。
 * 请返回从 0 开始（包括 0 ），你最多能 构造 出多少个连续整数。
 * 你可能有多个相同值的硬币。
 */
public class Solution {

    public int getMaximumConsecutive(int[] coins) {
        int res = 1;
        Arrays.sort(coins);
        for (int coin : coins) {
            if (coin > res) {
                break;
            }
            res += coin;
        }
        return res;
    }

}
