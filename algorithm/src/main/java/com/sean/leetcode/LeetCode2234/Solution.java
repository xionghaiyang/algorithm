package com.sean.leetcode.LeetCode2234;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-03-08 07:44
 * @Description https://leetcode.cn/problems/maximum-total-beauty-of-the-gardens
 * 2234. 花园的最大总美丽值
 * Alice 是 n 个花园的园丁，她想通过种花，最大化她所有花园的总美丽值。
 * 给你一个下标从 0 开始大小为 n 的整数数组 flowers ，其中 flowers[i] 是第 i 个花园里已经种的花的数目。
 * 已经种了的花 不能 移走。
 * 同时给你 newFlowers ，表示 Alice 额外可以种花的 最大数目 。
 * 同时给你的还有整数 target ，full 和 partial 。
 * 如果一个花园有 至少 target 朵花，那么这个花园称为 完善的 ，花园的 总美丽值 为以下分数之 和 ：
 * 完善 花园数目乘以 full.
 * 剩余 不完善 花园里，花的 最少数目 乘以 partial 。
 * 如果没有不完善花园，那么这一部分的值为 0 。
 * 请你返回 Alice 种最多 newFlowers 朵花以后，能得到的 最大 总美丽值。
 * 1 <= flowers.length <= 10^5
 * 1 <= flowers[i], target <= 10^5
 * 1 <= newFlowers <= 10^10
 * 1 <= full, partial <= 10^5
 */
public class Solution {

    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        int n = flowers.length;
        //如果全部种满，还剩多少朵花
        long leftFlowers = newFlowers - (long) target * n;
        for (int i = 0; i < n; i++) {
            flowers[i] = Math.min(flowers[i], target);
            leftFlowers += flowers[i];
        }
        //没有种花，所有花园都已种满
        if (leftFlowers == newFlowers) {
            return (long) n * full;
        }
        //可以全部种满
        if (leftFlowers >= 0) {
            return Math.max((long) (target - 1) * partial + (long) (n - 1) * full, (long) n * full);
        }
        Arrays.sort(flowers);
        long res = 0;
        long preSum = 0;
        int j = 0;
        //枚举i,表示后缀[i,n-1]种满(i=0的情况上面已讨论)
        for (int i = 1; i <= n; i++) {
            //撤销,flowers[i-1]不变成target
            leftFlowers += target - flowers[i - 1];
            //花不能为负数，需要继续撤销
            if (leftFlowers < 0) {
                continue;
            }
            //满足以下条件说明[0,j]都可以种flowers[j]朵花
            while (j < i && (long) flowers[j] * j <= preSum + leftFlowers) {
                preSum += flowers[j];
                j++;
            }
            //计算总美丽值
            //在前缀[0,j-1]中均匀种花，这样最小值最大
            //由于上面特判了，这里avg一定小于target
            long avg = (leftFlowers + preSum) / j;
            long totalBeauty = avg * partial + (long) (n - i) * full;
            res = Math.max(res, totalBeauty);
        }
        return res;
    }

}
