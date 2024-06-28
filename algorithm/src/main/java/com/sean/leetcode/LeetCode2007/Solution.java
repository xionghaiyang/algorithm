package com.sean.leetcode.LeetCode2007;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-18 20:11
 * @Description: https://leetcode.cn/problems/find-original-array-from-doubled-array/
 * 2007. 从双倍数组中还原原数组
 * 一个整数数组 original 可以转变成一个 双倍 数组 changed ，转变方式为将 original 中每个元素 值乘以 2 加入数组中，然后将所有元素 随机打乱 。
 * 给你一个数组 changed ，如果 change 是 双倍 数组，那么请你返回 original数组，否则请返回空数组。
 * original 的元素可以以 任意 顺序返回。
 */
public class Solution {

    public int[] findOriginalArray(int[] changed) {
        Arrays.sort(changed);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : changed) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] res = new int[changed.length / 2];
        int i = 0;
        for (int num : changed) {
            if (map.get(num) == 0) {
                continue;
            }
            map.put(num, map.get(num) - 1);
            if (map.getOrDefault(num * 2, 0) == 0) {
                return new int[]{};
            }
            map.put(num * 2, map.get(num * 2) - 1);
            res[i++] = num;
        }
        return res;
    }

}
