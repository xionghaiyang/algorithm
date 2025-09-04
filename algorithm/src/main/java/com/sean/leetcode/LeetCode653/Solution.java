package com.sean.leetcode.LeetCode653;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-09-04 11:51
 * @Description https://leetcode.cn/problems/two-sum-iv-input-is-a-bst
 * 653. 两数之和 IV - 输入二叉搜索树
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 * 二叉树的节点个数的范围是  [1, 10^4].
 * -10^4 <= Node.val <= 10^4
 * 题目数据保证，输入的 root 是一棵 有效 的二叉搜索树
 * -10^5 <= k <= 10^5
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

    private Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

}
