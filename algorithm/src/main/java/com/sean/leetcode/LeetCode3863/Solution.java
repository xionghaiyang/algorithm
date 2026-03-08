package com.sean.leetcode.LeetCode3863;

/**
 * @Author xionghaiyang
 * @Date 2026-03-08 15:11
 * @Description https://leetcode.cn/problems/minimum-operations-to-sort-a-string
 * 3863. 将一个字符串排序的最小操作次数
 * 给你一个由小写英文字母组成的字符串 s。
 * 在一次操作中，你可以选择 s 的任意 子字符串（但 不能 是整个字符串），并将其按 字母升序 进行 排序。
 * 返回使 s 按 升序 排列所需的 最小 操作次数。
 * 如果无法做到，则返回 -1。
 * 子字符串 是字符串中连续的 非空 字符序列。
 * 1 <= s.length <= 10^5
 * s 仅由小写英文字母组成。
 */
public class Solution {

    public int minOperations(String s) {
        int n = s.length();
        char[] str = s.toCharArray();
        boolean isSorted = true;
        for (int i = 1; i < n; i++) {
            if (str[i - 1] > str[i]) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            return 0;
        }
        if (n == 2) {
            return -1;
        }
        char min = str[0], max = str[1];
        for (char c : str) {
            min = (char) Math.min(min, c);
            max = (char) Math.max(max, c);
        }
        if (str[0] == min || str[n - 1] == max) {
            //如果str[0]是最小值，排序[1,n-1]
            //如果str[0]是最大值，排序[0,n-2]
            return 1;
        }
        //如果[1,n-2]中有最小值，排序[0,n-2]，然后排序[1,n-1]
        //如果[1,n-2]中有最大值，排序[1,n-1],然后排序[0,n-2]
        for (int i = 1; i < n - 1; i++) {
            if (str[i] == min || str[i] == max) {
                return 2;
            }
        }
        //str[0]是最大值，str[n-1]是最小值，需要先把其中任意一个值放中间
        return 3;
    }

}
