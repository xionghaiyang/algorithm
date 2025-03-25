package com.sean.course01.lesson07;

/**
 * @Author xionghaiyang
 * @Date 2025-03-25 20:49
 * @Description https://leetcode.cn/problems/path-sum
 * 能否组成路径和
 */
public class Code03_PathSum {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private boolean isSum = false;

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        process(root, 0, sum);
        return isSum;
    }

    private void process(TreeNode x, int preSum, int sum) {
        //x是叶节点
        if (x.left == null && x.right == null) {
            if (x.val + preSum == sum) {
                isSum = true;
            }
            return;
        }
        //x是非叶节点
        preSum += x.val;
        if (x.left != null) {
            process(x.left, preSum, sum);
        }
        if (x.right != null) {
            process(x.right, preSum, sum);
        }
    }

    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return process1(root, sum);
    }

    private boolean process1(TreeNode root, int rest) {
        if (root.left == null && root.right == null) {
            return root.val == rest;
        }
        boolean res = root.left != null ? process1(root.left, rest - root.val) : false;
        res |= root.right != null ? process1(root.right, rest - root.val) : false;
        return res;
    }

}
