package com.sean.leetcode.LeetCode2917;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-06 19:48
 * @Description: https://leetcode.cn/problems/find-the-k-or-of-an-array/
 * 2917. 找出数组中的 K-or 值
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 * nums 中的 K-or 是一个满足以下条件的非负整数：
 * 只有在 nums 中，至少存在 k 个元素的第 i 位值为 1 ，那么 K-or 中的第 i 位的值才是 1 。
 * 返回 nums 的 K-or 值。
 * 注意 ：对于整数 x ，如果 (2^i AND x) == 2^i ，则 x 中的第 i 位值为 1 ，其中 AND 为按位与运算符。
 */
public class Solution {

    public int findKOr(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < 31; i++) {
            int cnt = 0;
            for (int num : nums) {
                if (((num >> i) & 1) != 0) {
                    cnt++;
                }
            }
            if (cnt >= k) {
                res |= 1 << i;
            }
        }
        return res;
    }

}
