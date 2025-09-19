package com.sean.leetcode.LeetCode1530;

/**
 * @Author xionghaiyang
 * @Date 2025-09-19 12:35
 * @Description https://leetcode.cn/problems/number-of-good-leaf-nodes-pairs
 * 1530. 好叶子节点对的数量
 * 给你二叉树的根节点 root 和一个整数 distance 。
 * 如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。
 * 返回树中 好叶子节点对的数量 。
 * tree 的节点数在 [1, 2^10] 范围内。
 * 每个节点的值都在 [1, 100] 之间。
 * 1 <= distance <= 10
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

    private int distance;
    private int res;

    public int countPairs(TreeNode root, int distance) {
        this.distance = distance;
        res = 0;
        dfs(root);
        return res;
    }

    private int[] dfs(TreeNode root) {
        int[] depth = new int[distance + 1];
        if (root == null) {
            return depth;
        }
        if (root.left == null && root.right == null) {
            depth[1]++;
            return depth;
        }
        int[] leftDepth = dfs(root.left);
        int[] rightDepth = dfs(root.right);
        for (int i = 1; i < distance + 1; i++) {
            if (leftDepth[i] > 0) {
                for (int j = 1; j <= distance - i; j++) {
                    res += leftDepth[i] * rightDepth[j];
                }
            }
        }
        for (int i = 1; i < distance + 1; i++) {
            depth[i] += leftDepth[i - 1] + rightDepth[i - 1];
        }
        return depth;
    }

}
