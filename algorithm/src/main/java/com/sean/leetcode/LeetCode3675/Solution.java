package com.sean.leetcode.LeetCode3675;

/**
 * @Author xionghaiyang
 * @Date 2025-09-07 18:32
 * @Description https://leetcode.cn/problems/minimum-operations-to-transform-string
 * 3675. 转换字符串的最小操作次数
 * 给你一个仅由小写英文字母组成的字符串 s。
 * 你可以执行以下操作任意次（包括零次）：
 * 选择字符串中出现的一个字符 c，并将 每个 出现的 c 替换为英文字母表中 下一个 小写字母。
 * 返回将 s 转换为仅由 'a' 组成的字符串所需的最小操作次数。
 * 注意：字母表是循环的，因此 'z' 的下一个字母是 'a'。
 * 1 <= s.length <= 5 * 10^5
 * s 仅由小写英文字母组成。
 */
public class Solution {

    public int minOperations(String s) {
        int min = 'z' + 1;
        for (char c : s.toCharArray()) {
            if (c != 'a') {
                min = Math.min(min, c);
            }
        }
        return 'z' + 1 - min;
    }

}
