package com.sean.leetcode.LeetCode1261;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-12 13:19
 * @Description: https://leetcode.cn/problems/find-elements-in-a-contaminated-binary-tree/
 * 1261. 在受污染的二叉树中查找元素
 * 给出一个满足下述规则的二叉树：
 * root.val == 0
 * 如果 treeNode.val == x 且 treeNode.left != null，那么 treeNode.left.val == 2 * x + 1
 * 如果 treeNode.val == x 且 treeNode.right != null，那么 treeNode.right.val == 2 * x + 2
 * 现在这个二叉树受到「污染」，所有的 treeNode.val 都变成了 -1。
 * 请你先还原二叉树，然后实现 FindElements 类：
 * FindElements(TreeNode* root) 用受污染的二叉树初始化对象，你需要先把它还原。
 * bool find(int target) 判断目标值 target 是否存在于还原后的二叉树中并返回结果。
 */
public class FindElements {

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

    private TreeNode root;

    public FindElements(TreeNode root) {
        this.root = root;
        root.val = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                node.left.val = 2 * node.val + 1;
                queue.offer(node.left);
            }
            if (node.right != null) {
                node.right.val = 2 * node.val + 2;
                queue.offer(node.right);
            }
        }
    }

    public boolean find(int target) {
        return process(target, root);
    }

    private boolean process(int target, TreeNode node) {
        if (node == null) {
            return false;
        }
        if (target < node.val) {
            return false;
        }
        if (node.val == target) {
            return true;
        }
        return process(target, node.left) || process(target, node.right);
    }

}
