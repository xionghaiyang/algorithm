package com.sean.leetcode.LeetCode1419;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-06 08:09
 * @Description: https://leetcode.cn/problems/minimum-number-of-frogs-croaking/
 * 1419. 数青蛙
 * 给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。
 * 由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。
 * 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
 * 要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。
 * 如果没有输出全部五个字母，那么它就不会发出声音。
 * 如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。
 */
public class Solution {

    public int minNumberOfFrogs1(String croakOfFrogs) {
        int n = croakOfFrogs.length();
        if (n % 5 != 0) {
            return -1;
        }
        Queue<Integer> c = new LinkedList<>();
        Queue<Integer> r = new LinkedList<>();
        Queue<Integer> o = new LinkedList<>();
        Queue<Integer> a = new LinkedList<>();
        Queue<Integer> k = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            char character = croakOfFrogs.charAt(i);
            if (character == 'c') {
                if (k.isEmpty()) {
                    //如果没有k，则需要新的青蛙
                    res++;
                    c.add(res);
                } else {
                    //如果有k,则不需要新的青蛙
                    c.add(k.poll());
                }
            } else if (character == 'r' && !c.isEmpty()) {
                r.add(c.poll());
            } else if (character == 'o' && !r.isEmpty()) {
                o.add(r.poll());
            } else if (character == 'a' && !o.isEmpty()) {
                a.add(o.poll());
            } else if (character == 'k' && !a.isEmpty()) {
                k.add(a.poll());
            } else {
                return -1;
            }
        }
        return k.size() == res ? res : -1;
    }

    public int minNumberOfFrogs(String croakOfFrogs) {
        int n = croakOfFrogs.length();
        if (n % 5 != 0) {
            return -1;
        }
        int res = 0;
        int frogNum = 0;
        int[] cnt = new int[4];
        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('c', 0);
            put('r', 1);
            put('o', 2);
            put('a', 3);
            put('k', 4);
        }};
        for (int i = 0; i < n; i++) {
            char c = croakOfFrogs.charAt(i);
            int t = map.get(c);
            if (t == 0) {
                cnt[t]++;
                frogNum++;
                if (frogNum > res) {
                    res = frogNum;
                }
            } else {
                if (cnt[t - 1] == 0) {
                    return -1;
                }
                cnt[t - 1]--;
                if (t == 4) {
                    frogNum--;
                } else {
                    cnt[t]++;
                }
            }
        }
        if (frogNum > 0) {
            return -1;
        }
        return res;
    }

}
