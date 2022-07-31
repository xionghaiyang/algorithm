package com.sean.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode17 {

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
        char ch = digits.charAt(index);
        String letters = map.get(ch);
        int len = letters.length();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(letters.charAt(i));
            dfs(digits, index + 1);
            stringBuilder.deleteCharAt(index);
        }
    }

}
