package com.sean.leetcode.LeetCode1026;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-18 08:17
 * @Description: https://leetcode.cn/problems/maximum-difference-between-node-and-ancestor/
 * 1026. 节点与其祖先之间的最大差值
 * 给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
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
        int min;
        int max;
        int maxDiff;

        public Info(int min, int max, int maxDiff) {
            this.min = min;
            this.max = max;
            this.maxDiff = maxDiff;
        }
    }

    public int maxAncestorDiff(TreeNode root) {
        return process(root).maxDiff;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int min = root.val;
        int max = root.val;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        int maxDiff = Math.max(root.val - min, max - root.val);
        if (leftInfo != null) {
            maxDiff = Math.max(maxDiff, leftInfo.maxDiff);
        }
        if (rightInfo != null) {
            maxDiff = Math.max(maxDiff, rightInfo.maxDiff);
        }
        return new Info(min, max, maxDiff);
    }

    public int maxAncestorDiff1(TreeNode root) {
        return dfs(root, root.val, root.val);
    }

    private int dfs(TreeNode root, int min, int max) {
        if (root == null) {
            return 0;
        }
        int diff = Math.max(Math.abs(root.val - min), Math.abs(root.val - max));
        min = Math.min(min, root.val);
        max = Math.max(max, root.val);
        diff = Math.max(diff, dfs(root.left, min, max));
        diff = Math.max(diff, dfs(root.right, min, max));
        return diff;
    }

}
