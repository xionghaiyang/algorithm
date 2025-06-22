package com.sean.leetcode.LeetCode134;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-13 15:15
 * @Description: https://leetcode.cn/problems/gas-station
 * 134. 加油站
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，
 * 否则返回 -1 。
 * 如果存在解，则 保证 它是 唯一 的。
 * n == gas.length == cost.length
 * 1 <= n <= 10^5
 * 0 <= gas[i], cost[i] <= 10^4
 * 输入保证答案唯一。
 */
public class Solution {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int res = 0;
        int minS = 0, s = 0;
        for (int i = 0; i < n; i++) {
            s += gas[i] - cost[i];
            if (s < minS) {
                minS = s;
                res = i + 1;
            }
        }
        return s < 0 ? -1 : res;
    }

}
