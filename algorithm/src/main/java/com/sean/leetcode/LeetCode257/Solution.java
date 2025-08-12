package com.sean.leetcode.LeetCode257;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-08-12 19:41
 * @Description https://leetcode.cn/problems/binary-tree-paths
 * 257. 二叉树的所有路径
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * 叶子节点 是指没有子节点的节点。
 * 树中节点的数目在范围 [1, 100] 内
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

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        process(root, res, new ArrayList<>());
        return res;
    }

    private void process(TreeNode root, List<String> res, List<Integer> paths) {
        paths.add(root.val);
        if (root.left == null && root.right == null) {
            StringBuilder path = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                path.append(paths.get(i)).append("->");
            }
            path.append(paths.get(paths.size() - 1));
            res.add(path.toString());
            return;
        }
        if (root.left != null) {
            process(root.left, res, paths);
            paths.remove(paths.size() - 1);
        }
        if (root.right != null) {
            process(root.right, res, paths);
            paths.remove(paths.size() - 1);
        }
    }

}
