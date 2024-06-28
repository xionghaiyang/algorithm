package com.sean.leetcode.LeetCode894;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-02 14:22
 * @Description: https://leetcode.cn/problems/all-possible-full-binary-trees/
 * 894. 所有可能的真二叉树
 * 给你一个整数 n ，请你找出所有可能含 n 个节点的 真二叉树 ，并以列表形式返回。
 * 答案中每棵树的每个节点都必须符合 Node.val == 0 。
 * 答案的每个元素都是一棵真二叉树的根节点。
 * 你可以按 任意顺序 返回最终的真二叉树列表。
 * 真二叉树 是一类二叉树，树中每个节点恰好有 0 或 2 个子节点。
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


    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n % 2 == 0) {
            return res;
        }
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        for (int i = 1; i < n; i += 2) {
            List<TreeNode> leftSubTrees = allPossibleFBT(i);
            List<TreeNode> rightSubTrees = allPossibleFBT(n - 1 - i);
            for (TreeNode leftSubTree : leftSubTrees) {
                for (TreeNode rightSubTree : rightSubTrees) {
                    TreeNode root = new TreeNode(0, leftSubTree, rightSubTree);
                    res.add(root);
                }
            }
        }
        return res;
    }

    public List<TreeNode> allPossibleFBT1(int n) {
        if (n % 2 == 0) {
            return new ArrayList<>();
        }
        List<TreeNode>[] dp = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = new ArrayList<>();
        }
        dp[1].add(new TreeNode(0));
        for (int i = 3; i <= n; i += 2) {
            for (int j = 1; j < i; j += 2) {
                for (TreeNode leftSubTree : dp[j]) {
                    for (TreeNode rightSubTree : dp[i - 1 - j]) {
                        TreeNode root = new TreeNode(0, leftSubTree, rightSubTree);
                        dp[i].add(root);
                    }
                }
            }
        }
        return dp[n];
    }

}
