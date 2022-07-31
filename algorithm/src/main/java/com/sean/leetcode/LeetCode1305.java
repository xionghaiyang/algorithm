package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode1305 {

    public static class TreeNode {
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

    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        inorder(root1, list1);
        inorder(root2, list2);
        List<Integer> res = new ArrayList<>();
        int index1 = 0, index2 = 0;
        while (index1 < list1.size() && index2 < list2.size()) {
            if (list1.get(index1) < list2.get(index2)) {
                res.add(list1.get(index1++));
            } else {
                res.add(list2.get(index2++));
            }
        }
        while (index1 < list1.size()) {
            res.add(list1.get(index1++));
        }
        while (index2 < list2.size()) {
            res.add(list2.get(index2++));
        }
        return res;
    }

    public static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

}
