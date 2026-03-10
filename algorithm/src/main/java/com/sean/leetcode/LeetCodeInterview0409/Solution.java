package com.sean.leetcode.LeetCodeInterview0409;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-03-10 20:20
 * @Description https://leetcode.cn/problems/bst-sequences-lcci
 * 面试题 04.09. 二叉搜索树序列
 * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。
 * 给定一个由不同节点组成的二叉搜索树 root，输出所有可能生成此树的数组。
 * 二叉搜索树中的节点数在 [0, 1000] 的范围内
 * 1 <= 节点值 <= 10^6
 * 用例保证符合要求的数组数量不超过 5000
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

    public List<List<Integer>> BSTSequences(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            res.add(new ArrayList<>());
            return res;
        }
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        bfs(res, new ArrayList<>(), list);
        return res;
    }

    private void bfs(List<List<Integer>> res, List<Integer> temp, List<TreeNode> list) {
        if (list.isEmpty()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        List<TreeNode> copy = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);
            temp.add(node.val);
            list.remove(i);
            if (node.left != null) {
                list.add(node.left);
            }
            if (node.right != null) {
                list.add(node.right);
            }
            bfs(res, temp, list);
            temp.remove(temp.size() - 1);
            list = new ArrayList<>(copy);
        }
    }

}
