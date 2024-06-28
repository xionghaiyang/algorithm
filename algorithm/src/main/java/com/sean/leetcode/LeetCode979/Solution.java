package com.sean.leetcode.LeetCode979;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-05 14:22
 * @Description: https://leetcode.cn/problems/distribute-coins-in-binary-tree/
 * 979. 在二叉树中分配硬币
 * 给你一个有 n 个结点的二叉树的根结点 root ，其中树中每个结点 node 都对应有 node.val 枚硬币。
 * 整棵树上一共有 n 枚硬币。
 * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。
 * 移动可以是从父结点到子结点，或者从子结点移动到父结点。
 * 返回使每个结点上 只有 一枚硬币所需的 最少 移动次数。
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

    private int res;

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return res;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        //子树硬币个数
        int coins = left[0] + right[0] + node.val;
        //子树节点数
        int nodes = left[1] + right[1] + 1;
        res += Math.abs(coins - nodes);
        return new int[]{coins, nodes};
    }

    public int distributeCoins1(TreeNode root) {
        d(root);
        return res;
    }

    private int d(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int d = d(node.left) + d(node.right) + node.val - 1;
        res += Math.abs(d);
        return d;
    }

}
