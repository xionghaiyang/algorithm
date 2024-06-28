package com.sean.leetcode.LeetCode2583;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-23 19:15
 * @Description: https://leetcode.cn/problems/kth-largest-sum-in-a-binary-tree/
 * 2583. 二叉树中的第 K 大层和
 * 给你一棵二叉树的根节点 root 和一个正整数 k 。
 * 树中的 层和 是指 同一层 上节点值的总和。
 * 返回树中第 k 大的层和（不一定不同）。
 * 如果树少于 k 层，则返回 -1 。
 * 注意，如果两个节点与根节点的距离相同，则认为它们在同一层。
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

    public long kthLargestLevelSum(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            pq.offer(sum);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        if (pq.size() != k) {
            return -1;
        }
        return pq.peek();
    }

}
