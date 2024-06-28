package com.sean.leetcode.LeetCode447;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-30 14:01
 * @Description: https://leetcode.cn/problems/number-of-boomerangs/description/
 * 447. 回旋镖的数量
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。
 * 回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的欧式距离相等（需要考虑元组的顺序）。
 * 返回平面上所有回旋镖的数量。
 */
public class Solution {

    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int[] p : points) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int[] q : points) {
                int d2 = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                map.put(d2, map.getOrDefault(d2, 0) + 1);
            }
            for (int value : map.values()) {
                res += value * (value - 1);
            }
        }
        return res;
    }

}
