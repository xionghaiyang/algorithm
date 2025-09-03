package com.sean.leetcode.LeetCode337;

/**
 * @Author xionghaiyang
 * @Date 2025-09-03 08:13
 * @Description https://leetcode.cn/problems/house-robber-iii
 * 337. 打家劫舍 III
 * 小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为 root 。
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 给定二叉树的 root 。
 * 返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * 树的节点数在 [1, 10^4] 范围内
 * 0 <= Node.val <= 10^4
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

    public int rob(TreeNode root) {
        int[] value = process(root);
        return Math.max(value[0], value[1]);
    }

    private int[] process(TreeNode node) {
        int[] res = new int[2];
        if (node != null) {
            int[] left = process(node.left);
            int[] right = process(node.right);
            //node没有被打劫
            res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            //node被打劫
            res[1] = left[0] + right[0] + node.val;
        }
        return res;
    }

}
