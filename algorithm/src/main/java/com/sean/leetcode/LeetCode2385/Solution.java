package com.sean.leetcode.LeetCode2385;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-24 19:55
 * @Description: https://leetcode.cn/problems/amount-of-time-for-binary-tree-to-be-infected/
 * 2385. 感染二叉树需要的总时间
 * 给你一棵二叉树的根节点 root ，二叉树中节点的值 互不相同 。
 * 另给你一个整数 start 。
 * 在第 0 分钟，感染 将会从值为 start 的节点开始爆发。
 * 每分钟，如果节点满足以下全部条件，就会被感染：
 * 节点此前还没有感染。
 * 节点与一个已感染节点相邻。
 * 返回感染整棵树需要的分钟数。
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


    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        dfs(graph, root);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        int time = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int nodeVal = arr[0];
            time = arr[1];
            for (int childVal : graph.get(nodeVal)) {
                if (visited.add(childVal)) {
                    queue.offer(new int[]{childVal, time + 1});
                }
            }
        }
        return time;
    }

    private void dfs(Map<Integer, List<Integer>> graph, TreeNode node) {
        graph.putIfAbsent(node.val, new ArrayList<>());
        for (TreeNode child : Arrays.asList(node.left, node.right)) {
            if (child != null) {
                graph.get(node.val).add(child.val);
                graph.putIfAbsent(child.val, new ArrayList<>());
                graph.get(child.val).add(node.val);
                dfs(graph, child);
            }
        }
    }

}
