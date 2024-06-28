package com.sean.leetcode;

import java.util.Random;

/**
 * 将有序数组转换为二叉搜索树
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class LeetCode108 {

    private static class TreeNode {
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

    //中序遍历，总是选择中间位置左边的数字作为根节点
    public static TreeNode sortedArrayToBST1(int[] nums) {
        return helper1(nums, 0, nums.length - 1);
    }

    private static TreeNode helper1(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        //总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper1(nums, left, mid - 1);
        root.right = helper1(nums, mid + 1, right);
        return root;
    }

    //中序遍历，总是选择中间位置右边的数字作为根节点
    public TreeNode sortedArrayToBST2(int[] nums) {
        return helper2(nums, 0, nums.length - 1);
    }

    private static TreeNode helper2(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        //总是选择中间位置右边的数字作为根节点
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper2(nums, left, mid - 1);
        root.right = helper2(nums, mid + 1, right);
        return root;
    }

    //中序遍历，选择任意一个中间位置数字作为根节点
    private static Random random = new Random();

    public static TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private static TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        //选择任意一个中间位置数字作为根节点
        int mid = (left + right + random.nextInt(2)) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }
}
