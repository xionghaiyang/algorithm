package com.sean.lintcode;

import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/7/26 16:31
 */
public class LintCode661 {

    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }


    public TreeNode convertBST1(TreeNode root) {
        process(root, 0);
        return root;
    }

    private int process(TreeNode root, int sum) {
        if (root == null) {
            return sum;
        }
        root.val += process(root.right, sum);
        return process(root.left, root.val);
    }


    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            sum += cur.val;
            cur.val = sum;
            cur = cur.left;
        }
        return root;
    }

}
