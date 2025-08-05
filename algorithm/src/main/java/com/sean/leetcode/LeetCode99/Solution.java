package com.sean.leetcode.LeetCode99;

/**
 * @Author xionghaiyang
 * @Date 2025-08-05 19:29
 * @Description https://leetcode.cn/problems/recover-binary-search-tree
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。
 * 请在不改变其结构的情况下，恢复这棵树 。
 * 树上节点的数目在范围 [2, 1000] 内
 * -2^31 <= Node.val <= 2^31 - 1
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

    private TreeNode x = null;
    private TreeNode y = null;
    private TreeNode pre = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        process(root);
        swap(x, y);
    }

    private void process(TreeNode root) {
        if (root == null) {
            return;
        }
        process(root.left);
        if (pre != null && pre.val > root.val) {
            if (x == null) {
                x = pre;
            }
            y = root;
        }
        pre = root;
        process(root.right);
    }

    private void swap(TreeNode x, TreeNode y) {
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }

}
