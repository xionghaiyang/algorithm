package com.sean.leetcode.LeetCodeInterview0406;

/**
 * @Author xionghaiyang
 * @Date 2026-03-10 18:21
 * @Description https://leetcode.cn/problems/successor-lcci
 * 面试题 04.06. 后继者
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        if (p.right != null) {
            res = p.right;
            while (res.left != null) {
                res = res.left;
            }
            return res;
        }
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > p.val) {
                res = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return res;
    }

}
