package com.sean.leetcode.LeetCode982;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-06 08:28
 * @Description: https://leetcode.cn/problems/triples-with-bitwise-and-equal-to-zero/
 * 982. 按位与为零的三元组
 * 给你一个整数数组 nums ，返回其中 按位与三元组 的数目。
 * 按位与三元组 是由下标 (i, j, k) 组成的三元组，并满足下述全部条件：
 * 0 <= i < nums.length
 * 0 <= j < nums.length
 * 0 <= k < nums.length
 * nums[i] & nums[j] & nums[k] == 0 ，其中 & 表示按位与运算符。
 */
public class Solution {

    public int countTriplets(int[] nums) {
        int[] cnt = new int[1 << 16];
        for (int x : nums) {
            for (int y : nums) {
                cnt[x & y]++;
            }
        }
        int res = 0;
        for (int x : nums) {
            for (int mask = 0; mask < (1 << 16); mask++) {
                if ((x & mask) == 0) {
                    res += cnt[mask];
                }
            }
        }
        return res;
    }

}
