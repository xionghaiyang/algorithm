package com.sean.leetcode.LeetCode987;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-13 10:08
 * @Description: https://leetcode.cn/problems/vertical-order-traversal-of-a-binary-tree/
 * 987. 二叉树的垂序遍历
 * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
 * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。
 * 树的根结点位于 (0, 0) 。
 * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。
 * 如果同行同列上有多个结点，则按结点的值从小到大进行排序。
 * 返回二叉树的 垂序遍历 序列。
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

    Map<Integer, PriorityQueue<int[]>> map;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        process(root, 0, 0);
        List<Integer> cols = new ArrayList<>(map.keySet());
        Collections.sort(cols);
        for (int col : cols) {
            PriorityQueue<int[]> pq = map.get(col);
            List<Integer> list = new ArrayList<>();
            while (!pq.isEmpty()) {
                list.add(pq.poll()[1]);
            }
            res.add(list);
        }
        return res;
    }

    private void process(TreeNode root, int row, int col) {
        map.putIfAbsent(col, new PriorityQueue<int[]>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
        map.get(col).add(new int[]{row, root.val});
        if (root.left != null) {
            process(root.left, row + 1, col - 1);
        }
        if (root.right != null) {
            process(root.right, row + 1, col + 1);
        }
    }

}
