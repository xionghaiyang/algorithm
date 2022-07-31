package com.sean.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode111 {

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

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int mindepth = Integer.MAX_VALUE;
        if (root.left != null) {
            mindepth = Math.min(minDepth(root.left), mindepth);
        }
        if (root.right != null) {
            mindepth = Math.min(minDepth(root.right), mindepth);
        }
        return mindepth + 1;
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
