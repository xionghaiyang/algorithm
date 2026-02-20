package com.sean.leetcode.LeetCode761;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-08 11:30
 * @Description: https://leetcode.cn/problems/special-binary-string
 * 761. 特殊的二进制序列
 * 特殊的二进制序列是具有以下两个性质的二进制序列：
 * 0 的数量与 1 的数量相等。
 * 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。
 * 给定一个特殊的二进制序列 S，以字符串形式表示。
 * 定义一个操作 为首先选择 S 的两个连续且非空的特殊的子串，然后将它们交换。
 * （两个子串为连续的当且仅当第一个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。)
 * 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？
 * 1 <= s.length <= 50
 * s[i] 为 '0' 或 '1'。
 * s 是一个特殊的二进制字符串。
 */
public class Solution {

    public String makeLargestSpecial(String s) {
        List<String> items = new ArrayList<>();
        int n = s.length();
        for (int left = 0, right = 0, cnt = 0; right < n; right++) {
            cnt += s.charAt(right) == '1' ? 1 : -1;
            if (cnt == 0) {
                items.add("1" + makeLargestSpecial(s.substring(left + 1, right)) + "0");
                left = right + 1;
            }
        }
        items.sort(Comparator.reverseOrder());
        StringBuilder res = new StringBuilder();
        for (String item : items) {
            res.append(item);
        }
        return res.toString();
    }

}
