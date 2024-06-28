package com.sean.leetcode.LeetCode383;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-23 19:42
 * @Description: https://leetcode.cn/problems/ransom-note/?envType=study-plan-v2&envId=top-interview-150
 * 383. 赎金信
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 */
public class Solution {

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] cnt = new int[26];
        int n = magazine.length();
        for (int i = 0; i < n; i++) {
            cnt[magazine.charAt(i) - 'a']++;
        }
        int m = ransomNote.length();
        for (int i = 0; i < m; i++) {
            cnt[ransomNote.charAt(i) - 'a']--;
            if (cnt[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
