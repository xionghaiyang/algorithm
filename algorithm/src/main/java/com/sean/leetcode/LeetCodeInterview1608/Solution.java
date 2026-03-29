package com.sean.leetcode.LeetCodeInterview1608;

/**
 * @Author xionghaiyang
 * @Date 2026-03-23 12:33
 * @Description https://leetcode.cn/problems/english-int-lcci
 * 面试题 16.08. 整数的英语表示
 * 给定一个整数，打印该整数的英文描述。
 */
public class Solution { 

    private String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] thousands = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 3, unit = 1_000_000_000; i >= 0; i--, unit /= 1000) {
            int curNum = num / unit;
            if (curNum != 0) {
                num -= curNum * unit;
                StringBuilder sb = new StringBuilder();
                process(sb, curNum);
                sb.append(thousands[i]).append(" ");
                res.append(sb);
            }
        }
        return res.toString().trim();
    }

    private void process(StringBuilder sb, int num) {
        if (num == 0) {
            return;
        } else if (num < 10) {
            sb.append(singles[num]).append(" ");
        } else if (num < 20) {
            sb.append(teens[num - 10]).append(" ");
        } else if (num < 100) {
            sb.append(tens[num / 10]).append(" ");
            process(sb, num % 10);
        } else {
            sb.append(singles[num / 100]).append(" Hundred ");
            process(sb, num % 100);
        }
    }

}
