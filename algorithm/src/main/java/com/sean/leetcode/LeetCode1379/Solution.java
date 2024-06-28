package com.sean.leetcode.LeetCode1379;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-04 19:23
 * @Description: https://leetcode.cn/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/?envType=daily-question&envId=2024-04-03
 * 1379. 找出克隆二叉树中的相同节点
 * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
 * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
 * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
 * 注意：你 不能 对两棵二叉树，以及 target 节点进行更改。只能 返回对克隆树 cloned 中已有的节点的引用。
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) {
            return left;
        }
        return getTargetCopy(original.right, cloned.right, target);
    }

    public final TreeNode getTargetCopy1(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        queue1.offer(original);
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue2.offer(cloned);
        while (!queue1.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1 == target) {
                return node2;
            }
            if (node1.left != null) {
                queue1.offer(node1.left);
                queue2.offer(node2.left);
            }
            if (node1.right != null) {
                queue1.offer(node1.right);
                queue2.offer(node2.right);
            }
        }
        return null;
    }

}
