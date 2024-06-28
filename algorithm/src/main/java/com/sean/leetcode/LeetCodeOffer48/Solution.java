package com.sean.leetcode.LeetCodeOffer48;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-28 22:12
 * @Description: https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 */
public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int left = -1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (map.getOrDefault(c, -1) > left) {
                left = map.get(c);
            } else {
                res = Math.max(res, i - left);
            }
            map.put(c, i);
        }
        return res;
    }

}
