package com.sean.leetcode.LeetCode111;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-04 12:03
 * @Description: https://leetcode.cn/problems/minimum-depth-of-binary-tree
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 * 树中节点数的范围在 [0, 10^5] 内
 * -1000 <= Node.val <= 1000
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

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int minDepth = Integer.MAX_VALUE;
        if (root.left != null) {
            minDepth = Math.min(minDepth(root.left), minDepth);
        }
        if (root.right != null) {
            minDepth = Math.min(minDepth(root.right), minDepth);
        }
        return minDepth + 1;
    }

    public class Info {
        TreeNode node;
        int depth;

        public Info(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<Info> queue = new LinkedList<>();
        queue.offer(new Info(root, 1));
        while (!queue.isEmpty()) {
            Info info = queue.poll();
            TreeNode node = info.node;
            int depth = info.depth;
            if (node.left == null && node.right == null) {
                return depth;
            }
            if (node.left != null) {
                queue.offer(new Info(node.left, depth + 1));
            }
            if (node.right != null) {
                queue.offer(new Info(node.right, depth + 1));
            }
        }
        return 0;
    }

}
