package com.sean.leetcode.LeetCode27;

/**
 * @Author xionghaiyang
 * @Date 2025-06-12 18:20
 * @Description https://leetcode.cn/problems/remove-element
 * 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。
 * 元素的顺序可能发生改变。
 * 然后返回 nums 中与 val 不同的元素的数量。
 * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
 * 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。
 * nums 的其余元素和 nums 的大小并不重要。
 * 返回 k。
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 */
public class Solution {

    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != val) {
                swap(nums, i, k++);
            }
        }
        return k;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
