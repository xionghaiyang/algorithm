package com.sean.leetcode.LeetCode1561;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-01-22 21:50
 * @Description https://leetcode.cn/problems/maximum-number-of-coins-you-can-get/
 * 1561. 你可以获得的最大硬币数目
 * 有 3n 堆数目不一的硬币，你和你的朋友们打算按以下方式分硬币：
 * 每一轮中，你将会选出 任意 3 堆硬币（不一定连续）。
 * Alice 将会取走硬币数量最多的那一堆。
 * 你将会取走硬币数量第二多的那一堆。
 * Bob 将会取走最后一堆。
 * 重复这个过程，直到没有更多硬币。
 * 给你一个整数数组 piles ，其中 piles[i] 是第 i 堆中硬币的数目。
 * 返回你可以获得的最大硬币数目。
 * 3<= piles.length <= 10^5
 * piles.length % 3 == 0
 * 1 <= piles[i] <= 10^4
 */
public class Solution {

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int n = piles.length;
        int rounds = n / 3;
        int res = 0;
        int index = n - 2;
        for (int i = 0; i < rounds; i++) {
            res += piles[index];
            index -= 2;
        }
        return res;
    }

}
