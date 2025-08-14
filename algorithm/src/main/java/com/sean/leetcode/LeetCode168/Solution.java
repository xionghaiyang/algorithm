package com.sean.leetcode.LeetCode168;

/**
 * @Author xionghaiyang
 * @Date 2025-08-14 14:44
 * @Description https://leetcode.cn/problems/excel-sheet-column-title
 * 168. Excel 表列名称
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 * 例如：
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 1 <= columnNumber <= 2^31 - 1
 */
public class Solution {

    public String convertToTitle(int columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber > 0) {
            int a0 = (columnNumber - 1) % 26 + 1;
            res.append((char) (a0 - 1 + 'A'));
            columnNumber = (columnNumber - a0) / 26;
        }
        return res.reverse().toString();
    }

    public String convertToTitle1(int columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            res.append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }
        return res.reverse().toString();
    }

}
