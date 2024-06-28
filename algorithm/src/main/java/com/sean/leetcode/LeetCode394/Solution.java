package com.sean.leetcode.LeetCode394;

import java.util.LinkedList;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-07 22:55
 * @Description: https://leetcode.cn/problems/decode-string/?envType=study-plan-v2&envId=leetcode-75
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
 * 注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 */
public class Solution {

    int index;

    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        LinkedList<String> stk = new LinkedList<>();
        int n = s.length();
        index = 0;
        while (index < n) {
            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                //获取一个数字并进栈
                stk.addLast(getDigits(s));
            } else if (Character.isLetter(c) || c == '[') {
                //获取一个字母或左括号并进栈
                stk.addLast(String.valueOf(s.charAt(index++)));
            } else {
                //遇到右括号
                index++;
                LinkedList<String> sub = new LinkedList<>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addFirst(stk.removeLast());
                }
                //左括号出栈
                stk.removeLast();
                int cnt = Integer.parseInt(stk.removeLast());
                StringBuilder str = new StringBuilder();
                StringBuilder substr = getStringBuilder(sub);
                while (cnt > 0) {
                    str.append(substr.toString());
                    cnt--;
                }
                stk.addLast(str.toString());
            }
        }
        return getStringBuilder(stk).toString();
    }

    private String getDigits(String s) {
        StringBuilder res = new StringBuilder();
        while (Character.isDigit(s.charAt(index))) {
            res.append(s.charAt(index++));
        }
        return res.toString();
    }

    private StringBuilder getStringBuilder(LinkedList<String> stk) {
        StringBuilder res = new StringBuilder();
        while (!stk.isEmpty()) {
            res.append(stk.removeFirst());
        }
        return res;
    }

}
