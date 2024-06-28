package com.sean.leetcode.LeetCode500;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-01 15:06
 * @Description: https://leetcode.cn/problems/keyboard-row/description/
 * 500. 键盘行
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。
 * 键盘如下图所示。
 * 美式键盘 中：
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 */
public class Solution {

    public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>();
        String rowIndex = "12210111011122000010020202";
        for (String word : words) {
            boolean isValid = true;
            char index = rowIndex.charAt(Character.toLowerCase(word.charAt(0)) - 'a');
            for (int i = 1; i < word.length(); i++) {
                if (rowIndex.charAt(Character.toLowerCase(word.charAt(i)) - 'a') != index) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                list.add(word);
            }
        }
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
