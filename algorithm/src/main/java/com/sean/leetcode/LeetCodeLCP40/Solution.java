package com.sean.leetcode.LeetCodeLCP40;

/**
 * @Author xionghaiyang
 * @Date 2024-08-03 11:09
 * @Description https://leetcode.cn/problems/uOAnQW/description/
 * LCP 40. 心算挑战
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，
 * 则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
 * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。
 * 请帮参赛选手计算最大的有效得分。
 * 若不存在获取有效得分的卡牌方案，则返回 0。
 * 1 <= cnt <= cards.length <= 10^5
 * 1 <= cards[i] <= 1000
 */
public class Solution {

    public int maxmiumScore(int[] cards, int cnt) {
        int[] val = new int[1001];
        int n = cards.length;
        for (int i = 0; i < n; i++) {
            val[cards[i]]++;
        }
        int res = 0;
        int tmp = 0;
        int ed = -1;
        int odd = -1, even = -1;
        for (int i = 1000; i >= 1; i--) {
            if (val[i] == 0) {
                continue;
            }
            if (val[i] > cnt) {
                tmp += cnt * i;
                cnt = 0;
            } else {
                tmp += val[i] * i;
                cnt -= val[i];
                val[i] = 0;
            }
            if ((i & 1) != 0) {
                odd = i;
            } else {
                even = i;
            }
            if (cnt == 0) {
                if (val[i] > 0) {
                    ed = i;
                } else {
                    ed = i - 1;
                }
                break;
            }
        }
        if ((tmp & 1) == 0) {
            return tmp;
        }
        for (int i = ed; i >= 1; i--) {
            if (val[i] == 0) {
                continue;
            }
            if ((i & 1) != 0) {
                if (even != -1) {
                    res = Math.max(res, tmp - even + i);
                }
            } else {
                if (odd != -1) {
                    res = Math.max(res, tmp - odd + i);
                }
            }
        }
        return res;
    }

}
