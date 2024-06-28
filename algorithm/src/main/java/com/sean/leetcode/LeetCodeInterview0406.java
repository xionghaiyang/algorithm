package com.sean.leetcode;

import java.util.Stack;

public class LeetCodeInterview0406 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null, cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (pre == p) {
                return cur;
            }
            pre = cur;
            cur = cur.right;
        }
        return null;
    }

}
