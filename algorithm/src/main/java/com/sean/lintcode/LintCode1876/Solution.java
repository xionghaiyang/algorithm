package com.sean.lintcode.LintCode1876;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-11 11:50
 * @Description: https://www.lintcode.com/problem/1876/?showListFe=true&page=1&pageSize=50
 * 1876 · 外星人字典（简单）
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。
 * 字母表的顺序（order）是一些小写字母的排列。
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，
 * 只有当给定的单词在这种外星语中按字典序排列时，返回 true；
 * 否则，返回 false。
 */
public class Solution {

    public boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < order.length(); i++) {
            index[order.charAt(i) - 'a'] = i;
        }
        if (words == null || words.length < 2) {
            return true;
        }
        int n = words.length;
        for (int i = 1; i < n; i++) {
            if (!compare(words[i - 1], words[i], index)) {
                return false;
            }
        }
        return true;
    }

    private boolean compare(String first, String second, int[] index) {
        int i = 0;
        while (i < first.length() && i < second.length()) {
            if (index[first.charAt(i) - 'a'] == index[second.charAt(i) - 'a']) {
                i++;
            } else {
                break;
            }
        }
        if (i < first.length() && i == second.length()) {
            return false;
        }
        if (i < first.length() && i < second.length() && index[first.charAt(i) - 'a'] > index[second.charAt(i) - 'a']) {
            return false;
        }
        return true;
    }

}
