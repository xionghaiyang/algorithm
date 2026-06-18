package com.sean.leetcode.LeetCode1732;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-19 22:00
 * @Description: https://leetcode.cn/problems/find-the-highest-altitude
 * 1732. 找到最高海拔
 * 有一个自行车手打算进行一场公路骑行，这条路线总共由 n + 1 个不同海拔的点组成。
 * 自行车手从海拔为 0 的点 0 开始骑行。
 * 给你一个长度为 n 的整数数组 gain ，其中 gain[i] 是点 i 和点 i + 1 的 净海拔高度差（0 <= i < n）。
 * 请你返回 最高点的海拔 。
 * n == gain.length
 * 1 <= n <= 100
 * -100 <= gain[i] <= 100
 */
public class Solution {

    public int largestAltitude(int[] gain) {
        int res = 0, height = 0;
        for (int delta : gain) {
            height += delta;
            res = Math.max(res, height);
        }
        return res;
    }

}
