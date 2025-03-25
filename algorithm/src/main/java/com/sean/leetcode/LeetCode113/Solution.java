package com.sean.leetcode.LeetCode113;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-03-25 21:46
 * @Description https://leetcode.cn/problems/path-sum-ii
 * 113. 路径总和 II
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
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

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return res;
        }
        process(root, 0, sum);
        return res;
    }

    private void process(TreeNode x, int preSum, int sum) {
        if (x.left == null && x.right == null) {
            if (preSum + x.val == sum) {
                path.add(x.val);
                res.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
            return;
        }
        path.add(x.val);
        preSum += x.val;
        if (x.left != null) {
            process(x.left, preSum, sum);
        }
        if (x.right != null) {
            process(x.right, preSum, sum);
        }
        path.remove(path.size() - 1);
    }

}
