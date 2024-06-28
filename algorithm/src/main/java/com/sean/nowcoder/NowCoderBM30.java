package com.sean.nowcoder;

public class NowCoderBM30 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static TreeNode head = null;
    static TreeNode pre = null;

    public static TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        Convert(pRootOfTree.left);
        if (pre == null) {
            head = pRootOfTree;
            pre = pRootOfTree;
        } else {
            pRootOfTree.left = pre;
            pre.right = pRootOfTree;
            pre = pRootOfTree;
        }
        Convert(pRootOfTree.right);
        return head;
    }

}
