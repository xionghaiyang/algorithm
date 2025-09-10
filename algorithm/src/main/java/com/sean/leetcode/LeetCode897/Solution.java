package com.sean.leetcode.LeetCode897;

/**
 * @Author xionghaiyang
 * @Date 2025-09-10 09:22
 * @Description https://leetcode.cn/problems/increasing-order-search-tree
 * 897. 递增顺序搜索树
 * 给你一棵二叉搜索树的 root ，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * 树中节点数的取值范围是 [1, 100]
 * 0 <= Node.val <= 1000
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

    private TreeNode dummy;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyHead = new TreeNode();
        dummy = dummyHead;
        dfs(root);
        return dummyHead.right;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dummy.right = node;
        node.left = null;
        dummy = node;
        dfs(node.right);
    }

}
