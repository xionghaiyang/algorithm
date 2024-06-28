package com.sean.leetcode.LeetCode654;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-20 19:30
 * @Description: https://leetcode.cn/problems/maximum-binary-tree/
 * 654. 最大二叉树
 * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
 * 创建一个根节点，其值为 nums 中的最大值。
 * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
 * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
 * 返回 nums 构建的 最大二叉树 。
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

    public TreeNode constructMaximumBinaryTree1(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    private TreeNode process(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            return new TreeNode(nums[left]);
        }
        int val = nums[left];
        int mid = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > val) {
                val = nums[i];
                mid = i;
            }
        }
        TreeNode leftNode = process(nums, left, mid - 1);
        TreeNode rightNode = process(nums, mid + 1, right);
        return new TreeNode(val, leftNode, rightNode);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        TreeNode[] treeNodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            treeNodes[i] = new TreeNode(nums[i]);
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                //nums[stack.pop]右边第一个比他大的节点是nums[i]
                right[stack.pop()] = i;
            }
            if (!stack.isEmpty()) {
                //nums[i]左边第一个比他大的节点是stack.peek()
                left[i] = stack.peek();
            }
            stack.push(i);
        }
        //一个节点的父节点不是左边最近的比它大的节点就是右边最近比他大的节点
        //并且就是左右大于它的值里面更接近（较小）的那个
        TreeNode res = null;
        for (int i = 0; i < n; i++) {
            if (left[i] == -1 && right[i] == -1) {
                res = treeNodes[i];
            } else if (right[i] == -1 || (left[i] != -1 && nums[left[i]] < nums[right[i]])) { //
                treeNodes[left[i]].right = treeNodes[i];
            } else {
                treeNodes[right[i]].left = treeNodes[i];
            }
        }
        return res;
    }

}
