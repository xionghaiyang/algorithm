package com.sean.leetcode.LeetCode865;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-15 12:26
 * @Description: https://leetcode.cn/problems/smallest-subtree-with-all-the-deepest-nodes/description/
 * 865. 具有所有最深节点的最小子树
 * 给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。
 * 返回包含原始树中所有 最深节点 的 最小子树 。
 * 如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。
 * 一个节点的 子树 是该节点加上它的所有后代的集合。
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

    class Info {
        TreeNode node;
        int dist;

        public Info(TreeNode node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return process(root).node;
    }

    private Info process(TreeNode node) {
        if (node == null) {
            return new Info(null, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        if (leftInfo.dist > rightInfo.dist) {
            return new Info(leftInfo.node, leftInfo.dist + 1);
        }
        if (leftInfo.dist < rightInfo.dist) {
            return new Info(rightInfo.node, rightInfo.dist + 1);
        }
        return new Info(node, leftInfo.dist + 1);
    }

}
