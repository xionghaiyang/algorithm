package com.sean.leetcode.LeetCode3180;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-09 09:35
 * @Description: https://leetcode.cn/problems/maximum-total-reward-using-operations-i
 * 3180. 执行操作可获得的最大总奖励 I
 * 给你一个整数数组 rewardValues，长度为 n，代表奖励的值。
 * 最初，你的总奖励 x 为 0，所有下标都是 未标记 的。
 * 你可以执行以下操作 任意次 ：
 * 从区间 [0, n - 1] 中选择一个 未标记 的下标 i。
 * 如果 rewardValues[i] 大于 你当前的总奖励 x，则将 rewardValues[i] 加到 x 上（即 x = x + rewardValues[i]），并 标记 下标 i。
 * 以整数形式返回执行最优操作能够获得的 最大 总奖励。
 * 1 <= rewardValues.length <= 2000
 * 1 <= rewardValues[i] <= 2000
 */
public class Solution {

    public int maxTotalReward(int[] rewardValues) {
        int max = 0;
        for (int value : rewardValues) {
            max = Math.max(max, value);
        }
        Set<Integer> set = new HashSet<>();
        for (int value : rewardValues) {
            if (value == max - 1) {
                return max * 2 - 1;
            }
            if (set.contains(value)) {
                continue;
            }
            if (set.contains(max - 1 - value)) {
                return max * 2 - 1;
            }
            set.add(value);
        }
        Arrays.sort(rewardValues);
        int pre = 0;
        BigInteger f = BigInteger.ONE;
        for (int value : rewardValues) {
            if (value == pre) {
                continue;
            }
            BigInteger mask = BigInteger.ONE.shiftLeft(value).subtract(BigInteger.ONE);
            f = f.or(f.and(mask).shiftLeft(value));
            pre = value;
        }
        return f.bitLength() - 1;
    }

}
