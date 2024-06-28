package com.sean.leetcode.LeetCode236;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-09 16:36
 * @Description: https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
 * 最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
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

    List<TreeNode> pList;
    boolean pFound = false;
    List<TreeNode> qList;
    boolean qFound = false;
    List<TreeNode> temp = new ArrayList<>();

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

}
