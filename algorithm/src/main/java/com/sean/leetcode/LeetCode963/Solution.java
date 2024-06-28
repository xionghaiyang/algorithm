package com.sean.leetcode.LeetCode963;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-22 12:21
 * @Description: https://leetcode.cn/problems/minimum-area-rectangle-ii/description/
 * 963. 最小面积矩形 II
 * 给定在 xy 平面上的一组点，确定由这些点组成的任何矩形的最小面积，其中矩形的边不一定平行于 x 轴和 y 轴。
 * 如果没有任何矩形，就返回 0。
 */
public class Solution {

    public double minAreaFreeRect(int[][] points) {
        int n = points.length;
        Point[] A = new Point[n];
        Set<Point> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            A[i] = new Point(points[i][0], points[i][1]);
            set.add(A[i]);
        }
        double res = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            Point p1 = A[i];
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    Point p2 = A[j];
                    for (int k = j + 1; k < n; k++) {
                        if (k != i) {
                            Point p3 = A[k];
                            Point p4 = new Point(p2.x + p3.x - p1.x, p2.y + p3.y - p1.y);
                            if (set.contains(p4)) {
                                int doc = (p2.x - p1.x) * (p3.x - p1.x) + (p2.y - p1.y) * (p3.y - p1.y);
                                if (doc == 0) {
                                    double area = p1.distance(p2) * p1.distance(p3);
                                    if (area < res) {
                                        res = area;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return res < Double.MAX_VALUE ? res : 0;
    }

    public double minAreaFreeRect1(int[][] points) {
        int n = points.length;
        Point[] A = new Point[n];
        for (int i = 0; i < n; i++) {
            A[i] = new Point(points[i][0], points[i][1]);
        }
        Map<Integer, Map<Point, List<Point>>> seen = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Point a = A[i];
            for (int j = i + 1; j < n; j++) {
                Point b = A[j];
                Point center = new Point(a.x + b.x, a.y + b.y);
                int r2 = (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
                if (!seen.containsKey(r2)) {
                    seen.put(r2, new HashMap<>());
                }
                if (!seen.get(r2).containsKey(center)) {
                    seen.get(r2).put(center, new ArrayList<>());
                }
                seen.get(r2).get(center).add(a);
            }
        }
        double res = Double.MAX_VALUE;
        for (Map<Point, List<Point>> info : seen.values()) {
            for (Point center : info.keySet()) {
                List<Point> candidates = info.get(center);
                int size = candidates.size();
                for (int i = 0; i < size; i++) {
                    for (int j = i + 1; j < size; j++) {
                        Point p = candidates.get(i);
                        Point q = candidates.get(j);
                        Point o = new Point(center);
                        o.translate(-q.x, -q.y);
                        double area = p.distance(q) * p.distance(o);
                        if (area < res) {
                            res = area;
                        }
                    }
                }
            }
        }
        return res < Double.MAX_VALUE ? res : 0;
    }

}
