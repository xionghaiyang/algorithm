package com.sean.leetcode.LeetCode2641;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-07 08:44
 * @Description: https://leetcode.cn/problems/cousins-in-binary-tree-ii/
 * 2641. 二叉树的堂兄弟节点 II
 * 给你一棵二叉树的根 root ，请你将每个节点的值替换成该节点的所有 堂兄弟节点值的和 。
 * 如果两个节点在树中有相同的深度且它们的父节点不同，那么它们互为 堂兄弟 。
 * 请你返回修改值之后，树的根 root 。
 * 注意，一个节点的深度指的是从树根节点到这个节点经过的边数。
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

    public TreeNode replaceValueInTree(TreeNode root) {
        root.val = 0;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            List<TreeNode> temp = list;
            list = new ArrayList<>();
            int nextLevelSum = 0;
            for (TreeNode node : temp) {
                if (node.left != null) {
                    list.add(node.left);
                    nextLevelSum += node.left.val;
                }
                if (node.right != null) {
                    list.add(node.right);
                    nextLevelSum += node.right.val;
                }
            }
            for (TreeNode node : temp) {
                int childrenSum = (node.left != null ? node.left.val : 0) + (node.right != null ? node.right.val : 0);
                if (node.left != null) {
                    node.left.val = nextLevelSum - childrenSum;
                }
                if (node.right != null) {
                    node.right.val = nextLevelSum - childrenSum;
                }
            }
        }
        return root;
    }

}
