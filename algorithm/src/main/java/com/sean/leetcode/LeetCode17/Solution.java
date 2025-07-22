package com.sean.leetcode.LeetCode17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-09 20:18
 * @Description: https://leetcode.cn/problems/letter-combinations-of-a-phone-number
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。
 * 注意 1 不对应任何字母。
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class Solution {

    private List<String> res = new ArrayList<>();
    private int n;
    private StringBuilder str = new StringBuilder();
    private Map<Character, String> map = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        n = digits.length();
        process(digits, 0);
        return res;
    }

    private void process(String digits, int i) {
        if (i == n) {
            res.add(str.toString());
            return;
        }
        String s = map.get(digits.charAt(i));
        for (char c : s.toCharArray()) {
            str.append(c);
            process(digits, i + 1);
            str.deleteCharAt(i);
        }
    }

}
