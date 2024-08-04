package com.sean.leetcode.LeetCode3143;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-08-03 10:25
 * @Description https://leetcode.cn/problems/maximum-points-inside-the-square/description/
 * 3143. 正方形中的最多点数
 * 给你一个二维数组 points 和一个字符串 s ，其中 points[i] 表示第 i 个点的坐标，s[i] 表示第 i 个点的 标签 。
 * 如果一个正方形的中心在 (0, 0) ，所有边都平行于坐标轴，且正方形内 不 存在标签相同的两个点，那么我们称这个正方形是 合法 的。
 * 请你返回 合法 正方形中可以包含的 最多 点数。
 * 注意：
 * 如果一个点位于正方形的边上或者在边以内，则认为该点位于正方形内。
 * 正方形的边长可以为零。
 * 1 <= s.length, points.length <= 10^5
 * points[i].length == 2
 * -10^9 <= points[i][0], points[i][1] <= 10^9
 * s.length == points.length
 * points 中的点坐标互不相同。
 * s 只包含小写英文字母。
 */
public class Solution {

    public int maxPointsInsideSquare(int[][] points, String s) {
        int[] tagDis = new int[26];
        Arrays.fill(tagDis, Integer.MAX_VALUE);
        int n = points.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            int j = s.charAt(i) - 'a';
            int dis = Math.max(Math.abs(x), Math.abs(y));
            if (dis < tagDis[j]) {
                min = Math.min(min, tagDis[j]);
                tagDis[j] = dis;
            } else if (dis < min) {
                min = dis;
            }
        }
        int res = 0;
        for (int dis : tagDis) {
            if (dis < min) {
                res++;
            }
        }
        return res;
    }

}
