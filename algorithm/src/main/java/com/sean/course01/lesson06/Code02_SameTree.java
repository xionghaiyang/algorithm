package com.sean.course01.lesson06;

/**
 * @Author xionghaiyang
 * @Date 2025-03-24 20:14
 * @Description https://leetcode.cn/problems/same-tree
 * 判断两颗树是否结构相同
 */
public class Code02_SameTree {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
