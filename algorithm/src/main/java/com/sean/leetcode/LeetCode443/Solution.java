package com.sean.leetcode.LeetCode443;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-29 16:44
 * @Description: https://leetcode.cn/problems/string-compression/?envType=study-plan-v2&envId=leetcode-75
 * 443. 压缩字符串
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 * 如果这一组长度为 1 ，则将字符追加到 s 中。
 * 否则，需要向 s 追加字符，后跟这一组的长度。
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。
 * 需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 * 请在 修改完输入数组后 ，返回该数组的新长度。
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 */
public class Solution {

    public int compress(char[] chars) {
        int n = chars.length;
        int write = 0;
        int left = 0;
        for (int read = 0; read < n; read++) {
            if (read == n - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[read];
                int count = read - left + 1;
                if (count > 1) {
                    int start = write;
                    while (count > 0) {
                        chars[write++] = (char) (count % 10 + '0');
                        count /= 10;
                    }
                    reverse(chars, start, write - 1);
                }
                left = read + 1;
            }
        }
        return write;
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char t = chars[left];
            chars[left] = chars[right];
            chars[right] = t;
            left++;
            right--;
        }
    }

}
