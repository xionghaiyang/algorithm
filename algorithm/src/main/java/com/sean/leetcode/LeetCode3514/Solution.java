package com.sean.leetcode.LeetCode3514;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-24 06:24
 * @Description: https://leetcode.cn/problems/number-of-unique-xor-triplets-ii
 * 3514. 不同 XOR 三元组的数目 II
 * 给你一个整数数组 nums 。
 * XOR 三元组 定义为三个元素的异或值 nums[i] XOR nums[j] XOR nums[k]，其中 i <= j <= k。
 * 返回所有可能三元组 (i, j, k) 中 不同 的 XOR 值的数量。
 * 1 <= nums.length <= 1500
 * 1 <= nums[i] <= 1500
 */
public class Solution {

    public int uniqueXorTriplets(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int u = 1 << (32 - Integer.numberOfLeadingZeros(max));
        boolean[] has = new boolean[u];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                has[nums[i] ^ nums[j]] = true;
            }
        }
        boolean[] has3 = new boolean[u];
        for (int xy = 0; xy < u; xy++) {
            if (!has[xy]) {
                continue;
            }
            for (int z : nums) {
                has3[xy ^ z] = true;
            }
        }
        int res = 0;
        for (boolean b : has3) {
            if (b) {
                res++;
            }
        }
        return res;
    }

}
