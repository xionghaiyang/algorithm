package com.sean.leetcode.LeetCode106;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-12 14:39
 * @Description: https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/?envType=study-plan-v2&envId=top-interview-150
 * 106. 从中序与后序遍历序列构造二叉树
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历，
 * postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
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

    int post_index;
    int[] inorder;
    int[] postorder;
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        int n = postorder.length;
        post_index = n - 1;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return helper(0, n - 1);
    }

    private TreeNode helper(int in_left, int in_right) {
        if (in_left > in_right) {
            return null;
        }
        //选择post_index位置的元素作为当前子树根节点
        int root_val = postorder[post_index];
        TreeNode root = new TreeNode(root_val);
        //根据root所在位置分成左右两棵子树
        int index = map.get(root_val);
        //下标减一
        post_index--;
        //构造右子树
        root.right = helper(index + 1, in_right);
        //构造左子树
        root.left = helper(in_left, index - 1);
        return root;
    }

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        int n = postorder.length;
        TreeNode root = new TreeNode(postorder[n - 1]);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int inorderIndex = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }

}
