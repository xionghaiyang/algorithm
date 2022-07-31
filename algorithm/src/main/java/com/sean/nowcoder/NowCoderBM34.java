package com.sean.nowcoder;

public class NowCoderBM34 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static int max = Integer.MIN_VALUE;

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val < max) {
            return false;
        }
        max = root.val;
        return isValidBST(root.right);
    }

}
