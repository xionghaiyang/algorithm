package com.sean.leetcode.LeetCode835;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-13 06:17
 * @Description: https://leetcode.cn/problems/image-overlap
 * 835. 图像重叠
 * 给你两个图像 img1 和 img2 ，两个图像的大小都是 n x n ，用大小相同的二进制正方形矩阵表示。
 * 二进制矩阵仅由若干 0 和若干 1 组成。
 * 转换 其中一个图像，将所有的 1 向左，右，上，或下滑动任何数量的单位；然后把它放在另一个图像的上面。
 * 该转换的 重叠 是指两个图像 都 具有 1 的位置的数目。
 * 请注意，转换 不包括 向任何方向旋转。
 * 越过矩阵边界的 1 都将被清除。
 * 最大可能的重叠数量是多少？
 * n == img1.length == img1[i].length
 * n == img2.length == img2[i].length
 * 1 <= n <= 30
 * img1[i][j] 为 0 或 1
 * img2[i][j] 为 0 或 1
 */
public class Solution {

    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int res = 0;
        for (int dx = 1 - n; dx < n; dx++) {
            for (int dy = 1 - n; dy < n; dy++) {
                int cnt1 = 0;
                for (int i = Math.max(-dx, 0); i < Math.min(n - dx, n); i++) {
                    for (int j = Math.max(-dy, 0); j < Math.min(n - dy, n); j++) {
                        cnt1 += img1[i][j] * img2[i + dx][j + dy];
                    }
                }
                res = Math.max(res, cnt1);
            }
        }
        return res;
    }

}
