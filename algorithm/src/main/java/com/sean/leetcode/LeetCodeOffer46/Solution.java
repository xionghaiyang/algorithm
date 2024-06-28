package com.sean.leetcode.LeetCodeOffer46;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-28 16:41
 * @Description: https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。
 * 请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 */
public class Solution {

    public int translateNum(int num) {
        List<Integer> list = new ArrayList<>();
        if (num == 0) {
            list.add(0);
        }
        while (num != 0) {
            list.add(num % 10);
            num /= 10;
        }
        Collections.reverse(list);
        int n = list.size();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            num = list.get(i - 2) * 10 + list.get(i - 1);
            dp[i] = dp[i - 1] + ((num >= 10 && num <= 25) ? dp[i - 2] : 0);
        }
        return dp[n];
    }

}
