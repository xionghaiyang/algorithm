package com.sean.nowcoder;


public class NowCoderBM31 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public static boolean process(TreeNode treeNode1, TreeNode treeNode2) {
        if (treeNode1 == null && treeNode2 == null) {
            return true;
        }
        if (treeNode1 == null || treeNode2 == null || treeNode1.val != treeNode2.val) {
            return false;
        }
        return process(treeNode1.left, treeNode2.right) && process(treeNode1.right, treeNode2.left);
    }

    public static boolean isSymmetrical(TreeNode pRoot) {
        return process(pRoot, pRoot);
    }

}
