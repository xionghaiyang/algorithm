package com.sean.leetcode.LeetCode559;

import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-08-24 19:41
 * @Description https://leetcode.cn/problems/maximum-depth-of-n-ary-tree
 * 559. N 叉树的最大深度
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 * 树的深度不会超过 1000 。
 * 树的节点数目位于 [0, 10^4] 之间。
 */
public class Solution {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        for (Node node : root.children) {
            res = Math.max(res, maxDepth(node));
        }
        return res + 1;
    }

}
