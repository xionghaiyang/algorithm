package com.sean.nowcoder;

public class NowCoderBM36 {

    public static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        int depth;
        boolean isBST;

        public Info(int depth, boolean isBST) {
            this.depth = depth;
            this.isBST = isBST;
        }
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, true);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int depth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
        boolean isBST = leftInfo.isBST && rightInfo.isBST && Math.abs(leftInfo.depth - rightInfo.depth) <= 1;
        return new Info(depth, isBST);
    }

    public static boolean IsBalanced_Solution(TreeNode root) {
        return process(root).isBST;
    }

}
