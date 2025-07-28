package com.sean.leetcode.LeetCode114;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-06-09 09:52
 * @Description https://leetcode.cn/problems/flatten-binary-tree-to-linked-list
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
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

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        process(root, list);
        int size = list.size();
        TreeNode pre = list.get(0);
        pre.left = null;
        for (int i = 1; i < size; i++) {
            TreeNode cur = list.get(i);
            cur.left = null;
            pre.right = cur;
            pre = cur;
        }
    }

    private void process(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        process(root.left, list);
        process(root.right, list);
    }

    public void flatten1(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {//左子树节点不为空
                TreeNode next = cur.left;
                TreeNode pre = next;
                while (pre.right != null) {//找到左子树中的最右节点，作为前驱节点
                    pre = pre.right;
                }
                //当前节点的右节点挂在前驱节点下面
                pre.right = cur.right;
                cur.left = null;
                cur.right = next;
            }
            cur = cur.right;
        }
    }

}
