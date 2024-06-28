package com.sean.leetcode.LeetCode669;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-10 11:08
 * @Description: https://leetcode.cn/problems/trim-a-binary-search-tree/
 * 669. 修剪二叉搜索树
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。
 * 通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
 * 修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。
 * 可以证明，存在 唯一的答案 。
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
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


    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val >= low && root.val <= high) {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        } else if (root.val < low) {
            return trimBST(root.right, low, high);
        } else {
            return trimBST(root.left, low, high);
        }
    }

    public TreeNode trimBST1(TreeNode root, int low, int high) {
        //root来到第一个满足条件的头节点
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        //头节点为空返回空
        if (root == null) {
            return null;
        }
        //遍历左子树
        TreeNode cur = root;
        while (cur.left != null) {
            if (cur.left.val < low) {
                cur.left = cur.left.right;
            } else {
                cur = cur.left;
            }
        }
        //遍历右子树
        cur = root;
        while (cur.right != null) {
            if (cur.right.val > high) {
                cur.right = cur.right.left;
            } else {
                cur = cur.right;
            }
        }
        return root;
    }

}
