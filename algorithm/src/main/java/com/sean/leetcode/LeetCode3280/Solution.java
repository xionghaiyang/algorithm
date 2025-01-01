package com.sean.leetcode.LeetCode3280;

/**
 * @Author xionghaiyang
 * @Date 2025-01-01 17:03
 * @Description https://leetcode.cn/problems/convert-date-to-binary/
 * 3280. 将日期转换为二进制表示
 * 给你一个字符串 date，它的格式为 yyyy-mm-dd，表示一个公历日期。
 * date 可以重写为二进制表示，只需要将年、月、日分别转换为对应的二进制表示（不带前导零）并遵循 year-month-day 的格式。
 * 返回 date 的 二进制 表示。
 */
public class Solution {

    public String convertDateToBinary(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        return binary(year) + "-" + binary(month) + "-" + binary(day);
    }

    private String binary(int x) {
        StringBuilder res = new StringBuilder();
        for (; x != 0; x >>= 1) {
            res.append(x & 1);
        }
        return res.reverse().toString();
    }

}
