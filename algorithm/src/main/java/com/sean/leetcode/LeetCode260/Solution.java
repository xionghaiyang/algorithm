package com.sean.leetcode.LeetCode260;

/**
 * @Author xionghaiyang
 * @Date 2025-08-20 12:25
 * @Description https://leetcode.cn/problems/single-number-iii
 * 260. 只出现一次的数字 III
 * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
 * 找出只出现一次的那两个元素。
 * 你可以按 任意顺序 返回答案。
 * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
 * 2 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
 */
public class Solution {

    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int x = xor & (-xor);
        int num0 = 0;
        for (int num : nums) {
            if ((num & x) != 0) {
                num0 ^= num;
            }
        }
        int num1 = xor ^ num0;
        return new int[]{num0, num1};
    }

}
