package com.sean.nowcoder;

import java.util.Arrays;

public class NowCoderBM40 {

    public static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode reConstructBinaryTree(int[] pre, int[] vin) {
        if (pre == null || vin == null) {
            return null;
        }
        if (pre.length != vin.length) {
            return null;
        }
        if (pre.length == 0 || vin.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < vin.length; i++) {
            if (pre[0] == vin[i]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(vin, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(vin, i + 1, vin.length));
                break;
            }
        }
        return root;
    }



}
