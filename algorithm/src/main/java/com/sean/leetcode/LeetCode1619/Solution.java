package com.sean.leetcode.LeetCode1619;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-14 08:25
 * @Description: https://leetcode.cn/problems/mean-of-array-after-removing-some-elements/
 * 1619. 删除某些元素后的数组均值
 * 给你一个整数数组 arr ，请你删除最小 5% 的数字和最大 5% 的数字后，剩余数字的平均值。
 * 与 标准答案 误差在 10-5 的结果都被视为正确结果。
 */
public class Solution {

    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int m = n / 20;
        int sum = 0;
        for (int i = m; i < n - m; i++) {
            sum += arr[i];
        }
        return (double) sum / (n - 2 * m);
    }

}
