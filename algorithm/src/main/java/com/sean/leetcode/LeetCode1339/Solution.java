package com.sean.leetcode.LeetCode1339;

/**
 * @Author xionghaiyang
 * @Date 2025-09-16 19:30
 * @Description https://leetcode.cn/problems/maximum-product-of-splitted-binary-tree
 * 1339. 分裂二叉树的最大乘积
 * 给你一棵二叉树，它的根为 root 。
 * 请你删除 1 条边，使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。
 * 由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。
 * 每棵树最多有 50000 个节点，且至少有 2 个节点。
 * 每个节点的值在 [1, 10000] 之间。
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

    private int sum = 0;
    private int x = 0;

    public int maxProduct(TreeNode root) {
        dfs(root);
        dfs1(root);
        return (int) ((long) x * (sum - x) % 1_000_000_007);
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        sum += node.val;
        dfs(node.left);
        dfs(node.right);
    }

    private int dfs1(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int cur = dfs1(node.left) + dfs1(node.right) + node.val;
        if (Math.abs(cur * 2 - sum) < Math.abs(x * 2 - sum)) {
            x = cur;
        }
        return cur;
    }

}
