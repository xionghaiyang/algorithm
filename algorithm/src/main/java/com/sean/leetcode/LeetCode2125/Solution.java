package com.sean.leetcode.LeetCode2125;

/**
 * @Author xionghaiyang
 * @Date 2025-10-27 17:11
 * @Description https://leetcode.cn/problems/number-of-laser-beams-in-a-bank
 * 2125. 银行中的激光束数量
 * 银行内部的防盗安全装置已经激活。
 * 给你一个下标从 0 开始的二进制字符串数组 bank ，表示银行的平面图，这是一个大小为 m x n 的二维矩阵。
 * bank[i] 表示第 i 行的设备分布，由若干 '0' 和若干 '1' 组成。
 * '0' 表示单元格是空的，而 '1' 表示单元格有一个安全设备。
 * 对任意两个安全设备而言，如果同时 满足下面两个条件，则二者之间存在 一个 激光束：
 * 两个设备位于两个 不同行 ：r1 和 r2 ，其中 r1 < r2 。
 * 满足 r1 < i < r2 的 所有 行 i ，都 没有安全设备 。
 * 激光束是独立的，也就是说，一个激光束既不会干扰另一个激光束，也不会与另一个激光束合并成一束。
 * 返回银行中激光束的总数量。
 * m == bank.length
 * n == bank[i].length
 * 1 <= m, n <= 500
 * bank[i][j] 为 '0' 或 '1'
 */
public class Solution {

    public int numberOfBeams(String[] bank) {
        int m = bank.length, n = bank[0].length();
        int res = 0;
        for (int i = 0, pre = 0; i < m; i++) {
            int cur = 0;
            for (int j = 0; j < n; j++) {
                cur += bank[i].charAt(j) - '0';
            }
            if (cur > 0) {
                res += pre * cur;
                pre = cur;
            }
        }
        return res;
    }

}
