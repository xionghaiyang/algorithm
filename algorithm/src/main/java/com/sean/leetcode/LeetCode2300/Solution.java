package com.sean.leetcode.LeetCode2300;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-11-10 11:19
 * @Description: https://leetcode.cn/problems/successful-pairs-of-spells-and-potions
 * 2300. 咒语和药水的成功对数
 * 给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，
 * 其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。
 * 同时给你一个整数 success 。
 * 一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。
 * 请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
 * n == spells.length
 * m == potions.length
 * 1 <= n, m <= 10^5
 * 1 <= spells[i], potions[i] <= 10^5
 * 1 <= success <= 10^10
 */
public class Solution {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int max = Arrays.stream(potions).max().getAsInt();
        int[] cnt = new int[max + 1];
        for (int potion : potions) {
            cnt[potion]++;
        }
        for (int i = max - 1; i >= 0; i--) {
            cnt[i] += cnt[i + 1];
        }
        int n = spells.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            long low = (success - 1) / spells[i] + 1;
            res[i] = low <= max ? cnt[(int) low] : 0;
        }
        return res;
    }

}
