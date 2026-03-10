package com.sean.leetcode.LeetCodeInterview0408;

/**
 * @Author xionghaiyang
 * @Date 2026-03-10 18:57
 * @Description https://leetcode.cn/problems/first-common-ancestor-lcci
 * 面试题 04.08. 首个共同祖先
 * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。
 * 不得将其他的节点存储在另外的数据结构中。
 * 注意：这不一定是二叉搜索树。
 * 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
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

    public class Info {
        public boolean hasP;
        public boolean hasQ;
        public TreeNode res;

        public Info(boolean hasP, boolean hasQ, TreeNode res) {
            this.hasP = hasP;
            this.hasQ = hasQ;
            this.res = res;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).res;
    }

    private Info process(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(node.left, p, q);
        Info rightInfo = process(node.right, p, q);
        boolean hasP = leftInfo.hasP || rightInfo.hasP || node == p;
        boolean hasQ = leftInfo.hasQ || rightInfo.hasQ || node == q;
        TreeNode res = null;
        if (leftInfo.res != null) {
            res = leftInfo.res;
        } else if (rightInfo.res != null) {
            res = rightInfo.res;
        } else if (hasP && hasQ) {
            res = node;
        }
        return new Info(hasP, hasQ, res);
    }

}
