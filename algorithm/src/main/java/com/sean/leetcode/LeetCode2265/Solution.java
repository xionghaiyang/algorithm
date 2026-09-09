package com.sean.leetcode.LeetCode2265;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-10 05:49
 * @Description: https://leetcode.cn/problems/count-nodes-equal-to-average-of-subtree
 * 2265. 统计值等于子树平均值的节点数
 * 给你一棵二叉树的根节点 root ，找出并返回满足要求的节点数，要求节点的值等于其 子树 中值的 平均值 。
 * 注意：
 * n 个元素的平均值可以由 n 个元素 求和 然后再除以 n ，并 向下舍入 到最近的整数。
 * root 的 子树 由 root 和它的所有后代组成。
 * 树中节点数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 1000
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
        private int sum;
        private int cnt;
        private int res;

        public Info(int sum, int cnt, int res) {
            this.sum = sum;
            this.cnt = cnt;
            this.res = res;
        }
    }

    public int averageOfSubtree(TreeNode root) {
        Info info = process(root);
        return info != null ? info.res : 0;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        int sum = root.val, cnt = 1, res = 0;
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        if (leftInfo != null) {
            sum += leftInfo.sum;
            cnt += leftInfo.cnt;
            res += leftInfo.res;
        }
        if (rightInfo != null) {
            sum += rightInfo.sum;
            cnt += rightInfo.cnt;
            res += rightInfo.res;
        }
        res += root.val == sum / cnt ? 1 : 0;
        return new Info(sum, cnt, res);
    }

}
