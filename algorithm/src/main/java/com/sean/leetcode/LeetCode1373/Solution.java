package com.sean.leetcode.LeetCode1373;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-22 13:36
 * @Description: https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree/
 * 1373. 二叉搜索子树的最大键值和
 * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
 * 二叉搜索树的定义如下：
 * 任意节点的左子树中的键值都 小于 此节点的键值。
 * 任意节点的右子树中的键值都 大于 此节点的键值。
 * 任意节点的左子树和右子树都是二叉搜索树。
 */
public class Solution {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Info {
        boolean flag; //是否是二叉搜索树
        int max; //二叉搜索树最大值
        int min; //二叉搜索树最小值
        int sum; //二叉搜索树的键值和

        public Info(boolean flag) {
            this.flag = flag;
        }

    }

    int maxSumBST = 0;

    public int maxSumBST(TreeNode root) {
        process(root);
        return maxSumBST;
    }

    private Info process(TreeNode node) {
        //如果节点为null,则返回null
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        boolean flag = (leftInfo == null || leftInfo.flag)
                && (rightInfo == null || rightInfo.flag)
                && (leftInfo == null || leftInfo.max < node.val)
                && (rightInfo == null || rightInfo.min > node.val);
        Info info = new Info(flag);
        if (flag) {
            info.min = node.val;
            info.max = node.val;
            info.sum = node.val;
            if (leftInfo != null) {
                info.min = Math.min(info.min, leftInfo.min);
                info.sum += leftInfo.sum;
            }
            if (rightInfo != null) {
                info.max = Math.max(info.max, rightInfo.max);
                info.sum += rightInfo.sum;
            }
            if (info.sum > maxSumBST) {
                maxSumBST = info.sum;
            }
        }
        return info;
    }

}
