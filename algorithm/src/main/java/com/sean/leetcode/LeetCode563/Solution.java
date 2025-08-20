package com.sean.leetcode.LeetCode563;

/**
 * @Author xionghaiyang
 * @Date 2025-08-20 17:12
 * @Description https://leetcode.cn/problems/binary-tree-tilt
 * 563. 二叉树的坡度
 * 给你一个二叉树的根节点 root ，计算并返回 整个树 的坡度 。
 * 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。
 * 如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。
 * 空结点的坡度是 0 。
 * 整个树 的坡度就是其所有节点的坡度之和。
 * 树中节点数目的范围在 [0, 10^4] 内
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

    public class Info {
        int sum;
        int res;

        public Info(int sum, int res) {
            this.sum = sum;
            this.res = res;
        }
    }

    public int findTilt(TreeNode root) {
        return dfs(root).res;
    }

    private Info dfs(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftInfo = dfs(root.left);
        Info rightInfo = dfs(root.right);
        int sum = root.val + leftInfo.sum + rightInfo.sum;
        int res = Math.abs(leftInfo.sum - rightInfo.sum) + leftInfo.res + rightInfo.res;
        return new Info(sum, res);
    }

}
