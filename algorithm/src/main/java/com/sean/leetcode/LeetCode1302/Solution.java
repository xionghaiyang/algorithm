package com.sean.leetcode.LeetCode1302;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-17 11:33
 * @Description: https://leetcode.cn/problems/deepest-leaves-sum/
 * 1302. 层数最深叶子节点的和
 * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
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


    public int deepestLeavesSum1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        //Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                res += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }

    int maxLevel = -1;
    int sum = 0;

    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    public void dfs(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (level > maxLevel) {
            maxLevel = level;
            sum = node.val;
        } else if (level == maxLevel) {
            sum += node.val;
        }
        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }

}
