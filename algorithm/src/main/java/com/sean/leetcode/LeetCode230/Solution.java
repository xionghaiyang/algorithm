package com.sean.leetcode.LeetCode230;

/**
 * @Author xionghaiyang
 * @Date 2025-06-09 08:37
 * @Description https://leetcode.cn/problems/kth-smallest-element-in-a-bst
 * 230. 二叉搜索树中第 K 小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 10^4
 * 0 <= Node.val <= 10^4
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

    public class Info {
        public int size;
        public Integer res;

        public Info(int size) {
            this.size = size;
        }

        public Info(int size, Integer res) {
            this.size = size;
            this.res = res;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        return process(root, k).res;
    }

    private Info process(TreeNode root, int k) {
        if (root == null) {
            return new Info(0);
        }
        Info leftInfo = process(root.left, k);
        if (leftInfo.res != null) {
            return leftInfo;
        }
        int size = leftInfo.size + 1;
        Integer res = null;
        if (size == k) {
            res = root.val;
        } else {
            Info rightInfo = process(root.right, k - size);
            if (rightInfo.res != null) {
                return rightInfo;
            }
            size += rightInfo.size;
        }
        return new Info(size, res);
    }

}
