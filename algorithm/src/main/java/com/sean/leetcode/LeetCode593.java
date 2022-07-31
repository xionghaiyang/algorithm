package com.sean.leetcode;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-07-29 09:18
 */
public class LeetCode593 {

    /**
     * https://leetcode.cn/problems/valid-square/
     * 给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
     * 点的坐标 pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
     * 一个 有效的正方形 有四条等边和四个等角(90度角)。
     */

    class Vector {

        int[] point;
        int length;

        public Vector(int[] x, int[] y) {
            point = new int[4];
            point[0] = x[0] + y[0];
            point[1] = x[1] + y[1];
            point[2] = x[0] - y[0];
            point[3] = x[1] - y[1];
            this.length = point[2] * point[2] + point[3] * point[3];
        }

        public boolean isSameMidpoint(Vector that) {
            return this.point[0] == that.point[0] && this.point[1] == that.point[1];
        }

        public int getLength() {
            return length;
        }

        public int inner(Vector that) {
            return this.point[2] * that.point[2] + this.point[3] * that.point[3];
        }
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (process(p1, p2, p3, p4)) {
            return true;
        }
        if (process(p1, p3, p2, p4)) {
            return true;
        }
        if (process(p1, p4, p2, p3)) {
            return true;
        }
        return false;
    }

    private boolean process(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (Arrays.equals(p1, p2)) {
            return false;
        }
        /**
         * 1.如果两条斜边的中点相同：则说明以该两条斜边组成的四边形为「平行四边形」。
         * 2.在满足「条件一」的基础上，如果两条斜边的长度相同：则说明以该两条斜边组成的四边形为「矩形」。
         * 3.在满足「条件二」的基础上，如果两条斜边的相互垂直：则说明以该两条斜边组成的四边形为「正方形」。
         */
        Vector vector1 = new Vector(p1, p2);
        Vector vector2 = new Vector(p3, p4);
        return vector1.isSameMidpoint(vector2) && vector1.getLength() == vector2.getLength() && vector1.inner(vector2) == 0;
    }

}
