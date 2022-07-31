package com.sean.lintcode;

public class LintCode1085 {

    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    int res;

    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        process(root);
        return res;
    }

    public int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = process(root.left);
        int right = process(root.right);
        int leftInfo = 0, rightInfo = 0;
        if (root.left != null && root.val == root.left.val) {
            leftInfo += left + 1;
        }
        if (root.right != null && root.val == root.right.val) {
            rightInfo += right + 1;
        }
        res = Math.max(res, leftInfo + rightInfo);
        return Math.max(leftInfo, rightInfo);
    }

}
