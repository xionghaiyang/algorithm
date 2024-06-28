package com.sean.leetcode.LeetCode1110;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 08:16
 * @Description: https://leetcode.cn/problems/delete-nodes-and-return-forest/
 * 1110. 删点成林
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
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

    List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for (int num : to_delete) {
            set.add(num);
        }
        if (root != null) {
            process(root, set, true);
        }
        return res;
    }

    private TreeNode process(TreeNode root, Set<Integer> set, boolean is_root) {
        boolean deleted = set.contains(root.val);
        if (root.left != null) {
            root.left = process(root.left, set, deleted);
        }
        if (root.right != null) {
            root.right = process(root.right, set, deleted);
        }
        if (deleted) {//当前节点被删
            return null;
        } else {
            if (is_root) {
                res.add(root);
            }
            return root;
        }
    }

}
