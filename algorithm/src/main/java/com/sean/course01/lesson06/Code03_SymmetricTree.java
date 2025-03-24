package com.sean.course01.lesson06;

/**
 * @Author xionghaiyang
 * @Date 2025-03-24 20:28
 * @Description https://leetcode.cn/problems/symmetric-tree
 * 判断一棵树是否是镜面树
 */
public class Code03_SymmetricTree {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode h1, TreeNode h2) {
        if (h1 == null ^ h2 == null) {
            return false;
        }
        if (h1 == null && h2 == null) {
            return true;
        }
        return h1.val == h2.val && isMirror(h1.left, h2.right) && isMirror(h1.right, h2.left);
    }

}
