package com.sean.nowcoder;

import java.util.ArrayList;
import java.util.List;

public class NowCoderBM25 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }

    public static int[] postorderTraversal(TreeNode root) {
        List<Integer> arrayList = new ArrayList<>();
        postorder(root, arrayList);
        int[] ans = new int[arrayList.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = arrayList.get(i);
        }
        return ans;
    }

}
