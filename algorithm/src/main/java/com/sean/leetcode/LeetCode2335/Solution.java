package com.sean.leetcode.LeetCode2335;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-13 09:40
 * @Description: https://leetcode.cn/problems/minimum-amount-of-time-to-fill-cups/
 * 2335. 装满杯子需要的最短总时长
 * 现有一台饮水机，可以制备冷水、温水和热水。
 * 每秒钟，可以装满 2 杯 不同 类型的水或者 1 杯任意类型的水。
 * 给你一个下标从 0 开始、长度为 3 的整数数组 amount ，其中 amount[0]、amount[1] 和 amount[2] 分别表示需要装满冷水、温水和热水的杯子数量。
 * 返回装满所有杯子所需的 最少 秒数。
 */
public class Solution {

    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        if (amount[2] > amount[0] + amount[1]) {
            return amount[2];
        }
        return (amount[0] + amount[1] + amount[2] + 1) / 2;
    }

}
