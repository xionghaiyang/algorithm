package com.sean.leetcode.LeetCode1291;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-13 08:15
 * @Description: https://leetcode.cn/problems/sequential-digits
 * 1291. 顺次数
 * 我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。
 * 请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。
 * 10 <= low <= high <= 10^9
 */
public class Solution {

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            int num = i;
            for (int j = i + 1; j <= 9 && num <= high; j++) {
                num = num * 10 + j;
                if (low <= num && num <= high) {
                    res.add(num);
                }
            }
        }
        Collections.sort(res);
        return res;
    }

}
