package com.sean.course01.lesson07;

/**
 * @Author xionghaiyang
 * @Date 2025-03-25 21:50
 * @Description 是否是二叉搜索树
 */
public class Code05_IsBinarySearchTree {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.val;
        int min = x.val;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }
        boolean isBST = false;
        boolean leftIsBST = leftInfo == null ? true : leftInfo.isBST;
        boolean rightIsBST = rightInfo == null ? true : rightInfo.isBST;
        boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
        boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > x.val);
        if (leftIsBST && rightIsBST && leftMaxLessX && rightMinMoreX) {
            isBST = true;
        }
        return new Info(isBST, max, min);
    }

}
