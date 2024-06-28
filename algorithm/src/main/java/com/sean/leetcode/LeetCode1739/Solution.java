package com.sean.leetcode.LeetCode1739;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-26 16:01
 * @Description: https://leetcode.cn/problems/building-boxes/
 * 1739. 放置盒子
 * 有一个立方体房间，其长度、宽度和高度都等于 n 个单位。
 * 请你在房间里放置 n 个盒子，每个盒子都是一个单位边长的立方体。放置规则如下：
 * 你可以把盒子放在地板上的任何地方。
 * 如果盒子 x 需要放置在盒子 y 的顶部，那么盒子 y 竖直的四个侧面都 必须 与另一个盒子或墙相邻。
 * 给你一个整数 n ，返回接触地面的盒子的 最少 可能数量。
 */
public class Solution {

    public int minimumBoxes(int n) {
        int cur = 1, i = 1, j = 1;
        while (n > cur) {
            n -= cur;
            i++;
            cur += i;
        }
        cur = 1;
        while (n > cur) {
            n -= cur;
            j++;
            cur++;
        }
        return (i - 1) * i / 2 + j;
    }

}
