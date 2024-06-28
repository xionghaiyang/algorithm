package com.sean.leetcode.LeetCode2451;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-25 08:08
 * @Description: https://leetcode.cn/problems/odd-string-difference/
 * 2451. 差值数组不同的字符串
 * 给你一个字符串数组 words ，每一个字符串长度都相同，令所有字符串的长度都为 n 。
 * 每个字符串 words[i] 可以被转化为一个长度为 n - 1 的 差值整数数组 difference[i] ，
 * 其中对于 0 <= j <= n - 2 有 difference[i][j] = words[i][j+1] - words[i][j] 。
 * 注意两个字母的差值定义为它们在字母表中 位置 之差，也就是说 'a' 的位置是 0 ，'b' 的位置是 1 ，'z' 的位置是 25 。
 * 比方说，字符串 "acb" 的差值整数数组是 [2 - 0, 1 - 2] = [2, -1] 。
 * words 中所有字符串 除了一个字符串以外 ，其他字符串的差值整数数组都相同。
 * 你需要找到那个不同的字符串。
 * 请你返回 words中 差值整数数组 不同的字符串。
 */
public class Solution {

    public String oddString(String[] words) {
        int[] diff0 = get(words[0]);
        int[] diff1 = get(words[1]);
        if (Arrays.equals(diff0, diff1)) {
            for (int i = 2; i < words.length; i++) {
                if (!Arrays.equals(diff0, get(words[i]))) {
                    return words[i];
                }
            }
        }
        return Arrays.equals(diff0, get(words[2])) ? words[1] : words[0];
    }

    private int[] get(String word) {
        int n = word.length();
        int[] res = new int[n - 1];
        for (int i = 0; i + 1 < n; i++) {
            res[i] = word.charAt(i + 1) - word.charAt(i);
        }
        return res;
    }

}
