package com.sean.leetcode.LeetCode108;

/**
 * @Author xionghaiyang
 * @Date 2025-06-08 06:55
 * @Description https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree
 * 108. 将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
 * 1 <= nums.length <= 104
 * -10^4 <= nums[i] <= 10^4
 * nums 按 严格递增 顺序排列
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    private TreeNode process(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        return new TreeNode(nums[mid], process(nums, left, mid - 1), process(nums, mid + 1, right));
    }

}
