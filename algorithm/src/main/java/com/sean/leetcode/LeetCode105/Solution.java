package com.sean.leetcode.LeetCode105;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-20 15:32
 * @Description: https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
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

    //前序遍历:[根节点，[左子树的前序遍历结果]，[右子树的前序遍历结果]]
    //中序遍历:[[左子树的中序遍历结果],根节点,[右子树的中序遍历结果]]
    public TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        return build(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    //有一棵树，先序结果是pre[left1...right1],中序结果是in[left2...right2]
    //请建出整棵树返回头节点
    private TreeNode build(int[] pre, int left1, int right1, int[] in, int left2, int right2) {
        if (left1 > right1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[left1]);
        if (left1 == right1) {
            return head;
        }
        int find = left2;
        while (in[find] != pre[left1]) {
            find++;
        }
        head.left = build(pre, left1 + 1, left1 + find - left2, in, left2, find - 1);
        head.right = build(pre, left1 + find - left2 + 1, right1, in, find + 1, right2);
        return head;
    }

    public TreeNode buildTree1(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int n = in.length;
        for (int i = 0; i < n; i++) {
            map.put(in[i], i);
        }
        return build(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    private TreeNode build(int[] pre, int left1, int right1, int[] in, int left2, int right2, Map<Integer, Integer> map) {
        if (left1 > right1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[left1]);
        if (left1 == right1) {
            return head;
        }
        int find = map.get(pre[left1]);
        head.left = build(pre, left1 + 1, left1 + find - left2, in, left2, find - 1, map);
        head.right = build(pre, left1 + find - left2 + 1, right1, in, find + 1, right2, map);
        return head;
    }

}
