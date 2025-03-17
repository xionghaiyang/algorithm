package com.sean.leetcode.LeetCode1963;

/**
 * @Author xionghaiyang
 * @Date 2025-03-17 10:26
 * @Description https://leetcode.cn/problems/minimum-number-of-swaps-to-make-the-string-balanced
 * 1963. 使字符串平衡的最小交换次数
 * 给你一个字符串 s ，下标从 0 开始 ，且长度为偶数 n 。
 * 字符串 恰好 由 n / 2 个开括号 '[' 和 n / 2 个闭括号 ']' 组成。
 * 只有能满足下述所有条件的字符串才能称为 平衡字符串 ：
 * 字符串是一个空字符串，或者
 * 字符串可以记作 AB ，其中 A 和 B 都是 平衡字符串 ，或者
 * 字符串可以写成 [C] ，其中 C 是一个 平衡字符串 。
 * 你可以交换 任意 两个下标所对应的括号 任意 次数。
 * 返回使 s 变成 平衡字符串 所需要的 最小 交换次数。
 * n == s.length
 * 2 <= n <= 10^6
 * n 为偶数
 * s[i] 为'[' 或 ']'
 * 开括号 '[' 的数目为 n / 2 ，闭括号 ']' 的数目也是 n / 2
 */
public class Solution {

    public int minSwaps(String s) {
        int res = 0;
        int cnt = 0;
        for (char b : s.toCharArray()) {
            if (b == '[') {
                cnt++;
            } else if (cnt > 0) {
                cnt--;
            } else {//cnt = 0
                res++;
                cnt++;
            }
        }
        return res;
    }

    public int minSwaps1(String s) {
        char[] str = s.toCharArray();
        int n = s.length();
        int leftCnt = 0, rightCnt = 0;
        for (int i = 0; i < n; i++) {
            if (str[i] == '[') {
                leftCnt++;
            } else {
                rightCnt++;
                if (leftCnt > 0) {
                    rightCnt--;
                    leftCnt--;
                }
            }
        }
        return (leftCnt + 1) / 2;
    }

}
