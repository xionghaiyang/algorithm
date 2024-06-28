package com.sean.leetcode.LeetCode667;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-08 08:32
 * @Description: https://leetcode.cn/problems/beautiful-arrangement-ii/
 * 667. 优美的排列 II
 * 给你两个整数 n 和 k ，请你构造一个答案列表 answer ，该列表应当包含从 1 到 n 的 n 个不同正整数，并同时满足下述条件：
 * 假设该列表是 answer = [a1, a2, a3, ... , an] ，那么列表 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数。
 * 返回列表 answer 。如果存在多种答案，只需返回其中 任意一种 。
 */
public class Solution {

    public int[] constructArray(int n, int k) {
        int[] res = new int[n];
        int index = 0;
        for (int i = 1; i < n - k; i++) {
            res[index++] = i;
        }
        for (int i = n - k, j = n; i <= j; i++, j--) {
            res[index++] = i;
            if (i != j) {
                res[index++] = j;
            }
        }
        return res;
    }

}
