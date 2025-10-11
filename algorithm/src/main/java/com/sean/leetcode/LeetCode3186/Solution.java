package com.sean.leetcode.LeetCode3186;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-10-11 12:25
 * @Description https://leetcode.cn/problems/maximum-total-damage-with-spell-casting
 * 3186. 施咒的最大总伤害
 * 一个魔法师有许多不同的咒语。
 * 给你一个数组 power ，其中每个元素表示一个咒语的伤害值，可能会有多个咒语有相同的伤害值。
 * 已知魔法师使用伤害值为 power[i] 的咒语时，他们就 不能 使用伤害为 power[i] - 2 ，power[i] - 1 ，power[i] + 1 或者 power[i] + 2 的咒语。
 * 每个咒语最多只能被使用 一次 。
 * 请你返回这个魔法师可以达到的伤害值之和的 最大值 。
 * 1 <= power.length <= 10^5
 * 1 <= power[i] <= 10^9
 */
public class Solution {

    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : power) {
            map.merge(num, 1, Integer::sum);
        }
        int n = map.size();
        int[] arr = new int[n];
        int index = 0;
        for (int num : map.keySet()) {
            arr[index++] = num;
        }
        Arrays.sort(arr);
        long[] memo = new long[n];
        Arrays.fill(memo, -1);
        return dfs(arr, map, memo, n - 1);
    }

    private long dfs(int[] arr, Map<Integer, Integer> map, long[] memo, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int num = arr[i], j = i;
        while (j > 0 && arr[j - 1] >= num - 2) {
            j--;
        }
        return memo[i] = Math.max(dfs(arr, map, memo, i - 1), dfs(arr, map, memo, j - 1) + (long) num * map.get(num));
    }

}
