package com.sean.leetcode.LeetCodeOffer27;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-28 20:06
 * @Description: https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 27. 二叉树的镜像
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        root.left = mirrorTree(rightNode);
        root.right = mirrorTree(leftNode);
        return root;
    }

}
