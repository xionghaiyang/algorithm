package com.sean.leetcode.LeetCode404;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-08-21 12:39
 * @Description https://leetcode.cn/problems/sum-of-left-leaves
 * 404. 左叶子之和
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 * 节点数在 [1, 1000] 范围内
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

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        TreeNode left = root.left;
        if (left != null && left.left == null && left.right == null) {
            sum += left.val;
        }
        return sum;
    }

    public int sumOfLeftLeaves1(TreeNode root) {
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null) {
                    res += node.left.val;
                } else {
                    queue.offer(node.left);
                }
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return res;
    }


}
