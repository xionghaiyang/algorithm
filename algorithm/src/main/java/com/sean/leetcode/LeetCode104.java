package com.sean.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class LeetCode104 {

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

    //深度优先搜索
    public static int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHight = maxDepth1(root.left);
        int rightHight = maxDepth1(root.right);
        return Math.max(leftHight, rightHight) + 1;
    }

    //广度优先搜索
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            res++;
        }
        return res;
    }
}
