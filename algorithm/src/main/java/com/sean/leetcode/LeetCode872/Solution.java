package com.sean.leetcode.LeetCode872;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-09-09 19:50
 * @Description https://leetcode.cn/problems/leaf-similar-trees
 * 872. 叶子相似的树
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 * 给定的两棵树结点数在 [1, 200] 范围内
 * 给定的两棵树上的值在 [0, 200] 范围内
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

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        return isEqual(getList(root1), getList(root2));
    }

    private List<Integer> getList(TreeNode node) {
        List<Integer> res = new ArrayList<>();
        dfs(node, res);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            list.add(node.val);
            return;
        }
        dfs(node.left, list);
        dfs(node.right, list);
    }

    private boolean isEqual(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        int size = list1.size();
        for (int i = 0; i < size; i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

}
