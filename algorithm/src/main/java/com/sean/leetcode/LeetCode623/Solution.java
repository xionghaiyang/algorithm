package com.sean.leetcode.LeetCode623;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-05 11:30
 * @Description: https://leetcode.cn/problems/add-one-row-to-tree/
 * 623 在二叉树中增加一行
 * 给定一个二叉树的根 root 和两个整数 val 和 depth ，在给定的深度 depth 处添加一个值为 val 的节点行。
 * 注意，根节点 root 位于深度 1 。
 * 加法规则如下:
 * 给定整数 depth，对于深度为 depth - 1 的每个非空树节点 cur ，创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
 * cur 原来的左子树应该是新的左子树根的左子树。
 * cur 原来的右子树应该是新的右子树根的右子树。
 * 如果 depth == 1 意味着 depth - 1 根本没有深度，那么创建一个树节点，值 val 作为整个原始树的新根，而原始树就是新根的左子树。
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

    public TreeNode addOneRow1(TreeNode root, int val, int depth) {
        TreeNode res = new TreeNode(-1, root, null);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(res);
        for (int i = 0; i < depth - 1; i++) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode left = cur.left;
            TreeNode right = cur.right;
            cur.left = new TreeNode(val, left, null);
            cur.right = new TreeNode(val, null, right);
        }
        return res.left;
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        TreeNode res = new TreeNode(-1, root, null);
        process(0, depth, val, res);
        return res.left;
    }

    public void process(int curDepth, int depth, int val, TreeNode root) {
        if (root == null) {
            return;
        }
        if (curDepth == depth - 1) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = new TreeNode(val, left, null);
            root.right = new TreeNode(val, null, right);
            return;
        }
        process(curDepth + 1, depth, val, root.left);
        process(curDepth + 1, depth, val, root.right);
    }

}
