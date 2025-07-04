package com.sean.leetcode.LeetCode3307;

/**
 * @Author xionghaiyang
 * @Date 2025-07-04 06:54
 * @Description https://leetcode.cn/problems/find-the-k-th-character-in-string-game-ii
 * 3307. 找出第 K 个字符 II
 * Alice 和 Bob 正在玩一个游戏。
 * 最初，Alice 有一个字符串 word = "a"。
 * 给定一个正整数 k 和一个整数数组 operations，其中 operations[i] 表示第 i 次操作的类型。
 * 现在 Bob 将要求 Alice 按顺序执行 所有 操作：
 * 如果 operations[i] == 0，将 word 的一份 副本追加 到它自身。
 * 如果 operations[i] == 1，将 word 中的每个字符 更改 为英文字母表中的 下一个 字符来生成一个新字符串，并将其 追加 到原始的 word。
 * 例如，对 "c" 进行操作生成 "cd"，对 "zb" 进行操作生成 "zbac"。
 * 在执行所有操作后，返回 word 中第 k 个字符的值。
 * 注意，在第二种类型的操作中，字符 'z' 可以变成 'a'。
 * 1 <= k <= 10^14
 * 1 <= operations.length <= 100
 * operations[i] 可以是 0 或 1。
 * 输入保证在执行所有操作后，word 至少有 k 个字符。
 */
public class Solution {

    public char kthCharacter(long k, int[] operations) {
        return process(k, operations);
    }

    private char getNext(char c) {
        return c < 'z' ? (char) (c + 1) : 'a';
    }

    private int getStep(long k) {
        k--;
        int res = -1;
        while (k > 0) {
            k >>= 1;
            res++;
        }
        return res;
    }

    private char process(long k, int[] operations) {
        if (k == 1) {
            return 'a';
        }
        boolean flag = operations[getStep(k)] == 0;
        if ((k & (k - 1)) == 0) {
            char res = process(k >> 1, operations);
            return flag ? res : getNext(res);
        }
        long i = k;
        while ((i & (i - 1)) != 0) {
            i &= (i - 1);
        }
        char res = process(k - i, operations);
        return flag ? res : getNext(res);
    }

}
