package com.sean.course01.lesson07;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-03-25 20:13
 * @Description https://leetcode.cn/problems/binary-tree-level-order-traversal-ii
 * 二叉树按层遍历并收集节点
 */
public class Code01_BinaryTreeLevelOrderTraversalII {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            //res.add(0,list);
            res.add(list);
        }
        Collections.reverse(res);
        return res;
    }

}
