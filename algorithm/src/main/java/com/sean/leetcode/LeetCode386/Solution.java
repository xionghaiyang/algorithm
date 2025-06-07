package com.sean.leetcode.LeetCode386;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-06-08 05:51
 * @Description https://leetcode.cn/problems/lexicographical-numbers
 * 386. 字典序排数
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 * 1 <= n <= 5 * 10^4
 */
public class Solution {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0, num = 1; i < n; i++) {
            res.add(num);
            if (num * 10 <= n) {
                num *= 10;
            } else {
                while (num % 10 == 9 || num + 1 > n) {
                    num /= 10;
                }
                num++;
            }
        }
        return res;
    }

    public List<Integer> lexicalOrder1(int n) {
        List<Integer> res = new ArrayList<>();
        process(n, 0, res);
        return res;
    }

    private void process(int n, int pre, List<Integer> res) {
        for (int i = 0; i <= 9; i++) {
            int cur = pre * 10 + i;
            if (cur == 0) {
                continue;
            }
            if (cur <= n) {
                res.add(cur);
            }
            if (cur * 10 <= n) {
                process(n, cur, res);
            }
        }
    }

}
