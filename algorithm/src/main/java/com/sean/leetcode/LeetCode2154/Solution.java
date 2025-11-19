package com.sean.leetcode.LeetCode2154;

/**
 * @Author xionghaiyang
 * @Date 2025-11-19 10:50
 * @Description https://leetcode.cn/problems/keep-multiplying-found-values-by-two
 * 2154. 将找到的值乘以 2
 * 给你一个整数数组 nums ，另给你一个整数 original ，这是需要在 nums 中搜索的第一个数字。
 * 接下来，你需要按下述步骤操作：
 * 如果在 nums 中找到 original ，将 original 乘以 2 ，得到新 original（即，令 original = 2 * original）。
 * 否则，停止这一过程。
 * 只要能在数组中找到新 original ，就对新 original 继续 重复 这一过程。
 * 返回 original 的 最终 值。
 * 1 <= nums.length <= 1000
 * 1 <= nums[i], original <= 1000
 */
public class Solution {

    public int findFinalValue(int[] nums, int original) {
        int mask = 0;
        for (int num : nums) {
            if (num % original == 0) {
                num /= original;
                if ((num & (num - 1)) == 0) {
                    mask |= num;
                }
            }
        }
        mask = ~mask;
        return original * (mask & (-mask));
    }

}
