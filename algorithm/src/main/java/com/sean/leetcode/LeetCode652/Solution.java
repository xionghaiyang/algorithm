package com.sean.leetcode.LeetCode652;

import javafx.util.Pair;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-05 08:25
 * @Description: https://leetcode.cn/problems/find-duplicate-subtrees/
 * 652. 寻找重复的子树
 * 给定一棵二叉树 root，返回所有重复的子树。
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
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

    Map<String, TreeNode> map1 = new HashMap<>();
    Set<TreeNode> set1 = new HashSet<>();

    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        dfs1(root);
        return new ArrayList<>(set1);
    }

    private String dfs1(TreeNode node) {
        if (node == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        sb.append("(");
        sb.append(dfs1(node.left));
        sb.append(")(");
        sb.append(dfs1(node.right));
        sb.append(")");
        String res = sb.toString();
        if (map1.containsKey(res)) {
            set1.add(map1.get(res));
        } else {
            map1.put(res, node);
        }
        return res;
    }

    Map<String, Pair<TreeNode, Integer>> map = new HashMap<>();
    Set<TreeNode> set = new HashSet<>();
    int index = 0;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return new ArrayList<>(set);
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int[] tri = {node.val, dfs(node.left), dfs(node.right)};
        String cur = Arrays.toString(tri);
        if (map.containsKey(cur)) {
            Pair<TreeNode, Integer> pair = map.get(cur);
            set.add(pair.getKey());
            return pair.getValue();
        } else {
            map.put(cur, new Pair<>(node, ++index));
            return index;
        }
    }

}
