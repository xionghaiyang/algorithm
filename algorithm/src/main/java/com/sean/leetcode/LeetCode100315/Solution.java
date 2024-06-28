package com.sean.leetcode.LeetCode100315;

/**
 * @Author xionghaiyang
 * @Date 2024-06-02 21:21
 * @Description https://leetcode.cn/problems/find-subarray-with-bitwise-and-closest-to-k/
 * 100315. 找到按位与最接近 K 的子数组
 * 给你一个数组 nums 和一个整数 k 。
 * 你需要找到 nums 的一个子数组 ，满足子数组中所有元素按位与运算 AND 的值与 k 的 绝对差 尽可能 小 。
 * 换言之，你需要选择一个子数组 nums[l..r] 满足 |k - (nums[l] AND nums[l + 1] ... AND nums[r])| 最小。
 * 请你返回 最小 的绝对差值。
 * 子数组是数组中连续的 非空 元素序列。
 */
public class Solution {

    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            res = Math.min(res, Math.abs(x - k));
            for (int j = i - 1; j >= 0 && (nums[j] & x) != nums[j]; j--) {
                nums[j] &= x;
                res = Math.min(res, Math.abs(nums[j] - k));
            }
        }
        return res;
    }

}
