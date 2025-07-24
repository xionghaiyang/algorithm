package com.sean.leetcode.LeetCode222;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-13 21:17
 * @Description: https://leetcode.cn/problems/count-complete-tree-nodes
 * 222. 完全二叉树的节点个数
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：
 * 在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~ 2^h 个节点。
 * 树中节点的数目范围是[0, 5 * 10^4]
 * 0 <= Node.val <= 5 * 10^4
 * 题目数据保证输入的树是 完全二叉树
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

    public int countNodes(TreeNode root) {
        int leftHight = 0, rightHight = 0;
        TreeNode node = root;
        while (node != null) {
            node = node.left;
            leftHight++;
        }
        node = root;
        while (node != null) {
            node = node.right;
            rightHight++;
        }
        if (leftHight == rightHight) {
            return (int) Math.pow(2, leftHight) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

}