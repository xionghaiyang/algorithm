package com.sean.leetcode.LeetCode2196;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-07 07:06
 * @Description: https://leetcode.cn/problems/create-binary-tree-from-descriptions
 * 2196. 根据描述创建二叉树
 * 给你一个二维整数数组 descriptions ，其中 descriptions[i] = [parenti, childi, isLefti] 表示 parenti 是 childi 在 二叉树 中的 父节点，二叉树中各节点的值 互不相同 。
 * 此外：
 * 如果 isLefti == 1 ，那么 childi 就是 parenti 的左子节点。
 * 如果 isLefti == 0 ，那么 childi 就是 parenti 的右子节点。
 * 请你根据 descriptions 的描述来构造二叉树并返回其 根节点 。
 * 测试用例会保证可以构造出 有效 的二叉树。
 * 1 <= descriptions.length <= 10^4
 * descriptions[i].length == 3
 * 1 <= parenti, childi <= 10^5
 * 0 <= isLefti <= 1
 * descriptions 所描述的二叉树是一棵有效二叉树
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

    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for (int[] description : descriptions) {
            int parent = description[0], child = description[1], isLeft = description[2];
            if (!map.containsKey(child)) {
                map.put(child, new TreeNode(child));
            }
            if (!map.containsKey(parent)) {
                map.put(parent, new TreeNode(parent));
            }
            if (isLeft == 1) {
                map.get(parent).left = map.get(child);
            } else {
                map.get(parent).right = map.get(child);
            }
            set.add(child);
        }
        for (int key : map.keySet()) {
            if (!set.contains(key)) {
                return map.get(key);
            }
        }
        return null;
    }

}
