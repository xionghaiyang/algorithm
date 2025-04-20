package com.sean.leetcode.LeetCode781;

/**
 * @Author xionghaiyang
 * @Date 2025-04-20 14:15
 * @Description https://leetcode.cn/problems/rabbits-in-forest
 * 781. 森林中的兔子
 * 森林中有未知数量的兔子。
 * 提问其中若干只兔子 "还有多少只兔子与你（指被提问的兔子）颜色相同?" ，
 * 将答案收集到一个整数数组 answers 中，其中 answers[i] 是第 i 只兔子的回答。
 * 给你数组 answers ，返回森林中兔子的最少数量。
 * 1 <= answers.length <= 1000
 * 0 <= answers[i] < 1000
 */
public class Solution {

    public int numRabbits(int[] answers) {
        int[] cnt = new int[1001];
        int res = 0;
        for (int answer : answers) {
            if (cnt[answer] == 0) {
                res += (answer + 1);
            }
            cnt[answer]++;
            if (cnt[answer] > answer) {
                cnt[answer] = 0;
            }
        }
        return res;
    }

}
