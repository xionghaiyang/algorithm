package com.sean.leetcode.LeetCode1524;

/**
 * @Author xionghaiyang
 * @Date 2024-05-08 20:21
 * @Description https://leetcode.cn/problems/number-of-sub-arrays-with-odd-sum/
 * 1524. 和为奇数的子数组数目
 * 给你一个整数数组 arr 。
 * 请你返回和为 奇数 的子数组数目。
 * 由于答案可能会很大，请你将结果对 10^9 + 7 取余后返回。
 */
public class Solution {

    public int numOfSubarrays(int[] arr) {
        int mod = 1_000_000_007;
        int odd = 0, even = 1, preSum = 0;
        int res = 0;
        for (int num : arr) {
            preSum += num;
            res = (res + (preSum % 2 == 0 ? odd : even)) % mod;
            if (preSum % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return res;
    }

}
