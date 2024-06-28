package com.sean.leetcode.LeetCode834;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-05 14:44
 * @Description: https://leetcode.cn/problems/sum-of-distances-in-tree/description/
 * 834. 树中距离之和
 * 给定一个无向、连通的树。
 * 树中有 n 个标记为 0...n-1 的节点以及 n-1 条边 。
 * 给定整数 n 和数组 edges ， edges[i] = [ai, bi]表示树中的节点 ai 和 bi 之间有一条边。
 * 返回长度为 n 的数组 answer ，其中 answer[i] 是树中第 i 个节点与所有其他节点之间的距离之和。
 */
public class Solution {

    //邻接表
    private List<List<Integer>> graph = new ArrayList<>();
    //距离和
    private int[] distSum;
    //子树节点个数（包括自己）
    private int[] nodeNum;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        distSum = new int[N];
        nodeNum = new int[N];
        Arrays.fill(nodeNum, 1);
        postOrder(0, -1);
        preOrder(0, -1);
        return distSum;
    }

    //求root到子树所有节点的距离和
    private void postOrder(int root, int parent) {
        List<Integer> neighbors = graph.get(root);
        for (int neighbor : neighbors) {
            if (neighbor == parent) {
                //如果临界点是父节点，则跳过
                continue;
            }
            postOrder(neighbor, root);
            nodeNum[root] += nodeNum[neighbor];
            distSum[root] += distSum[neighbor] + nodeNum[neighbor];
        }
    }

    //根据root计算其邻居所在子树之外的节点的距离和(包括root节点)
    private void preOrder(int root, int parent) {
        List<Integer> neighbors = graph.get(root);
        for (int neighbor : neighbors) {
            if (neighbor == parent) {
                continue;
            }
            distSum[neighbor] = distSum[root] - nodeNum[neighbor] + (graph.size() - nodeNum[neighbor]);
            preOrder(neighbor, root);
        }
    }

}
