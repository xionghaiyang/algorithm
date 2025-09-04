package com.sean.leetcode.LeetCode606;

/**
 * @Author xionghaiyang
 * @Date 2025-09-04 09:49
 * @Description https://leetcode.cn/problems/construct-string-from-binary-tree
 * 606. 根据二叉树创建字符串
 * 给你二叉树的根节点 root ，请你采用前序遍历的方式，将二叉树转化为一个由括号和整数组成的字符串，返回构造出的字符串。
 * 空节点使用一对空括号对 "()" 表示，转化后需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 * 树中节点的数目范围是 [1, 10^4]
 * -1000 <= Node.val <= 1000
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

    public String tree2str(TreeNode root) {
        StringBuilder res = new StringBuilder();
        dfs(root, res);
        return res.toString();
    }

    private void dfs(TreeNode root, StringBuilder res) {
        if (root == null) {
            return;
        }
        res.append(root.val);
        if (root.left == null && root.right == null) {
            return;
        }
        res.append('(');
        dfs(root.left, res);
        res.append(')');
        if (root.right != null) {
            res.append('(');
            dfs(root.right, res);
            res.append(')');
        }
    }

}
