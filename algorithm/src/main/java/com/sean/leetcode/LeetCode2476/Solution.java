package com.sean.leetcode.LeetCode2476;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-24 12:02
 * @Description: https://leetcode.cn/problems/closest-nodes-queries-in-a-binary-search-tree/
 * 2476. 二叉搜索树最近节点查询
 * 给你一个 二叉搜索树 的根节点 root ，和一个由正整数组成、长度为 n 的数组 queries 。
 * 请你找出一个长度为 n 的 二维 答案数组 answer ，其中 answer[i] = [mini, maxi] ：
 * mini 是树中小于等于 queries[i] 的 最大值 。如果不存在这样的值，则使用 -1 代替。
 * maxi 是树中大于等于 queries[i] 的 最小值 。如果不存在这样的值，则使用 -1 代替。
 * 返回数组 answer 。
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

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> arr = new ArrayList<>();
        dfs(root, arr);
        List<List<Integer>> res = new ArrayList<>();
        for (int val : queries) {
            int maxVal = -1, minVal = -1;
            int index = binarySearch(arr, val);
            if (index != arr.size()) {
                maxVal = arr.get(index);
                if (arr.get(index) == val) {
                    minVal = val;
                    List<Integer> list = new ArrayList<>();
                    list.add(minVal);
                    list.add(maxVal);
                    res.add(list);
                    continue;
                }
            }
            if (index > 0) {
                minVal = arr.get(index - 1);
            }
            List<Integer> list = new ArrayList<>();
            list.add(minVal);
            list.add(maxVal);
            res.add(list);
        }
        return res;
    }

    private void dfs(TreeNode root, List<Integer> arr) {
        if (root == null) {
            return;
        }
        dfs(root.left, arr);
        arr.add(root.val);
        dfs(root.right, arr);
    }

    private int binarySearch(List<Integer> arr, int target) {
        int low = 0, high = arr.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}
