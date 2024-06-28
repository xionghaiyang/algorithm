package com.sean.leetcode.LeetCode437;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-09 12:32
 * @Description https://leetcode.cn/problems/path-sum-iii/
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
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

    public int pathSum(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        int res = process(root, targetSum);
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }

    private int process(TreeNode root, long targetSum) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == targetSum) {
            res++;
        }
        res += process(root.left, targetSum - val);
        res += process(root.right, targetSum - val);
        return res;
    }

    public int pathSum1(TreeNode root, long targetSum) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        return process(root, map, 0, targetSum);
    }

    private int process(TreeNode root, Map<Long, Integer> map, long preSum, long targetSum) {
        if (root == null) {
            return 0;
        }
        preSum += root.val;
        int res = map.getOrDefault(preSum - targetSum, 0);
        map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        res += process(root.left, map, preSum, targetSum);
        res += process(root.right, map, preSum, targetSum);
        map.put(preSum, map.getOrDefault(preSum, 0) - 1);
        return res;
    }

}
