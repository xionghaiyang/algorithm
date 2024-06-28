package com.sean.leetcode.LeetCode1632;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-29 11:05
 * @Description: https://leetcode.cn/problems/rank-transform-of-a-matrix/
 * 1632. 矩阵转换后的秩
 * 给你一个 m x n 的矩阵 matrix ，请你返回一个新的矩阵 answer ，其中 answer[row][col] 是 matrix[row][col] 的秩。
 * 每个元素的 秩 是一个整数，表示这个元素相对于其他元素的大小关系，它按照如下规则计算：
 * 秩是从 1 开始的一个整数。
 * 如果两个元素 p 和 q 在 同一行 或者 同一列 ，那么：
 * 如果 p < q ，那么 rank(p) < rank(q)
 * 如果 p == q ，那么 rank(p) == rank(q)
 * 如果 p > q ，那么 rank(p) > rank(q)
 * 秩 需要越 小 越好。
 * 题目保证按照上面规则 answer 数组是唯一的。
 */
public class Solution {

    class UnionFind {
        int m, n;
        int[][][] root;
        int[][] size;

        public UnionFind(int m, int n) {
            this.m = m;
            this.n = n;
            this.root = new int[m][n][2];
            this.size = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    root[i][j][0] = i;
                    root[i][j][1] = j;
                    size[i][j] = 1;
                }
            }
        }

        private int[] find(int i, int j) {
            int[] rootArr = root[i][j];
            int ri = rootArr[0];
            int rj = rootArr[1];
            if (ri == i && rj == j) {
                return rootArr;
            }
            return find(ri, rj);
        }

        public void union(int i1, int j1, int i2, int j2) {
            int[] rootArr1 = find(i1, j1);
            int[] rootArr2 = find(i2, j2);
            int ri1 = rootArr1[0];
            int rj1 = rootArr1[1];
            int ri2 = rootArr2[0];
            int rj2 = rootArr2[1];
            if (ri1 != ri2 || rj1 != rj2) {
                if (size[ri1][rj1] >= size[ri2][rj2]) {
                    root[ri2][rj2][0] = ri1;
                    root[ri2][rj2][1] = rj1;
                    size[ri1][rj1] += size[ri2][rj2];
                } else {
                    root[ri1][rj1][0] = ri2;
                    root[ri1][rj1][1] = rj2;
                    size[ri2][rj2] += size[ri1][rj1];
                }
            }
        }
    }

    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        UnionFind unionFind = new UnionFind(m, n);
        for (int i = 0; i < m; i++) {
            Map<Integer, List<int[]>> num2indexList = new HashMap<>();
            for (int j = 0; j < n; j++) {
                int num = matrix[i][j];
                num2indexList.putIfAbsent(num, new ArrayList<>());
                num2indexList.get(num).add(new int[]{i, j});
            }
            for (List<int[]> indexList : num2indexList.values()) {
                int[] arr1 = indexList.get(0);
                int i1 = arr1[0];
                int j1 = arr1[1];
                for (int k = 1; k < indexList.size(); k++) {
                    int[] arr2 = indexList.get(k);
                    int i2 = arr2[0];
                    int j2 = arr2[1];
                    unionFind.union(i1, j1, i2, j2);
                }
            }
        }
        for (int j = 0; j < n; j++) {
            Map<Integer, List<int[]>> num2indexList = new HashMap<>();
            for (int i = 0; i < m; i++) {
                int num = matrix[i][j];
                num2indexList.putIfAbsent(num, new ArrayList<>());
                num2indexList.get(num).add(new int[]{i, j});
            }
            for (List<int[]> indexList : num2indexList.values()) {
                int[] arr1 = indexList.get(0);
                int i1 = arr1[0];
                int j1 = arr1[1];
                for (int k = 1; k < indexList.size(); k++) {
                    int[] arr2 = indexList.get(k);
                    int i2 = arr2[0];
                    int j2 = arr2[1];
                    unionFind.union(i1, j1, i2, j2);
                }
            }
        }
        int[][] degree = new int[m][n];
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int i = 0; i < m; i++) {
            Map<Integer, int[]> num2index = new HashMap<>();
            for (int j = 0; j < n; j++) {
                int num = matrix[i][j];
                num2index.put(num, new int[]{i, j});
            }
            List<Integer> sortedArray = new ArrayList<>(num2index.keySet());
            Collections.sort(sortedArray);
            for (int k = 1; k < sortedArray.size(); k++) {
                int[] prev = num2index.get(sortedArray.get(k - 1));
                int[] curr = num2index.get(sortedArray.get(k));
                int i1 = prev[0];
                int j1 = prev[1];
                int i2 = curr[0];
                int j2 = curr[1];
                int[] root1 = unionFind.find(i1, j1);
                int[] root2 = unionFind.find(i2, j2);
                int ri1 = root1[0];
                int rj1 = root1[1];
                int ri2 = root2[0];
                int rj2 = root2[1];
                degree[ri2][rj2]++;
                adj.putIfAbsent(ri1 * n + rj1, new ArrayList<>());
                adj.get(ri1 * n + rj1).add(new int[]{ri2, rj2});
            }
        }
        for (int j = 0; j < n; j++) {
            Map<Integer, int[]> num2index = new HashMap<>();
            for (int i = 0; i < m; i++) {
                int num = matrix[i][j];
                num2index.put(num, new int[]{i, j});
            }
            List<Integer> sortedArray = new ArrayList<>(num2index.keySet());
            Collections.sort(sortedArray);
            for (int k = 1; k < sortedArray.size(); k++) {
                int[] prev = num2index.get(sortedArray.get(k - 1));
                int[] curr = num2index.get(sortedArray.get(k));
                int i1 = prev[0];
                int j1 = prev[1];
                int i2 = curr[0];
                int j2 = curr[1];
                int[] root1 = unionFind.find(i1, j1);
                int[] root2 = unionFind.find(i2, j2);
                int ri1 = root1[0];
                int rj1 = root1[1];
                int ri2 = root2[0];
                int rj2 = root2[1];
                degree[ri2][rj2]++;
                adj.putIfAbsent(ri1 * n + rj1, new ArrayList<>());
                adj.get(ri1 * n + rj1).add(new int[]{ri2, rj2});
            }
        }
        Set<Integer> rootSet = new HashSet<>();
        int[][] ranks = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int[] rootArr = unionFind.find(i, j);
                int ri = rootArr[0];
                int rj = rootArr[1];
                rootSet.add(ri * n + rj);
                ranks[ri][rj] = 1;
            }
        }
        Queue<int[]> queue = new ArrayDeque<>();
        for (int val : rootSet) {
            int i = val / n;
            int j = val % n;
            if (degree[i][j] == 0) {
                queue.offer(new int[]{i, j});
            }
        }
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int i = arr[0];
            int j = arr[1];
            for (int[] adjArr : adj.getOrDefault(i * n + j, new ArrayList<>())) {
                int ui = adjArr[0];
                int uj = adjArr[1];
                degree[ui][uj]--;
                if (degree[ui][uj] == 0) {
                    queue.offer(new int[]{ui, uj});
                }
                ranks[ui][uj] = Math.max(ranks[ui][uj], ranks[i][j] + 1);
            }
        }
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int[] rootArr = unionFind.find(i, j);
                int ri = rootArr[0];
                int rj = rootArr[1];
                res[i][j] = ranks[ri][rj];
            }
        }
        return res;
    }

}


