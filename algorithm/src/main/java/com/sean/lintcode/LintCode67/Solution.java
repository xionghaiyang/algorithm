package com.sean.lintcode.LintCode67;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-03 11:33
 * @Description: https://www.lintcode.com/problem/67/
 * 给出一棵二叉树,返回其中序遍历。
 */
public class Solution {

    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        process(root, res);
        return res;
    }

    private void process(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        process(root.left, list);
        list.add(root.val);
        process(root.right, list);
    }

}
