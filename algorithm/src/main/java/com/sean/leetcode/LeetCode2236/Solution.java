package com.sean.leetcode.LeetCode2236;

/**
 * @Author xionghaiyang
 * @Date 2025-02-05 19:34
 * @Description https://leetcode.cn/problems/root-equals-sum-of-children
 * 2236. 判断根结点是否等于子结点之和
 * 给你一个 二叉树 的根结点 root，该二叉树由恰好 3 个结点组成：根结点、左子结点和右子结点。
 * 如果根结点值等于两个子结点值之和，返回 true ，否则返回 false 。
 * 树只包含根结点、左子结点和右子结点
 * -100 <= Node.val <= 100
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

    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }

}
