package com.sean.base.chapter30;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-30 10:45
 * @Description: TODO
 */
public class Code02_MinDepth {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //下面的方法是一般解
    public int minDepth1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return process(head);
    }

    //返回以x为头的节点，最小深度是多少
    private int process(TreeNode x) {
        if (x.left == null && x.right == null) {
            return 1;
        }
        //左右子树起码有一个不为空
        int leftH = Integer.MAX_VALUE;
        if (x.left != null) {
            leftH = process(x.left);
        }
        int rightH = Integer.MAX_VALUE;
        if (x.right != null) {
            rightH = process(x.right);
        }
        return 1 + Math.min(leftH, rightH);
    }

    //下面的方法是morris遍历的解
    public int minDepth2(TreeNode head) {
        if (head == null) {
            return 0;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        int curLevel = 0;
        int minHeight = Integer.MAX_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                int rightBoardSize = 1;
                while (mostRight.right != null && mostRight.right != cur) {
                    rightBoardSize++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    curLevel++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    if (mostRight.left == null) {
                        minHeight = Math.min(minHeight, curLevel);
                    }
                    curLevel -= rightBoardSize;
                    mostRight.right = null;
                }
            } else {
                curLevel++;
            }
            cur = cur.right;
        }
        int finalRight = 1;
        cur = head;
        while (cur.right != null) {
            finalRight++;
            cur = cur.right;
        }
        if (cur.left == null && cur.right == null) {
            minHeight = Math.min(minHeight, finalRight);
        }
        return minHeight;
    }

}
