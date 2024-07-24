package com.sean.leetcode.LeetCode2844;

/**
 * @Author xionghaiyang
 * @Date 2024-07-25 06:44
 * @Description https://leetcode.cn/problems/minimum-operations-to-make-a-special-number/
 * 2844. 生成特殊数字的最少操作
 * 给你一个下标从 0 开始的字符串 num ，表示一个非负整数。
 * 在一次操作中，您可以选择 num 的任意一位数字并将其删除。
 * 请注意，如果你删除 num 中的所有数字，则 num 变为 0。
 * 返回最少需要多少次操作可以使 num 变成特殊数字。
 * 如果整数 x 能被 25 整除，则该整数 x 被认为是特殊数字。
 */
public class Solution {

    public int minimumOperations(String num) {
        int n = num.length();
        boolean find0 = false, find5 = false;
        for (int i = n - 1; i >= 0; i--) {
            if (num.charAt(i) == '0' || num.charAt(i) == '5') {
                if (find0) {
                    return n - i - 2;
                }
                if (num.charAt(i) == '0') {
                    find0 = true;
                } else {
                    find5 = true;
                }
            } else if (num.charAt(i) == '2' || num.charAt(i) == '7') {
                if (find5) {
                    return n - i - 2;
                }
            }
        }
        if (find0) {
            return n - 1;
        }
        return n;
    }

}
