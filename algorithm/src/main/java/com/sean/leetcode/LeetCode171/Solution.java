package com.sean.leetcode.LeetCode171;

/**
 * @Author xionghaiyang
 * @Date 2025-08-14 15:06
 * @Description https://leetcode.cn/problems/excel-sheet-column-number
 * 171. Excel 表列序号
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。
 * 返回 该列名称对应的列序号 。
 * 例如：
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * 1 <= columnTitle.length <= 7
 * columnTitle 仅由大写英文组成
 * columnTitle 在范围 ["A", "FXSHRXW"] 内
 */
public class Solution {

    public int titleToNumber(String columnTitle) {
        int res = 0;
        for (char c : columnTitle.toCharArray()) {
            res = res * 26 + (c - 'A' + 1);
        }
        return res;
    }

}
