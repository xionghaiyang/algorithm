package com.sean.leetcode.LeetCode173;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-12 15:42
 * @Description: TODO
 */
public class BSTIterator1 {

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

    private TreeNode cur;
    private Deque<TreeNode> stack;

    public BSTIterator1(TreeNode root) {
        cur = root;
        stack = new LinkedList<>();
    }

    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int res = cur.val;
        cur = cur.right;
        return res;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }

}
