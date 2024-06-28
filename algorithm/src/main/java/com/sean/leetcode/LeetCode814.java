package com.sean.leetcode;

public class LeetCode814 {

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

    class Info {
        TreeNode treeNode;
        boolean flag;

        public Info(TreeNode treeNode, boolean flag) {
            this.treeNode = treeNode;
            this.flag = flag;
        }
    }


    public TreeNode pruneTree1(TreeNode root) {
        return process(root).treeNode;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(null, false);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        root.left = leftInfo.treeNode;
        root.right = rightInfo.treeNode;
        if (root.val == 1) {
            return new Info(root, true);
        } else {
            if (leftInfo.flag || rightInfo.flag) {
                return new Info(root, true);
            } else {
                return new Info(null, false);
            }
        }
    }


    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

}
