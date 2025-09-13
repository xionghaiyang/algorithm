package com.sean.leetcode.LeetCode1042;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-17 08:21
 * @Description: https://leetcode.cn/problems/flower-planting-with-no-adjacent
 * 1042. 不邻接植花
 * 有 n 个花园，按从 1 到 n 标记。
 * 另有数组 paths ，其中 paths[i] = [xi, yi] 描述了花园 xi 到花园 yi 的双向路径。
 * 在每个花园中，你打算种下四种花之一。
 * 另外，所有花园 最多 有 3 条路径可以进入或离开.
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 * 以数组形式返回 任一 可行的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。
 * 花的种类用  1、2、3、4 表示。
 * 保证存在答案。
 * 1 <= n <= 10^4
 * 0 <= paths.length <= 2 * 10^4
 * paths[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * 每个花园 最多 有 3 条路径可以进入或离开
 */
public class Solution {

    public int[] gardenNoAdj(int n, int[][] paths) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] path : paths) {
            int u = path[0] - 1, v = path[1] - 1;
            g[u].add(v);
            g[v].add(u);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            boolean[] colored = new boolean[5];
            for (int j : g[i]) {
                colored[res[j]] = true;
            }
            while (colored[++res[i]]) ;
        }
        return res;
    }

}
