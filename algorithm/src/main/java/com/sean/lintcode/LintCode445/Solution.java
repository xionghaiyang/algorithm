package com.sean.lintcode.LintCode445;

/**
 * @Author xionghaiyang
 * @Date 2022-08-14 08:02
 * @Description https://www.lintcode.com/problem/445/?showListFe=true&page=1&pageSize=50
 * 445 · 余弦相似度
 * 余弦相似性是内积空间的两个矢量之间的相似性的度量，其测量它们之间的角度的余弦。
 * 0° 的余弦为 1，对于任何其他角度，余弦小于 1。
 * 给你两个相同大小的向量 A B，求出他们的余弦相似度。
 * 返回 2.0000 如果余弦相似不合法 (比如 A = [0] B = [0])。
 */
public class Solution {

    public double cosineSimilarity(int[] A, int[] B) {
        double aLength = length(A);
        double bLength = length(B);
        if (aLength == 0 || bLength == 0) {
            return 2.0000;
        }
        double fz = aLength * bLength;
        double fm = 0;
        for (int i = 0; i < A.length; i++) {
            fm += A[i] * B[i];
        }
        return fm / fz;
    }

    private double length(int[] arr) {
        double sum = 0;
        for (int num : arr) {
            sum += Math.pow(num, 2);
        }
        return Math.sqrt(sum);
    }

}
