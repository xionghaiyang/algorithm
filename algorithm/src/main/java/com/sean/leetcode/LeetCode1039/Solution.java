package com.sean.leetcode.LeetCode1039;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-03 20:36
 * @Description: https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/
 * 1039. 多边形三角剖分的最低得分
 * 你有一个凸的 n 边形，其每个顶点都有一个整数值。
 * 给定一个整数数组 values ，其中 values[i] 是第 i 个顶点的值（即 顺时针顺序 ）。
 * 假设将多边形 剖分 为 n - 2 个三角形。
 * 对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 n - 2 个三角形的值之和。
 * 返回 多边形进行三角剖分后可以得到的最低分 。
 */
public class Solution {

    int n;
    int[] values;
    Map<Integer, Integer> dp = new HashMap<>();

    public int minScoreTriangulation(int[] values) {
        this.n = values.length;
        this.values = values;
        return process(0, n - 1);
    }

    private int process(int i, int j) {
        if (i + 2 > j) {
            return 0;
        }
        if (i + 2 == j) {
            return values[i] * values[i + 1] * values[j];
        }
        int key = i * n + j;
        if (!dp.containsKey(key)) {
            int minScore = Integer.MAX_VALUE;
            for (int k = i + 1; k < j; k++) {
                minScore = Math.min(minScore, values[i] * values[k] * values[j] + process(i, k) + process(k, j));
            }
            dp.put(key, minScore);
        }
        return dp.get(key);
    }

}
