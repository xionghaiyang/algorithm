package com.sean.lintcode.LintCode1038;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-19 11:45
 * @Description: https://www.lintcode.com/problem/1038/?showListFe=true&page=1&pageSize=50
 * 1038 · 珠宝和石头
 * 给定字符串J代表是珠宝的石头类型，而S代表你拥有的石头.
 * S中的每个字符都是你拥有的一个石头. 你想知道你的石头有多少是珠宝.
 * J中的字母一定不同，J和S中的字符都是字母。
 * 字母区分大小写，因此"a"和"A"是不同的类型.
 */
public class Solution {

    public int numJewelsInStones(String j, String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < j.length(); i++) {
            set.add(j.charAt(i));
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                res++;
            }
        }
        return res;
    }

}
