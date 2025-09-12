package com.sean.leetcode.LeetCode968;

/**
 * @Author xionghaiyang
 * @Date 2025-09-12 17:05
 * @Description https://leetcode.cn/problems/binary-tree-cameras
 * 968. 监控二叉树
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 * 给定树的节点数的范围是 [1, 1000]。
 * 每个节点的值都是 0。
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

    private int res = 0;

    public int minCameraCover(TreeNode root) {
        if (dfs(root) == 0) {
            res++;
        }
        return res;
    }

    //返回0，该节点无覆盖
    //返回1，该节点有摄像头
    //返回2，该节点有覆盖
    private int dfs(TreeNode node) {
        //空节点有覆盖
        if (node == null) {
            return 2;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        //左右节点至少有一个无覆盖
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        //左右节点至少有一个摄像头
        if (left == 1 || right == 1) {
            return 2;
        }
        //左右节点都有覆盖，left == 2 && right == 2
        return 0;
    }

    public int minCameraCover1(TreeNode root) {
        //0:当前节点装摄像头,1:父节点装摄像头,2:子节点装摄像头
        int[] res = process(root);
        return Math.min(res[0], res[2]);
    }

    private int[] process(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] left = process(node.left);
        int[] right = process(node.right);
        int choose = Math.min(left[0], left[1]) + Math.min(right[0], right[1]) + 1;
        int byFa = Math.min(left[0], left[2]) + Math.min(right[0], right[2]);
        int byChildren = Math.min(Math.min(left[0] + right[2], left[2] + right[0]), left[0] + right[0]);
        return new int[]{choose, byFa, byChildren};
    }

}
