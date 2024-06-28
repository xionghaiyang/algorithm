package com.sean.leetcode.LeetCode1156;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-05 08:27
 * @Description: https://leetcode.cn/problems/swap-for-longest-repeated-character-substring/
 * 1156. 单字符重复子串的最大长度
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 */
public class Solution {

    public int maxRepOpt1(String text) {
        if (text == null || text.length() == 0) {
            return 0;
        }
        int n = text.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int res = 0;
        for (int i = 0; i < n; ) {
            char c = text.charAt(i);
            //找出当前连续的一段[i,j)
            int j = i;
            while (j < n && text.charAt(j) == c) {
                j++;
            }
            int curCnt = j - i;
            //如果这一段长度小于该字符出现的总数，并且前面或后面有空位，则使用curCnt+1更新答案
            if (curCnt < map.getOrDefault(c, 0) && (j < n || i > 0)) {
                res = Math.max(res, curCnt + 1);
            }
            //找到这一段后面与之相隔一个不同字符的另一段[j+1,k),如果不存在则k=j+1
            int k = j + 1;
            while (k < n && text.charAt(k) == c) {
                k++;
            }
            res = Math.max(res, Math.min(k - i, map.getOrDefault(c, 0)));
            i = j;
        }
        return res;
    }

}
