package com.sean.leetcode.LeetCode437;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-01 11:34
 * @Description: https://leetcode.cn/problems/path-sum-iii/
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
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

    int res = 0;

    public int pathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        process1(root, targetSum);
        pathSum1(root.left, targetSum);
        pathSum1(root.right, targetSum);
        return res;
    }

    private void process1(TreeNode root, long targetSum) {
        if (root == null) {
            return;
        }
        if (root.val == targetSum) {
            res++;
        }
        process1(root.left, targetSum - root.val);
        process1(root.right, targetSum - root.val);
    }

    Map<Long, Integer> prefix = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        prefix.put(0L, 1);
        return dfs(root, 0, targetSum);
    }

    private int dfs(TreeNode root, long cur, int targetSum) {
        if (root == null) {
            return 0;
        }
        cur += root.val;
        int res = prefix.getOrDefault(cur - targetSum, 0);
        prefix.put(cur, prefix.getOrDefault(cur, 0) + 1);
        res += dfs(root.left, cur, targetSum);
        res += dfs(root.right, cur, targetSum);
        prefix.put(cur, prefix.getOrDefault(cur, 0) - 1);
        return res;
    }

}
