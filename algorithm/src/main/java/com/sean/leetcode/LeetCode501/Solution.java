package com.sean.leetcode.LeetCode501;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-09-06 14:26
 * @Description https://leetcode.cn/problems/find-mode-in-binary-search-tree
 * 501. 二叉搜索树中的众数
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 * 假定 BST 满足如下定义：
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 * 树中节点的数目在范围 [1, 10^4] 内
 * -10^5 <= Node.val <= 10^5
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

    private List<Integer> list = new ArrayList<>();
    private int num;
    private int cnt;
    private int maxCnt;

    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        update(node.val);
        dfs(node.right);
    }

    private void update(int x) {
        if (x == num) {
            cnt++;
        } else {
            cnt = 1;
            num = x;
        }
        if (cnt == maxCnt) {
            list.add(num);
        }
        if (cnt > maxCnt) {
            maxCnt = cnt;
            list.clear();
            list.add(num);
        }
    }

}
