package com.sean.leetcode.LeetCode3102;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-07-09 07:59
 * @Description https://leetcode.cn/problems/minimize-manhattan-distances/
 * 3102. 最小化曼哈顿距离
 * 给你一个下标从 0 开始的数组 points ，它表示二维平面上一些点的整数坐标，其中 points[i] = [xi, yi] 。
 * 两点之间的距离定义为它们的曼哈顿距离 。
 * 请你恰好移除一个点，返回移除后任意两点之间的 最大 距离可能的 最小 值。
 * 3 <= points.length <= 10^5
 * points[i].length == 2
 * 1 <= points[i][0], points[i][1] <= 10^8
 */
public class Solution {

    public int minimumDistance(int[][] points) {
        int n = points.length;
        List<int[]> sx = new ArrayList<>();
        List<int[]> sy = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            sx.add(new int[]{x - y, i});
            sy.add(new int[]{x + y, i});
        }
        Collections.sort(sx, (a, b) -> a[0] - b[0]);
        Collections.sort(sy, (a, b) -> a[0] - b[0]);
        int maxVal1 = sx.get(sx.size() - 1)[0] - sx.get(0)[0];
        int maxVal2 = sy.get(sy.size() - 1)[0] - sy.get(0)[0];
        int res = Integer.MAX_VALUE;
        if (maxVal1 >= maxVal2) {
            int i = sx.get(0)[1];
            int j = sx.get(sx.size() - 1)[1];
            //去掉i后的最大曼哈顿距离
            res = Math.min(res, Math.max(remove(sx, i), remove(sy, i)));
            //去掉j后的最大曼哈顿距离
            res = Math.min(res, Math.max(remove(sx, j), remove(sy, j)));
        } else {
            int i = sy.get(0)[1];
            int j = sy.get(sy.size() - 1)[1];
            //去掉i后的最大曼哈顿距离
            res = Math.min(res, Math.max(remove(sx, i), remove(sy, i)));
            //去掉j后的最大曼哈顿距离
            res = Math.min(res, Math.max(remove(sx, j), remove(sy, j)));
        }
        return res;
    }

    private int remove(List<int[]> list, int i) {
        int size = list.size();
        if (list.get(0)[1] == i) {
            return list.get(size - 1)[0] - list.get(1)[0];
        } else if (list.get(size - 1)[1] == i) {
            return list.get(size - 2)[0] - list.get(0)[0];
        } else {
            return list.get(size - 1)[0] - list.get(0)[0];
        }
    }

}
