package com.sean.leetcode.LeetCode17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-09 20:18
 * @Description: https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class Solution {

    StringBuilder stringBuilder = new StringBuilder();
    List<String> res = new ArrayList<>();
    Map<Character, String> map = new HashMap<Character, String>() {{
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
        dfs(digits, 0);
        return res;
    }

    private void dfs(String digits, int index) {
        if (index == digits.length()) {
            res.add(stringBuilder.toString());
            return;
        }
        String str = map.get(digits.charAt(index));
        for (int i = 0; i < str.length(); i++) {
            stringBuilder.append(str.charAt(i));
            dfs(digits, index + 1);
            stringBuilder.deleteCharAt(index);
        }
    }

}
