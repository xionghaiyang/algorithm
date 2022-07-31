package com.sean.leetcode;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/7/27 16:27
 */
public class LeetCode114 {

    /**
     * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     */

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

    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        process(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode pre = list.get(i - 1);
            TreeNode cur = list.get(i);
            pre.left = null;
            pre.right = cur;
        }
    }

    private void process(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            process(root.left, list);
            process(root.right, list);
        }
    }

}
