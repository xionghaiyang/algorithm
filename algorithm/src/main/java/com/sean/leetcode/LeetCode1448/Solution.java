package com.sean.leetcode.LeetCode1448;

/**
 * @Author xionghaiyang
 * @Date 2025-09-03 18:54
 * @Description https://leetcode.cn/problems/count-good-nodes-in-binary-tree
 * 1448. 统计二叉树中好节点的数目
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 * 二叉树中节点数目范围是 [1, 10^5] 。
 * 每个节点权值的范围是 [-10^4, 10^4] 。
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
    
    public int goodNodes(TreeNode root) {
        return process(root, root.val);
    }

    private int process(TreeNode root, int preMax) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (preMax <= root.val) {
            res++;
        }
        preMax = Math.max(root.val, preMax);
        res += process(root.left, preMax);
        res += process(root.right, preMax);
        return res;
    }

}
