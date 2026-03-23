package com.sean.leetcode.LeetCodeInterview1613;

/**
 * @Author xionghaiyang
 * @Date 2026-03-23 19:33
 * @Description https://leetcode.cn/problems/bisect-squares-lcci
 * 面试题 16.13. 平分正方形
 * 给定两个正方形及一个二维平面。
 * 请找出将这两个正方形分割成两半的一条直线。
 * 假设正方形顶边和底边与 x 轴平行。
 * 每个正方形的数据square包含3个数值，正方形的左下顶点坐标[X,Y] = [square[0],square[1]]，以及正方形的边长square[2]。
 * 所求直线穿过两个正方形会形成4个交点，请返回4个交点形成线段的两端点坐标（两个端点即为4个交点中距离最远的2个点，这2个点所连成的线段一定会穿过另外2个交点）。
 * 2个端点坐标[X1,Y1]和[X2,Y2]的返回格式为{X1,Y1,X2,Y2}，要求若X1 != X2，需保证X1 < X2，否则需保证Y1 <= Y2。
 * 若同时有多条直线满足要求，则选择斜率最大的一条计算并返回（与Y轴平行的直线视为斜率无穷大）。
 * square.length == 3
 * square[2] > 0
 */
public class Solution {

    public double[] cutSquares(int[] square1, int[] square2) {
        //第一个正方形的中心点坐标及正方形的边长
        double x1 = square1[0] + square1[2] / 2.0;
        double y1 = square1[1] + square1[2] / 2.0;
        int d1 = square1[2];
        //第二个正方形的中心点坐标及正方形边长
        double x2 = square2[0] + square2[2] / 2.0;
        double y2 = square2[1] + square2[2] / 2.0;
        int d2 = square2[2];
        double[] res = new double[4];
        if (x1 == x2) {
            //斜率不存在
            res[0] = x1;
            res[1] = Math.min(square1[1], square2[1]);
            res[2] = x1;
            res[3] = Math.max(square1[1] + d1, square2[1] + d2);
        } else {
            //斜率存在，y = kx + b
            //斜率
            double k = (y1 - y2) / (x1 - x2);
            //系数
            double b = y1 - k * x1;
            if (Math.abs(k) > 1) {
                //斜率绝对值大于1，说明与正方形的上边和下边相交
                //底边
                res[1] = Math.min(square1[1], square2[1]);
                res[0] = (res[1] - b) / k;
                //顶边
                res[3] = Math.max(square1[1] + d1, square2[1] + d2);
                res[2] = (res[3] - b) / k;
            } else {
                //斜率绝对值小于等于1，说明与正方形的左边和右边相交
                //左边
                res[0] = Math.min(square1[0], square2[0]);
                res[1] = k * res[0] + b;
                //右边
                res[2] = Math.max(square1[0] + d1, square2[0] + d2);
                res[3] = k * res[2] + b;
            }
        }
        if (res[0] > res[2]) {
            swap(res, 0, 2);
            swap(res, 1, 3);
        }
        return res;
    }

    private void swap(double[] res, int x, int y) {
        double temp = res[x];
        res[x] = res[y];
        res[y] = temp;
    }

}
