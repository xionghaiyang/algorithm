package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode113 {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayList<Integer> path = new ArrayList<>();
        process(root, path, 0, targetSum, ans);
        return ans;
    }

    public static void process(TreeNode x, List<Integer> path, int preSum, int sum, List<List<Integer>> ans) {
        if (x.left == null && x.right == null) {
            if (preSum + x.val == sum) {
                path.add(x.val);
                ans.add(copy(path));
                path.remove(path.size() - 1);
            }
            return;
        }
        path.add(x.val);
        preSum += x.val;
        if (x.left != null) {
            process(x.left, path, preSum, sum, ans);
        }
        if (x.right != null) {
            process(x.right, path, preSum, sum, ans);
        }
        path.remove(path.size() - 1);
    }

    public static List<Integer> copy(List<Integer> path) {
        List<Integer> ans = new ArrayList<>();
        for (Integer num : path) {
            ans.add(num);
        }
        return ans;
    }

}
