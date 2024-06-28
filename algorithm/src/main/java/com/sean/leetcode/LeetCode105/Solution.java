package com.sean.leetcode.LeetCode105;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-20 15:32
 * @Description: https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
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

    //前序遍历:[根节点，[左子树的前序遍历结果],[右子树的前序遍历结果]]
    //中序遍历:[[左子树的中序遍历结果],根节点,[右子树的中序遍历结果]]

    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        //前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        //在中序遍历中定位根节点
        int inorder_root = map.get(preorder[preorder_root]);
        //先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        //得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        //递归地构造左子树，并连接到根节点
        //先序遍历中[从左边界+1开始的size_left_subtree]个元素就对应了中序遍历中的[左边界开始到根节点定位-1]的元素
        root.left = build(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        //递归地构造右子树，并连接到根节点
        //先序遍历中[从左边界+1+左子树节点数目开始到右边界]的元素就对应了中序遍历中[从根节点定位+1到右边界]的元素
        root.right = build(preorder, inorder, preorder_left + 1 + size_left_subtree, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

}
