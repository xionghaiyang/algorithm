package com.sean.leetcode.LeetCode744;

/**
 * @Author xionghaiyang
 * @Date 2025-09-28 20:45
 * @Description https://leetcode.cn/problems/find-smallest-letter-greater-than-target
 * 744. 寻找比目标字母大的最小字母
 * 给你一个字符数组 letters，该数组按非递减顺序排序，以及一个字符 target。
 * letters 里至少有两个不同的字符。
 * 返回 letters 中大于 target 的最小的字符。
 * 如果不存在这样的字符，则返回 letters 的第一个字符。
 * 2 <= letters.length <= 10^4
 * letters[i] 是一个小写字母
 * letters 按非递减顺序排序
 * letters 最少包含两个不同的字母
 * target 是一个小写字母
 */
public class Solution {

    public char nextGreatestLetter(char[] letters, char target) {
        char res = letters[0];
        for (char letter : letters) {
            if (letter > target) {
                res = letter;
                break;
            }
        }
        return res;
    }

    public char nextGreatestLetter1(char[] letters, char target) {
        int n = letters.length;
        if (target >= letters[n - 1]) {
            return letters[0];
        }
        int res = 0, left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (letters[mid] > target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return letters[res];
    }

}
