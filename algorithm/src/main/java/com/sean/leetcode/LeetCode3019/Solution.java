package com.sean.leetcode.LeetCode3019;

/**
 * @Author xionghaiyang
 * @Date 2025-01-29 07:59
 * @Description https://leetcode.cn/problems/number-of-changing-keys
 * 3019. 按键变更的次数
 * 给你一个下标从 0 开始的字符串 s ，该字符串由用户输入。
 * 按键变更的定义是：使用与上次使用的按键不同的键。
 * 例如 s = "ab" 表示按键变更一次，而 s = "bBBb" 不存在按键变更。
 * 返回用户输入过程中按键变更的次数。
 * 注意：shift 或 caps lock 等修饰键不计入按键变更，也就是说，如果用户先输入字母 'a' 然后输入字母 'A' ，不算作按键变更。
 * 1 <= s.length <= 100
 * s 仅由英文大写字母和小写字母组成。
 */
public class Solution {

    public int countKeyChanges(String s) {
        int n = s.length();
        int res = 0;
        char last = Character.toLowerCase(s.charAt(0));
        for (int i = 1; i < n; i++) {
            char cur = Character.toLowerCase(s.charAt(i));
            if (cur != last) {
                res++;
                last = cur;
            }
        }
        return res;
    }

}
