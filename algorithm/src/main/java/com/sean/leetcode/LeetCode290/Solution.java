package com.sean.leetcode.LeetCode290;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-23 20:51
 * @Description: https://leetcode.cn/problems/word-pattern/description/?envType=study-plan-v2&envId=top-interview-150
 * 290. 单词规律
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
 */
public class Solution {

    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        String[] arr = s.split(" ");
        if (pattern.length() != arr.length) {
            return false;
        }
        int n = pattern.length();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(pattern.charAt(i))) {
                if (map.containsValue(arr[i])) {
                    return false;
                }
                map.put(pattern.charAt(i), arr[i]);
            } else {
                if (!map.get(pattern.charAt(i)).equals(arr[i])) {
                    return false;
                }
            }
        }
        return true;
    }

}
