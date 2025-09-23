package com.sean.leetcode.LeetCode1382;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-09-23 17:36
 * @Description https://leetcode.cn/problems/balance-a-binary-search-tree
 * 1382. 将二叉搜索树变平衡
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
 * 如果有多种构造方法，请你返回任意一种。
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 * 树节点的数目在 [1, 10^4] 范围内。
 * 1 <= Node.val <= 10^5
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

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        in(root, list);
        return build(list, 0, list.size() - 1);
    }

    private void in(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        in(node.left, list);
        list.add(node.val);
        in(node.right, list);
    }

    private TreeNode build(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        TreeNode node = new TreeNode(list.get(mid));
        node.left = build(list, left, mid - 1);
        node.right = build(list, mid + 1, right);
        return node;
    }

}
