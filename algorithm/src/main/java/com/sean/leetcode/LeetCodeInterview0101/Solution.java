package com.sean.leetcode.LeetCodeInterview0101;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-23 12:16
 * @Description: https://leetcode.cn/problems/is-unique-lcci
 * 面试题 01.01. 判定字符是否唯一
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 * 0 <= len(s) <= 100
 * s[i]仅包含小写字母
 * 如果你不使用额外的数据结构，会很加分。
 */
public class Solution {

    public boolean isUnique(String astr) {
        int mask = 0;
        for (char c : astr.toCharArray()) {
            int cur = 1 << (c - 'a');
            if ((mask & cur) != 0) {
                return false;
            } else {
                mask |= cur;
            }
        }
        return true;
    }

}
