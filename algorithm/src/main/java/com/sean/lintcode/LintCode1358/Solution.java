package com.sean.lintcode.LintCode1358;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-20 11:40
 * @Description: https://www.lintcode.com/problem/1358/?showListFe=true&page=1&problemTypeId=11&pageSize=50
 * 1358 · 路径和
 * 描述
 * 给定二叉树和求和，确定树是否具有根到叶路径，使得沿路径的所有值相加等于给定的总和。
 */
public class Solution {

    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public boolean pathSum(TreeNode root, int sum) {
        if (root == null) {
            return sum == 0;
        }
        return pathSum(root.left, sum - root.val) || pathSum(root.right, sum - root.val);
    }

}
