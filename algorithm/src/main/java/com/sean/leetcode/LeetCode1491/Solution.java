package com.sean.leetcode.LeetCode1491;

/**
 * @Author xionghaiyang
 * @Date 2024-05-03 12:51
 * @Description https://leetcode.cn/problems/average-salary-excluding-the-minimum-and-maximum-salary/
 * 1491. 去掉最低工资和最高工资后的工资平均值
 * 给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。
 * 请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。
 */
public class Solution {

    public double average(int[] salary) {
        int n = salary.length;
        int sum = 0;
        int min = salary[0], max = salary[0];
        for (int i = 0; i < n; i++) {
            min = Math.min(min, salary[i]);
            max = Math.max(max, salary[i]);
            sum += salary[i];
        }
        return (double) (sum - max - min) / (n - 2);
    }

}
