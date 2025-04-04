package com.sean.leetcode.LeetCode1123;

/**
 * @Author xionghaiyang
 * @Date 2025-04-04 08:59
 * @Description https://leetcode.cn/problems/lowest-common-ancestor-of-deepest-leaves
 * 1123. 最深叶节点的最近公共祖先
 * 给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。
 * 回想一下：
 * 叶节点 是二叉树中没有子节点的节点
 * 树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
 * 如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
 * 树中的节点数将在 [1, 1000] 的范围内。
 * 0 <= Node.val <= 1000
 * 每个节点的值都是 独一无二 的。
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

    private TreeNode res;
    private int maxDepth = -1;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        process(root, 0);
        return res;
    }

    private int process(TreeNode node, int depth) {
        if (node == null) {
            maxDepth = Math.max(maxDepth, depth);
            return depth;
        }
        int leftDepth = process(node.left, depth + 1);
        int rightDepth = process(node.right, depth + 1);
        if (leftDepth == rightDepth && leftDepth == maxDepth) {
            res = node;
        }
        return Math.max(leftDepth, rightDepth);
    }

    class Info {
        int height;
        TreeNode lca;

        public Info(int height, TreeNode lca) {
            this.height = height;
            this.lca = lca;
        }
    }

    public TreeNode lcaDeepestLeaves1(TreeNode root) {
        return dfs(root).lca;
    }

    private Info dfs(TreeNode node) {
        if (node == null) {
            return new Info(0, null);
        }
        Info leftInfo = dfs(node.left);
        Info rightInfo = dfs(node.right);
        if (leftInfo.height > rightInfo.height) {
            return new Info(leftInfo.height + 1, leftInfo.lca);
        } else if (leftInfo.height < rightInfo.height) {
            return new Info(rightInfo.height + 1, rightInfo.lca);
        } else {
            return new Info(leftInfo.height + 1, node);
        }
    }

}
