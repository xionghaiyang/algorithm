package com.sean.nowcoder;

import java.util.*;

public class NowCoderBM41 {

    public static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode buildTree(int[] xianxu, int[] zhongxu, int l1, int r1, int l2, int r2, Map<Integer, Integer> map) {
        if (l1 > r1 || l2 > r2) {
            return null;
        }
        TreeNode root = new TreeNode(xianxu[l1]);
        int rootIndex = map.get(xianxu[l1]);
        int leftSize = rootIndex - l2;
        root.left = buildTree(xianxu, zhongxu, l1 + 1, l1 + leftSize, l2, rootIndex - 1, map);
        root.right = buildTree(xianxu, zhongxu, l1 + leftSize + 1, r1, rootIndex + 1, r2, map);
        return root;
    }

    public static ArrayList<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                TreeNode node = queue.poll();
                if (i == size) {
                    ans.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return ans;
    }

    public static int[] solve(int[] xianxu, int[] zhongxu) {
        if (xianxu.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < zhongxu.length; i++) {
            map.put(zhongxu[i], i);
        }
        TreeNode root = buildTree(xianxu, zhongxu, 0, xianxu.length - 1, 0, zhongxu.length - 1, map);
        ArrayList<Integer> list = rightSideView(root);
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] xianxu = {1, 2, 4, 5, 3};
        int[] zhongxu = {4, 2, 5, 1, 3};
        int[] ans = solve(xianxu, zhongxu);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        ArrayList<Integer> integers = rightSideView(root);
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }
    }

}
