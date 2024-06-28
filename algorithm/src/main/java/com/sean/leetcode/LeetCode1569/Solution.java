package com.sean.leetcode.LeetCode1569;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-29 14:10
 * @Description: https://leetcode.cn/problems/number-of-ways-to-reorder-array-to-get-same-bst/description/
 * 1569. 将子数组重新排序得到同一个二叉搜索树的方案数
 * 给你一个数组 nums 表示 1 到 n 的一个排列。
 * 我们按照元素在 nums 中的顺序依次插入一个初始为空的二叉搜索树（BST）。
 * 请你统计将 nums 重新排序后，统计满足如下条件的方案数：重排后得到的二叉搜索树与 nums 原本数字顺序得到的二叉搜索树相同。
 * 比方说，给你 nums = [2,1,3]，我们得到一棵 2 为根，1 为左孩子，3 为右孩子的树。
 * 数组 [2,3,1] 也能得到相同的 BST，但 [3,2,1] 会得到一棵不同的 BST 。
 * 请你返回重排 nums 后，与原数组 nums 得到相同二叉搜索树的方案数。
 * 由于答案可能会很大，请将结果对 10^9 + 7 取余数。
 */
public class Solution {

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;
        int size;
        int res;

        public TreeNode(int value) {
            this.value = value;
            size = 1;
            res = 0;
        }
    }

    int mod = 1000000007;
    long[][] c;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        c = new long[n][n];
        c[0][0] = 1;
        for (int i = 1; i < n; i++) {
            c[i][0] = 1;
            for (int j = 1; j < n; j++) {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;
            }
        }
        TreeNode root = new TreeNode(nums[0]);
        for (int i = 1; i < n; i++) {
            insert(root, nums[i]);
        }
        dfs(root);
        return (root.res - 1 + mod) % mod;
    }

    private void insert(TreeNode root, int value) {
        TreeNode cur = root;
        while (true) {
            cur.size++;
            if (value < cur.value) {
                if (cur.left == null) {
                    cur.left = new TreeNode(value);
                    return;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new TreeNode(value);
                    return;
                }
                cur = cur.right;
            }
        }
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        int lsize = node.left != null ? node.left.size : 0;
        int rsize = node.right != null ? node.right.size : 0;
        int lres = node.left != null ? node.left.res : 1;
        int rres = node.right != null ? node.right.res : 1;
        node.res = (int) (c[lsize + rsize][lsize] % mod * lres % mod * rres % mod);
    }

}
