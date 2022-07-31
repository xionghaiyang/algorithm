package com.sean.leetcode;

import java.util.LinkedList;

/**
 * 对称二叉树
 * https://leetcode-cn.com/problems/symmetric-tree/
 */
public class LeetCode101 {

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

    //递归
    public static boolean isSymmetric1(TreeNode root) {
        return check1(root, root);
    }

    private static boolean check1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check1(p.left, q.right) && check1(p.right, q.left);
    }

    //迭代
    public static boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    private static boolean check(TreeNode u, TreeNode v) {
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }
            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }

}
