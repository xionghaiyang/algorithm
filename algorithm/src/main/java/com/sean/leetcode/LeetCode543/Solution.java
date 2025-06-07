package com.sean.leetcode.LeetCode543;

/**
 * @Author xionghaiyang
 * @Date 2025-06-07 10:39
 * @Description https://leetcode.cn/problems/diameter-of-binary-tree
 * 543. 二叉树的直径
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。
 * 这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 * 树中节点数目在范围 [1, 10^4] 内
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

    public class Info {
        int height;
        int maxDistance;

        public Info(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return process(root).maxDistance;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = 0;
        maxDistance = Math.max(maxDistance, leftInfo.maxDistance);
        maxDistance = Math.max(maxDistance, rightInfo.maxDistance);
        maxDistance = Math.max(maxDistance, leftInfo.height + rightInfo.height);
        return new Info(height, maxDistance);
    }

}
