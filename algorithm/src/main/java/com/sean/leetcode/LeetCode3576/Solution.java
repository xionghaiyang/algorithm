package com.sean.leetcode.LeetCode3576;

/**
 * @Author xionghaiyang
 * @Date 2025-06-09 10:38
 * @Description https://leetcode.cn/problems/transform-array-to-all-equal-elements/
 * 3576. 数组元素相等转换
 * 给你一个大小为 n 的整数数组 nums，其中只包含 1 和 -1，以及一个整数 k。
 * 你可以最多进行 k 次以下操作：
 * 选择一个下标 i（0 <= i < n - 1），然后将 nums[i] 和 nums[i + 1] 同时 乘以 -1。
 * 注意：你可以在 不同 的操作中多次选择相同的下标 i。
 * 如果在最多 k 次操作后可以使数组的所有元素相等，则返回 true；否则，返回 false。
 * 1 <= n == nums.length <= 10^5
 * nums[i] 的值为 -1 或 1。
 * 1 <= k <= n
 */
public class Solution {

    public boolean canMakeEqual(int[] nums, int k) {
        return process(nums, k, -1) || process(nums, k, 1);
    }

    //最多操作k次，所有值变为x
    private boolean process(int[] nums, int k, int x) {
        int n = nums.length;
        int num = 1;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] * num != x) {//需要改变
                if (k == 0) {//不能改变了
                    return false;
                }
                k--;
                num = -1;
            } else {
                num = 1;
            }
        }
        return nums[n - 1] * num == x;
    }

}
