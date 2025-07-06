package com.sean.leetcode.LeetCode3602;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-07-06 18:00
 * @Description https://leetcode.cn/problems/hexadecimal-and-hexatrigesimal-conversion
 * 3602. 十六进制和三十六进制转化
 * 给你一个整数 n。
 * 返回 n^2 的 十六进制表示 和 n^3 的 三十六进制表示 拼接成的字符串。
 * 十六进制 数定义为使用数字 0 – 9 和大写字母 A - F 表示 0 到 15 的值。
 * 三十六进制 数定义为使用数字 0 – 9 和大写字母 A - Z 表示 0 到 35 的值。
 * 1 <= n <= 1000
 */
public class Solution {

    Map<Integer, Character> map = new HashMap<Integer, Character>() {{
        put(0, '0');
        put(1, '1');
        put(2, '2');
        put(3, '3');
        put(4, '4');
        put(5, '5');
        put(6, '6');
        put(7, '7');
        put(8, '8');
        put(9, '9');
        put(10, 'A');
        put(11, 'B');
        put(12, 'C');
        put(13, 'D');
        put(14, 'E');
        put(15, 'F');
        put(16, 'G');
        put(17, 'H');
        put(18, 'I');
        put(19, 'J');
        put(20, 'K');
        put(21, 'L');
        put(22, 'M');
        put(23, 'N');
        put(24, 'O');
        put(25, 'P');
        put(26, 'Q');
        put(27, 'R');
        put(28, 'S');
        put(29, 'T');
        put(30, 'U');
        put(31, 'V');
        put(32, 'W');
        put(33, 'X');
        put(34, 'Y');
        put(35, 'Z');
    }};

    public String concatHex36(int n) {
        return getString(n * n, 16) + getString(n * n * n, 36);
    }

    private String getString(int n, int radix) {
        StringBuilder res = new StringBuilder();
        while (n > 0) {
            res.append(map.get(n % radix));
            n /= radix;
        }
        return res.reverse().toString();
    }

}
