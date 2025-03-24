package com.sean.course01.lesson06;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-03-24 21:10
 * @Description https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 用先序数组和中序数组重建一棵树
 */
public class Code05_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        return f(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    //有一棵树，先序结果是pre[L1...R1],中序结果是in[L2...R2]
    //请建出整棵树返回头节点
    private TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
        if (L1 > R1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }
        int find = L2;
        while (in[find] != pre[L1]) {
            find++;
        }
        head.left = f(pre, L1 + 1, L1 + find - L2, in, L2, find - 1);
        head.right = f(pre, L1 + find - L2 + 1, R1, in, find + 1, R2);
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
        return g(pre, 0, n - 1, in, 0, n - 1, map);
    }

    private TreeNode g(int[] pre, int L1, int R1, int[] in, int L2, int R2, Map<Integer, Integer> map) {
        if (L1 > R1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }
        int find = map.get(pre[L1]);
        head.left = g(pre, L1 + 1, L1 + find - L2, in, L2, find - 1, map);
        head.right = g(pre, L1 + find - L2 + 1, R1, in, find + 1, R2, map);
        return head;
    }

}
