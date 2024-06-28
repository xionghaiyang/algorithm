package com.sean.leetcode.LeetCode1307;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-30 14:19
 * @Description: https://leetcode.cn/problems/verbal-arithmetic-puzzle/description/
 * 1307. 口算难题
 * 给你一个方程，左边用 words 表示，右边用 result 表示。
 * 你需要根据以下规则检查方程是否可解：
 * 每个字符都会被解码成一位数字（0 - 9）。
 * 每对不同的字符必须映射到不同的数字。
 * 每个 words[i] 和 result 都会被解码成一个没有前导零的数字。
 * 左侧数字之和（words）等于右侧数字（result）。
 * 如果方程可解，返回 True，否则返回 False。
 */
public class Solution {

    int[] c2n = new int[26];
    int[] n2c = new int[10];
    boolean[] not0 = new boolean[26];//不为0的字母

    public boolean isSolvable(String[] words, String result) {
        int max = 0;
        for (String word : words) {
            //记录不能为前导零的字母
            if (word.length() > 1) {
                not0[word.charAt(0) - 'A'] = true;
            }
            max = Math.max(max, word.length());
            //如果存在word长度大于result长度，无解
            if (word.length() > result.length()) {
                return false;
            }
        }
        //最大word长度+1小于result长度，无解
        if (max + 1 < result.length()) {
            return false;
        }
        //记录不能为前导零的字母
        if (result.length() > 1) {
            not0[result.charAt(0) - 'A'] = true;
        }
        Arrays.fill(c2n, -1);
        Arrays.fill(n2c, -1);
        return dfs(words, result, 0, 0, 0);
    }

    //index为当前遍历到的word索引，pos为已经校验和的倒数索引，x为进位
    private boolean dfs(String[] words, String result, int index, int pos, int x) {
        //遍历到result第一位之前，若进位为0，恰好找到解
        if (pos == result.length()) {
            return x == 0;
        }
        //遍历完一轮words
        if (index == words.length) {
            //所有word倒数pos位置的数字和
            int sum = x;
            for (String word : words) {
                //如果word长度不足则直接跳过
                if (word.length() > pos) {
                    sum += c2n[word.charAt(word.length() - 1 - pos) - 'A'];
                }
            }
            int num = sum % 10;
            char c = result.charAt(result.length() - 1 - pos);
            //result倒数pos位置的字母已经有映射，则必须等于num
            if (c2n[c - 'A'] != -1) {
                if (c2n[c - 'A'] == num) {
                    //index回到0,pos向前进一个，进位更新为sum/10
                    return dfs(words, result, 0, pos + 1, sum / 10);
                }
                return false;
            } else {
                //如果num已经被其他字母映射，无解
                if (n2c[num] != -1) {
                    return false;
                }
                //result倒数pos位置的字母映射为num
                c2n[c - 'A'] = num;
                n2c[num] = c;
                boolean check = dfs(words, result, 0, pos + 1, sum / 10);
                if (check) {
                    return true;
                }
                n2c[num] = -1;
                c2n[c - 'A'] = -1;
                return false;
            }
        } else {//在某一轮words的遍历中
            String word = words[index];
            //当前word长度不足，index向前进一个
            if (word.length() <= pos) {
                return dfs(words, result, index + 1, pos, x);
            } else {
                char c = word.charAt(word.length() - 1 - pos);
                //word在倒数pos位置已经有映射
                if (c2n[c - 'A'] != -1) {
                    return dfs(words, result, index + 1, pos, x);
                } else {
                    //如果该位置字母不能为0，尝试在1-9中为其分配
                    if (not0[c - 'A']) {
                        for (int i = 1; i < 10; i++) {
                            if (n2c[i] == -1) {
                                n2c[i] = c;
                                c2n[c - 'A'] = i;
                                //wordIndex前进一个,pos不变
                                boolean check = dfs(words, result, index + 1, pos, x);
                                if (check) {
                                    return true;
                                }
                                c2n[c - 'A'] = -1;
                                n2c[i] = -1;
                            }
                        }
                    } else {
                        //在0-9中为其分配
                        for (int i = 0; i < 10; i++) {
                            if (n2c[i] == -1) {
                                n2c[i] = c;
                                c2n[c - 'A'] = i;
                                boolean check = dfs(words, result, index + 1, pos, x);
                                if (check) {
                                    return true;
                                }
                                c2n[c - 'A'] = -1;
                                n2c[i] = -1;
                            }
                        }
                    }
                }
            }
        }
        //所有方案都不满足
        return false;
    }

}
