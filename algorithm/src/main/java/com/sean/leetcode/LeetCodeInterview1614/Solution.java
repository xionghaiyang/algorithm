package com.sean.leetcode.LeetCodeInterview1614;

/**
 * @Author xionghaiyang
 * @Date 2026-03-23 20:05
 * @Description https://leetcode.cn/problems/best-line-lcci
 * 面试题 16.14. 最佳直线
 * 给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。
 * 请找出一条直线，其通过的点的数目最多。
 * 设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，你仅需返回[S[0],S[1]]作为答案，若有多条直线穿过了相同数量的点，则选择S[0]值较小的直线返回，S[0]相同则选择S[1]值较小的直线返回。
 * 2 <= len(Points) <= 300
 * len(Points[i]) = 2
 */
public class Solution {

    public int[] bestLine(int[][] points) {
        int n = points.length;
        int[] res = null;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int deltaY1 = points[j][1] - points[i][1];
                int deltaX1 = points[j][0] - points[i][0];
                int sum = 1;
                for (int k = j + 1; k < n; k++) {
                    int deltaY2 = points[k][1] - points[i][1];
                    int deltaX2 = points[k][0] - points[i][0];
                    if (deltaY1 * deltaX2 == deltaY2 * deltaX1) {
                        sum++;
                    }
                }
                if (sum > max) {
                    max = sum;
                    res = new int[]{i, j};
                }
            }
        }
        return res;
    }

}
