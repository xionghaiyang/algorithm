package com.sean.leetcode.LeetCode3609;

/**
 * @Author xionghaiyang
 * @Date 2025-07-07 18:57
 * @Description https://leetcode.cn/problems/minimum-moves-to-reach-target-in-grid
 * 3609. 到达目标点的最小移动次数
 * 给你四个整数 sx、sy、tx 和 ty，表示在一个无限大的二维网格上的两个点 (sx, sy) 和 (tx, ty)。
 * 你的起点是 (sx, sy)。
 * 在任何位置 (x, y)，定义 m = max(x, y)。
 * 你可以执行以下两种操作之一：
 * 移动到 (x + m, y)，或者
 * 移动到 (x, y + m)。
 * 返回到达 (tx, ty) 所需的 最小 移动次数。
 * 如果无法到达目标点，则返回 -1。
 * 0 <= sx <= tx <= 10^9
 * 0 <= sy <= ty <= 10^9
 */
public class Solution {

    public int minMoves(int sx, int sy, int tx, int ty) {
        int res = 0;
        for (; tx != sx || ty != sy; res++) {
            if (tx < sx || ty < sy) {
                return -1;
            }
            if (tx == ty) {
                if (sy > 0) {
                    tx = 0;
                } else {
                    ty = 0;
                }
                continue;
            }
            //保证tx>ty
            if (tx < ty) {
                int tmp = tx;
                tx = ty;
                ty = tmp;
                tmp = sx;
                sx = sy;
                sy = tmp;
            }
            if (tx > ty * 2) {
                if (tx % 2 > 0) {
                    return -1;
                }
                tx >>= 1;
            } else {
                tx -= ty;
            }
        }
        return res;
    }

}
