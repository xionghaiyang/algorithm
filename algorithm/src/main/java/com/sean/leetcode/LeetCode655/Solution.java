package com.sean.leetcode.LeetCode655;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-22 11:33
 * @Description: https://leetcode.cn/problems/print-binary-tree/
 * 655. 输出二叉树
 * 给你一棵二叉树的根节点 root ，请你构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res ，用以表示树的 格式化布局 。
 * 构造此格式化布局矩阵需要遵循以下规则：
 * 树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。
 * 矩阵的列数 n 应该等于 2^(height+1) - 1 。
 * 根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
 * 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2^(height-r-1)] ，右子节点放置在 res[r+1][c+2^(height-r-1)] 。
 * 继续这一过程，直到树中的所有节点都妥善放置。
 * 任意空单元格都应该包含空字符串 "" 。
 * 返回构造得到的矩阵 res 。
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

    public List<List<String>> printTree1(TreeNode root) {
        int height = root != null ? getDepth(root) : -1;
        int m = height + 1;
        int n = (1 << (height + 1)) - 1;
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add("");
            }
            res.add(row);
        }
        dfs(res, root, 0, (n - 1) >> 1, height);
        return res;
    }

    private int getDepth(TreeNode root) {
        int h = 0;
        if (root.left != null) {
            h = Math.max(h, getDepth(root.left) + 1);
        }
        if (root.right != null) {
            h = Math.max(h, getDepth(root.right) + 1);
        }
        return h;
    }

    private void dfs(List<List<String>> res, TreeNode root, int r, int c, int height) {
        res.get(r).set(c, Integer.toString(root.val));
        if (root.left != null) {
            dfs(res, root.left, r + 1, c - (1 << (height - r - 1)), height);
        }
        if (root.right != null) {
            dfs(res, root.right, r + 1, c + (1 << (height - r - 1)), height);
        }
    }

    public class Info {
        TreeNode treeNode;
        int r;
        int c;

        public Info(TreeNode treeNode, int r, int c) {
            this.treeNode = treeNode;
            this.r = r;
            this.c = c;
        }

    }

    public List<List<String>> printTree(TreeNode root) {
        int height = getDepth1(root);
        int m = height + 1;
        int n = (1 << (height + 1)) - 1;
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add("");
            }
            res.add(row);
        }
        Queue<Info> queue = new ArrayDeque<>();
        queue.offer(new Info(root, 0, (n - 1) >> 1));
        while (!queue.isEmpty()) {
            Info info = queue.poll();
            TreeNode treeNode = info.treeNode;
            int r = info.r, c = info.c;
            res.get(r).set(c, Integer.toString(treeNode.val));
            if (treeNode.left != null) {
                queue.offer(new Info(treeNode.left, r + 1, c - (1 << (height - r - 1))));
            }
            if (treeNode.right != null) {
                queue.offer(new Info(treeNode.right, r + 1, c + (1 << (height - r - 1))));
            }
        }
        return res;
    }

    private int getDepth1(TreeNode root) {
        int res = -1;
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }

}
