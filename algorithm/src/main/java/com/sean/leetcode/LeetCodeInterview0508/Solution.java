package com.sean.leetcode.LeetCodeInterview0508;

/**
 * @Author xionghaiyang
 * @Date 2026-03-13 20:21
 * @Description https://leetcode.cn/problems/draw-line-lcci
 * 面试题 05.08. 绘制直线
 * 已知一个由像素点组成的单色屏幕，每行均有 w 个像素点，所有像素点初始为 0，左上角位置为 (0,0)。
 * 现将每行的像素点按照「每 32 个像素点」为一组存放在一个 int 中，再依次存入长度为 length 的一维数组中。
 * 我们将在屏幕上绘制一条从点 (x1,y) 到点 (x2,y) 的直线（即像素点修改为 1），请返回绘制过后的数组。
 * 1 <= length <= 10^5
 * 1 <= w <= 3 * 10^5
 * 0 <= x1 <= x2 < w
 * 0 <= y <= 10
 */
public class Solution {

    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] res = new int[length];
        int low = (y * w + x1) / 32, high = (y * w + x2) / 32;
        for (int i = low; i <= high; i++) {
            res[i] = -1;
        }
        res[low] = res[low] >>> x1 % 32;
        res[high] = res[high] & Integer.MIN_VALUE >> x2 % 32;
        return res;
    }

}
