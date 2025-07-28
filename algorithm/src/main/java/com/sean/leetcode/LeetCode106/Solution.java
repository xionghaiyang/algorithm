package com.sean.leetcode.LeetCode106;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-12 14:39
 * @Description: https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 106. 从中序与后序遍历序列构造二叉树
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历，
 * postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder 和 postorder 都由 不同 的值组成
 * postorder 中每一个值都在 inorder 中
 * inorder 保证是树的中序遍历
 * postorder 保证是树的后序遍历
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

    //中序遍历:[[左子树的中序遍历结果],根节点,[右子树的中序遍历结果]]
    //后序遍历:[[左子树的后序遍历结果],[右子树的后序遍历结果],根节点]
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return process(map, 0, n - 1, postorder, 0, n - 1);
    }

    private TreeNode process(Map<Integer, Integer> map, int inL, int inR, int[] postorder, int postL, int postR) {
        if (inL > inR || postL > postR) {
            return null;
        }
        int inRootIndex = map.get(postorder[postR]);
        int leftSize = inRootIndex - inL;
        TreeNode left = process(map, inL, inRootIndex - 1, postorder, postL, postL + leftSize - 1);
        TreeNode right = process(map, inRootIndex + 1, inR, postorder, postL + leftSize, postR - 1);
        return new TreeNode(postorder[postR], left, right);
    }

}
