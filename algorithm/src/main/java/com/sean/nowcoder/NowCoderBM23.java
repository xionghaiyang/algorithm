package com.sean.nowcoder;

import java.util.ArrayList;
import java.util.List;

public class NowCoderBM23 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    public static int[] preorderTraversal(TreeNode root) {
        List<Integer> arrayList = new ArrayList<>();
        preorder(root, arrayList);
        int[] ans = new int[arrayList.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = arrayList.get(i);
        }
        return ans;
    }

}
