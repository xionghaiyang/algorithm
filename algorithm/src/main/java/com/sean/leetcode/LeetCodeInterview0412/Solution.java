package com.sean.leetcode.LeetCodeInterview0412;

/**
 * @Author xionghaiyang
 * @Date 2026-03-11 09:52
 * @Description https://leetcode.cn/problems/paths-with-sum-lcci
 * 面试题 04.12. 求和路径
 * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。
 * 设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。
 * 注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
 * 节点总数 <= 10000
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

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int res = rootSum(root, sum);
        res += pathSum(root.left, sum);
        res += pathSum(root.right, sum);
        return res;
    }

    private int rootSum(TreeNode root, int sum) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == sum) {
            res++;
        }
        res += rootSum(root.left, sum - val);
        res += rootSum(root.right, sum - val);
        return res;
    }

}
