package com.sean.leetcode.LeetCode3625;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-12-03 10:59
 * @Description https://leetcode.cn/problems/count-number-of-trapezoids-ii
 * 3625. 统计梯形的数目 II
 * 给你一个二维整数数组 points，其中 points[i] = [xi, yi] 表示第 i 个点在笛卡尔平面上的坐标。
 * 返回可以从 points 中任意选择四个不同点组成的梯形的数量。
 * 梯形 是一种凸四边形，具有 至少一对 平行边。
 * 两条直线平行当且仅当它们的斜率相同。
 * 4 <= points.length <= 500
 * –1000 <= xi, yi <= 1000
 * 所有点两两不同。
 */
public class Solution {

    public int countTrapezoids(int[][] points) {
        //斜率 -> [截距]
        Map<Double, List<Double>> groups = new HashMap<>();
        //中点 -> [斜率]
        Map<Integer, List<Double>> groups2 = new HashMap<>();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = 0; j < i; j++) {
                int x2 = points[j][0], y2 = points[j][1];
                int dy = y1 - y2, dx = x1 - x2;
                double k = dx != 0 ? 1.0 * dy / dx : Double.MAX_VALUE;
                if (k == -0.0) {
                    k = 0.0;
                }
                double b = dx != 0 ? 1.0 * (y1 * dx - x1 * dy) / dx : x1;
                groups.computeIfAbsent(k, e -> new ArrayList<>()).add(b);
                int mid = (x1 + x2 + 2000) * 10000 + (y1 + y2 + 2000);
                groups2.computeIfAbsent(mid, e -> new ArrayList<>()).add(k);
            }
        }
        int res = 0;
        Map<Double, Integer> cnt = new HashMap<>();
        for (List<Double> list : groups.values()) {
            if (list.size() == 1) {
                continue;
            }
            cnt.clear();
            for (Double b : list) {
                if (b == -0.0) {
                    b = 0.0;
                }
                cnt.compute(b, (k, v) -> v == null ? 1 : v + 1);
            }
            int s = 0;
            for (int c : cnt.values()) {
                res += s * c;
                s += c;
            }
        }
        for (List<Double> list : groups2.values()) {
            if (list.size() == 1) {
                continue;
            }
            cnt.clear();
            for (Double k : list) {
                cnt.compute(k, (key, value) -> value == null ? 1 : value + 1);
            }
            int s = 0;
            for (int c : cnt.values()) {
                res -= s * c;
                s += c;
            }
        }
        return res;
    }

}
