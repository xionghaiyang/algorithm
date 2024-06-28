package com.sean.leetcode.LeetCode816;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-07 08:20
 * @Description: https://leetcode.cn/problems/ambiguous-coordinates/
 * 816. 模糊坐标
 * 我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。
 * 返回所有可能的原始字符串到一个列表中。
 * 原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。
 * 此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
 * 最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
 */
public class Solution {

    public List<String> ambiguousCoordinates(String s) {
        int n = s.length() - 2;
        List<String> res = new ArrayList<>();
        s = s.substring(1, s.length() - 1);
        for (int left = 1; left < n; left++) {
            List<String> leftList = process(s.substring(0, left));
            if (leftList.isEmpty()) {
                continue;
            }
            List<String> rightList = process(s.substring(left));
            if (rightList.isEmpty()) {
                continue;
            }
            for (String i : leftList) {
                for (String j : rightList) {
                    res.add("(" + i + ", " + j + ")");
                }
            }
        }
        return res;
    }

    private List<String> process(String s) {
        List<String> res = new ArrayList<>();
        if (s.charAt(0) != '0' || "0".equals(s)) {
            res.add(s);
        }
        for (int i = 1; i < s.length(); i++) {
            if ((i != 1 && s.charAt(0) == '0') || s.charAt(s.length() - 1) == '0') {
                continue;
            }
            res.add(s.substring(0, i) + "." + s.substring(i));
        }
        return res;
    }

}
