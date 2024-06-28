package com.sean.leetcode.LeetCode784;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-01 08:24
 * @Description: https://leetcode.cn/problems/letter-case-permutation/
 * 784. 字母大小写全排列
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 * 返回 所有可能得到的字符串集合 。
 * 以 任意顺序 返回输出。
 */
public class Solution {

    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        Queue<StringBuilder> queue = new LinkedList<>();
        queue.offer(new StringBuilder());
        while (!queue.isEmpty()) {
            StringBuilder cur = queue.peek();
            if (cur.length() == s.length()) {
                res.add(cur.toString());
                queue.poll();
            } else {
                int pos = cur.length();
                if (Character.isLetter(s.charAt(pos))) {
                    StringBuilder next = new StringBuilder(cur);
                    next.append((char) (s.charAt(pos) ^ 32));
                    queue.offer(next);
                }
                cur.append(s.charAt(pos));
            }
        }
        return res;
    }

}
