package com.sean.leetcode.LeetCode662;

import javafx.util.Pair;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2022-08-27 10:46
 * @Description https://leetcode.cn/problems/maximum-width-of-binary-tree/
 * 662. 二叉树最大宽度
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 * 树的 最大宽度 是所有层中最大的 宽度 。
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。
 * 将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
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

    public int widthOfBinaryTree1(TreeNode root) {
        List<Pair<TreeNode, Integer>> arr = new ArrayList<>();
        arr.add(new Pair<TreeNode, Integer>(root, 1));
        int res = 0;
        while (!arr.isEmpty()) {
            List<Pair<TreeNode, Integer>> temp = new ArrayList<>();
            for (Pair<TreeNode, Integer> pair : arr) {
                TreeNode node = pair.getKey();
                int index = pair.getValue();
                if (node.left != null) {
                    temp.add(new Pair<TreeNode, Integer>(node.left, 2 * index));
                }
                if (node.right != null) {
                    temp.add(new Pair<TreeNode, Integer>(node.right, 2 * index + 1));
                }
            }
            res = Math.max(res, arr.get(arr.size() - 1).getValue() - arr.get(0).getValue() + 1);
            arr = temp;
        }
        return res;
    }

    Map<Integer, Integer> map = new HashMap<>();

    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 1, 1);
    }

    private int dfs(TreeNode node, int depth, int index) {
        if (node == null) {
            return 0;
        }
        map.putIfAbsent(depth, index);
        return Math.max(index - map.get(depth) + 1, Math.max(dfs(node.left, depth + 1, index * 2), dfs(node.right, depth + 1, index * 2 + 1)));
    }

}
