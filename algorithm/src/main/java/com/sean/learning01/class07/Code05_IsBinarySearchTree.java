package com.sean.learning01.class07;

public class Code05_IsBinarySearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(TreeNode x) {
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
        boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < max);
        boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > min);
        if (leftIsBST && rightIsBST && leftMaxLessX && rightMinMoreX) {
            isBST = true;
        }
        return new Info(isBST, max, min);
    }

}
