package com.sean.leetcode.LeetCode988;

/**
 * @Author xionghaiyang
 * @Date 2025-09-23 12:31
 * @Description https://leetcode.cn/problems/smallest-string-starting-from-leaf
 * 988. 从叶结点开始的最小字符串
 * 给定一颗根结点为 root 的二叉树，树中的每一个结点都有一个 [0, 25] 范围内的值，分别代表字母 'a' 到 'z'。
 * 返回 按字典序最小 的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。
 * 注：字符串中任何较短的前缀在 字典序上 都是 较小 的：
 * 例如，在字典序上 "ab" 比 "aba" 要小。
 * 叶结点是指没有子结点的结点。
 * 节点的叶节点是没有子节点的节点。
 * 给定树的结点数在 [1, 8500] 范围内
 * 0 <= Node.val <= 25
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

    String res = "";

    public String smallestFromLeaf(TreeNode root) {
        dfs(root, "");
        return res;
    }

    private void dfs(TreeNode node, String str) {
        if (node == null) {
            return;
        }
        str = (char) (node.val + 'a') + str;
        if (node.left == null && node.right == null) {
            if (res == "" || str.compareTo(res) < 0) {
                res = str;
            }
            return;
        }
        dfs(node.left, str);
        dfs(node.right, str);
    }

}
