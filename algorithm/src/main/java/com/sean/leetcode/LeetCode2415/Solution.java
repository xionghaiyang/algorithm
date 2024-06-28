package com.sean.leetcode.LeetCode2415;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-15 11:51
 * @Description: https://leetcode.cn/problems/reverse-odd-levels-of-binary-tree/
 * 2415. 反转二叉树的奇数层
 * 给你一棵 完美 二叉树的根节点 root ，请你反转这棵树中每个 奇数 层的节点值。
 * 例如，假设第 3 层的节点值是 [2,1,3,4,7,11,29,18] ，那么反转后它应该变成 [18,29,11,7,4,3,1,2] 。
 * 反转后，返回树的根节点。
 * 完美 二叉树需满足：二叉树的所有父节点都有两个子节点，且所有叶子节点都在同一层。
 * 节点的 层数 等于该节点到根节点之间的边数。
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

    public TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isOdd = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> arr = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (isOdd) {
                    arr.add(node);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            if (isOdd) {
                for (int left = 0, right = size - 1; left < right; left++, right--) {
                    int temp = arr.get(left).val;
                    arr.get(left).val = arr.get(right).val;
                    arr.get(right).val = temp;
                }
            }
            isOdd ^= true;
        }
        return root;
    }

    public TreeNode reverseOddLevels1(TreeNode root) {
        dfs(root.left, root.right, true);
        return root;
    }

    private void dfs(TreeNode root1, TreeNode root2, boolean isOdd) {
        if (root1 == null) {
            return;
        }
        if (isOdd) {
            int temp = root1.val;
            root1.val = root2.val;
            root2.val = temp;
        }
        dfs(root1.left, root2.right, !isOdd);
        dfs(root1.right, root2.left, !isOdd);
    }

}
