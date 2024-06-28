package com.sean.leetcode.LeetCode1238;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-23 08:12
 * @Description: https://leetcode.cn/problems/circular-permutation-in-binary-representation/
 * 1238. 循环码排列
 * 给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足：
 * p[0] = start
 * p[i] 和 p[i+1] 的二进制表示形式只有一位不同
 * p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同
 */
public class Solution {

    public List<Integer> circularPermutation1(int n, int start) {
        List<Integer> res = new ArrayList<>();
        res.add(start);
        for (int i = 1; i <= n; i++) {
            int m = res.size();
            for (int j = m - 1; j >= 0; j--) {
                res.add(((res.get(j) ^ start) | (1 << (i - 1))) ^ start);
            }
        }
        return res;
    }

    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            res.add((i >> 1) ^ i ^ start);
        }
        return res;
    }

}
