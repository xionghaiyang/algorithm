package com.sean.leetcode.LeetCodeOffer26;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-28 19:54
 * @Description: https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 26. 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
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

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (process(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    private boolean process(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }
        return process(A.left, B.left) && process(A.right, B.right);
    }

}
