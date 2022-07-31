package com.sean.nowcoder;

import java.util.ArrayList;
import java.util.List;

public class NowCoderBM24 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public static int[] inorderTraversal(TreeNode root) {
        List<Integer> arrayList = new ArrayList<>();
        inorder(root, arrayList);
        int[] ans = new int[arrayList.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = arrayList.get(i);
        }
        return ans;
    }

}
