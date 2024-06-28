package com.sean.nowcoder;

import java.util.LinkedList;
import java.util.Queue;

public class NowCoderBM35 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode cur = null;
        boolean flag = false;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur == null) {
                flag = true;
                continue;
            }
            if (flag) {
                return false;
            }
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return true;
    }

}
