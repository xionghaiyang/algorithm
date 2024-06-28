package com.sean.leetcode.LeetCode100307;

/**
 * @Author xionghaiyang
 * @Date 2024-06-02 20:06
 * @Description https://leetcode.cn/problems/minimum-number-of-chairs-in-a-waiting-room/
 * 100307. 候诊室中的最少椅子数
 * 给你一个字符串 s，模拟每秒钟的事件 i：
 * 如果 s[i] == 'E'，表示有一位顾客进入候诊室并占用一把椅子。
 * 如果 s[i] == 'L'，表示有一位顾客离开候诊室，从而释放一把椅子。
 * 返回保证每位进入候诊室的顾客都能有椅子坐的 最少 椅子数，假设候诊室最初是 空的 。
 */
public class Solution {

    public int minimumChairs(String s) {
        int n = s.length();
        int res = 0;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'E') {
                cur++;
                res = Math.max(res, cur);
            } else {
                cur--;
            }
        }
        return res;
    }

}
