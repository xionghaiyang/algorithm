package com.sean.nowcoder;

public class NowCoderBM33 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode Mirror1(TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        TreeNode left = Mirror1(pRoot.left);
        TreeNode right = Mirror1(pRoot.right);
        pRoot.left = right;
        pRoot.right = left;
        return pRoot;
    }

}
