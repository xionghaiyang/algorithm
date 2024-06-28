package com.sean.leetcode.LeetCode2229;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-28 09:44
 * @Description: https://leetcode.cn/problems/strong-password-checker-ii/
 * 2299. 强密码检验器 II
 * 如果一个密码满足以下所有条件，我们称它是一个 强 密码：
 * 它有至少 8 个字符。
 * 至少包含 一个小写英文 字母。
 * 至少包含 一个大写英文 字母。
 * 至少包含 一个数字 。
 * 至少包含 一个特殊字符 。特殊字符为："!@#$%^&*()-+" 中的一个。
 * 它 不 包含 2 个连续相同的字符（比方说 "aab" 不符合该条件，但是 "aba" 符合该条件）。
 * 给你一个字符串 password ，如果它是一个 强 密码，返回 true，否则返回 false 。
 */
public class Solution {

    public boolean strongPasswordCheckerII(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        int n = password.length();
        boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false;
        Set<Character> set = new HashSet<Character>() {{
            add('!');
            add('@');
            add('#');
            add('$');
            add('%');
            add('^');
            add('&');
            add('*');
            add('(');
            add(')');
            add('-');
            add('+');
        }};
        for (int i = 0; i < n; i++) {
            if (i > 0 && password.charAt(i) == password.charAt(i - 1)) {
                return false;
            }
            char c = password.charAt(i);
            if (Character.isLowerCase(c)) {
                flag1 = true;
            } else if (Character.isUpperCase(c)) {
                flag2 = true;
            } else if (Character.isDigit(c)) {
                flag3 = true;
            } else if (set.contains(c)) {
                flag4 = true;
            }
        }
        return flag1 && flag2 && flag3 && flag4;
    }

}
