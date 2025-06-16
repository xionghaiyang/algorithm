package com.sean.leetcode.LeetCode394;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-07 22:55
 * @Description: https://leetcode.cn/problems/decode-string
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
 * 注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 1 <= s.length <= 30
 * s 由小写英文字母、数字和方括号 '[]' 组成
 * s 保证是一个 有效 的输入。
 * s 中所有整数的取值范围为 [1, 300]
 */
public class Solution {

    public String decodeString(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<StringBuilder> strStack = new ArrayDeque<>();
        StringBuilder curStr = new StringBuilder();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                numStack.push(num);
                num = 0;
                strStack.push(curStr);
                curStr = new StringBuilder();
            } else if (c == ']') {
                int repeatTimes = numStack.pop();
                StringBuilder temp = strStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(curStr);
                }
                curStr = temp;
            } else {
                curStr.append(c);
            }
        }
        return curStr.toString();
    }

}
