package com.sean.leetcode.LeetCode1261;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-12 13:43
 * @Description: https://leetcode.cn/problems/find-elements-in-a-contaminated-binary-tree/
 * 1261. 在受污染的二叉树中查找元素
 * 给出一个满足下述规则的二叉树：
 * root.val == 0
 * 如果 treeNode.val == x 且 treeNode.left != null，那么 treeNode.left.val == 2 * x + 1
 * 如果 treeNode.val == x 且 treeNode.right != null，那么 treeNode.right.val == 2 * x + 2
 * 请你先还原二叉树，然后实现 FindElements 类：
 * FindElements(TreeNode* root) 用受污染的二叉树初始化对象，你需要先把它还原。
 * bool find(int target) 判断目标值 target 是否存在于还原后的二叉树中并返回结果。
 */
public class FindElements1 {

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

    private Set<Integer> set;

    public FindElements1(TreeNode root) {
        set = new HashSet<>();
        dfs(root, 0);
    }

    private void dfs(TreeNode node, int val) {
        if (node == null) {
            return;
        }
        node.val = val;
        set.add(val);
        dfs(node.left, val * 2 + 1);
        dfs(node.right, val * 2 + 2);
    }

    public boolean find(int target) {
        return set.contains(target);
    }

}
