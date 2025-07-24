package com.sean.leetcode.LeetCode236;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-09 16:36
 * @Description: https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
 * 最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 树中节点数目在范围 [2, 10^5] 内。
 * -10^9 <= Node.val <= 10^9
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
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

    private List<TreeNode> pList;
    private boolean pFound = false;
    private List<TreeNode> qList;
    private boolean qFound = false;
    private List<TreeNode> temp = new ArrayList<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        find(root, p, q);
        TreeNode res = null;
        for (int i = 0, j = 0; i < pList.size() && j < qList.size(); i++, j++) {
            if (pList.get(i) == qList.get(j)) {
                res = pList.get(i);
            } else {
                return res;
            }
        }
        return res;
    }

    private void find(TreeNode root, TreeNode p, TreeNode q) {
        temp.add(root);
        if (root == p) {
            pList = new ArrayList<>(temp);
            pFound = true;
        } else if (root == q) {
            qList = new ArrayList<>(temp);
            qFound = true;
        }
        if (pFound && qFound) {
            return;
        }
        if (root.left != null) {
            find(root.left, p, q);
        }
        if (root.right != null) {
            find(root.right, p, q);
        }
        temp.remove(root);
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    public class Info {
        private boolean hasP;
        private boolean hasQ;
        private TreeNode bothParent;

        public Info(boolean hasP, boolean hasQ, TreeNode bothParent) {
            this.hasP = hasP;
            this.hasQ = hasQ;
            this.bothParent = bothParent;
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).bothParent;
    }

    private Info process(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(root.left, p, q);
        Info rightInfo = process(root.right, p, q);
        boolean hasP = leftInfo.hasP || rightInfo.hasP || root == p;
        boolean hasQ = leftInfo.hasQ || rightInfo.hasQ || root == q;
        TreeNode bothParent = null;
        if (leftInfo.bothParent != null) {
            bothParent = leftInfo.bothParent;
        }
        if (rightInfo.bothParent != null) {
            bothParent = rightInfo.bothParent;
        }
        if (bothParent == null && hasP && hasQ) {
            bothParent = root;
        }
        return new Info(hasP, hasQ, bothParent);
    }

}
