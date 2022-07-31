package com.sean.nowcoder;

import java.util.ArrayList;

public class NowCoderBM38 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    public static boolean flag = false;

    public static void dfs(TreeNode root, ArrayList<Integer> path, int target) {
        if (flag || root == null) {
            return;
        }
        path.add(root.val);
        if (root.val == target) {
            flag = true;
            return;
        }
        dfs(root.left, path, target);
        dfs(root.right, path, target);
        if (flag) {
            return;
        }
        path.remove(path.size() - 1);
    }

    public static int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        ArrayList<Integer> path_o1 = new ArrayList<>();
        ArrayList<Integer> path_o2 = new ArrayList<>();
        dfs(root, path_o1, o1);
        flag = false;
        dfs(root, path_o2, o2);
        int ans = 0;
        for (int i = 0; i < path_o1.size() && i < path_o2.size(); i++) {
            int x = path_o1.get(i);
            int y = path_o2.get(i);
            if (x == y) {
                ans = x;
            } else {
                break;
            }
        }
        return ans;
    }

}
