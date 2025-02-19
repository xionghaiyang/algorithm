package com.sean.leetcode.LeetCode624;

import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-02-19 08:50
 * @Description https://leetcode.cn/problems/maximum-distance-in-arrays/
 * 624. 数组列表中的最大距离
 * 给定 m 个数组，每个数组都已经按照升序排好序了。
 * 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。
 * 两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。
 * 返回最大距离。
 * m == arrays.length
 * 2 <= m <= 10^5
 * 1 <= arrays[i].length <= 500
 * -10^4 <= arrays[i][j] <= 10^4
 * arrays[i] 以 升序 排序。
 * 所有数组中最多有 10^5 个整数。
 */
public class Solution {

    public int maxDistance(List<List<Integer>> arrays) {
        int m = arrays.size();
        int res = 0;
        int min = arrays.get(0).get(0);
        int max = arrays.get(0).get(arrays.get(0).size() - 1);
        for (int i = 1; i < m; i++) {
            res = Math.max(res, Math.max(Math.abs(max - arrays.get(i).get(0)), Math.abs(arrays.get(i).get(arrays.get(i).size() - 1) - min)));
            min = Math.min(min, arrays.get(i).get(0));
            max = Math.max(max, arrays.get(i).get(arrays.get(i).size() - 1));
        }
        return res;
    }

}
