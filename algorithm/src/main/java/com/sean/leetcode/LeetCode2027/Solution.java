package com.sean.leetcode.LeetCode2027;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-27 08:23
 * @Description: https://leetcode.cn/problems/minimum-moves-to-convert-string/
 * 2027. 转换字符串的最少操作次数
 * 给你一个字符串 s ，由 n 个字符组成，每个字符不是 'X' 就是 'O' 。
 * 一次 操作 定义为从 s 中选出 三个连续字符 并将选中的每个字符都转换为 'O' 。
 * 注意，如果字符已经是 'O' ，只需要保持 不变 。
 * 返回将 s 中所有字符均转换为 'O' 需要执行的 最少 操作次数。
 */
public class Solution {

    public int minimumMoves(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int res = 0;
        int covered = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'X' && i > covered) {
                res++;
                covered = i + 2;
            }
        }
        return res;
    }

}
