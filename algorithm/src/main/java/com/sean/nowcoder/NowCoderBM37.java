package com.sean.nowcoder;

import java.util.ArrayList;

public class NowCoderBM37 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static ArrayList<Integer> getPath(TreeNode root, int target) {
        ArrayList<Integer> path = new ArrayList<>();
        TreeNode cur = root;
        while (cur.val != target) {
            path.add(cur.val);
            if (cur.val < target) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        path.add(cur.val);
        return path;
    }

    public static int lowestCommonAncestor(TreeNode root, int p, int q) {
        ArrayList<Integer> path_p = getPath(root, p);
        ArrayList<Integer> path_q = getPath(root, q);
        int ans = 0;
        for (int i = 0; i < path_p.size() && i < path_q.size(); i++) {
            int x = path_p.get(i);
            int y = path_q.get(i);
            if(x == y){
                ans = x;
            }else {
                break;
            }
        }
        return ans;
    }

}
