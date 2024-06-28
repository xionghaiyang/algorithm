package com.sean.leetcode.LeetCode2300;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-11-10 11:19
 * @Description: https://leetcode.cn/problems/successful-pairs-of-spells-and-potions/description/
 * 2300. 咒语和药水的成功对数
 * 给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，
 * 其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。
 * 同时给你一个整数 success 。
 * 一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。
 * 请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
 */
public class Solution {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            long t = (success + spells[i] - 1) / spells[i] - 1;
            res[i] = m - binarySearch(potions, 0, m - 1, t);
        }
        return res;
    }

    private int binarySearch(int[] arr, int low, int high, long target) {
        int res = high + 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    public int[] successfulPairs1(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] res = new int[n];
        int[][] index = new int[n][2];
        for (int i = 0; i < n; i++) {
            index[i][0] = spells[i];
            index[i][1] = i;
        }
        Arrays.sort(potions);
        for (int i = 0, j = m - 1; i < j; i++, j--) {
            int temp = potions[i];
            potions[i] = potions[j];
            potions[j] = temp;
        }
        Arrays.sort(index, (a, b) -> a[0] - b[0]);
        for (int i = 0, j = 0; i < n; i++) {
            int p = index[i][1];
            int v = index[i][0];
            while (j < m && (long) potions[j] * v >= success) {
                j++;
            }
            res[p] = j;
        }
        return res;
    }

}
