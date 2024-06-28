package com.sean.leetcode.LeetCodeOffer28;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-28 20:18
 * @Description: https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 28. 对称的二叉树
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        return process(root, root);
    }

    private boolean process(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.val == root2.val && process(root1.left, root2.right) && process(root1.right, root2.left);
    }

}
