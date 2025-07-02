package com.sean.leetcode.LeetCode3304;

/**
 * @Author xionghaiyang
 * @Date 2025-07-03 05:21
 * @Description https://leetcode.cn/problems/find-the-k-th-character-in-string-game-i
 * 3304. 找出第 K 个字符 I
 * Alice 和 Bob 正在玩一个游戏。
 * 最初，Alice 有一个字符串 word = "a"。
 * 给定一个正整数 k。
 * 现在 Bob 会要求 Alice 执行以下操作 无限次 :
 * 将 word 中的每个字符 更改 为英文字母表中的 下一个 字符来生成一个新字符串，并将其 追加 到原始的 word。
 * 例如，对 "c" 进行操作生成 "cd"，对 "zb" 进行操作生成 "zbac"。
 * 在执行足够多的操作后， word 中 至少 存在 k 个字符，此时返回 word 中第 k 个字符的值。
 * 注意，在操作中字符 'z' 可以变成 'a'。
 * 1 <= k <= 500
 */
public class Solution {

    public char kthCharacter(int k) {
        return process(k);
    }

    private char getNext(char c) {
        return c < 'z' ? (char) (c + 1) : 'a';
    }

    private char process(int k) {
        if (k == 1) {
            return 'a';
        }
        if ((k & (k - 1)) == 0) {
            return getNext(process(k >> 1));
        }
        int i = k;
        while ((i & (i - 1)) != 0) {
            i &= (i - 1);
        }
        return getNext(process(k - i));
    }

}
