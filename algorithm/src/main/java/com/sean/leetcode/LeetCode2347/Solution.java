package com.sean.leetcode.LeetCode2347;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-20 08:15
 * @Description: https://leetcode.cn/problems/best-poker-hand/
 * 2347. 最好的扑克手牌
 * 给你一个整数数组 ranks 和一个字符数组 suit 。你有 5 张扑克牌，第 i 张牌大小为 ranks[i] ，花色为 suits[i] 。
 * 下述是从好到坏你可能持有的 手牌类型 ：
 * "Flush"：同花，五张相同花色的扑克牌。
 * "Three of a Kind"：三条，有 3 张大小相同的扑克牌。
 * "Pair"：对子，两张大小一样的扑克牌。
 * "High Card"：高牌，五张大小互不相同的扑克牌。
 * 请你返回一个字符串，表示给定的 5 张牌中，你能组成的 最好手牌类型 。
 * 注意：返回的字符串 大小写 需与题目描述相同。
 */
public class Solution {

    public String bestHand(int[] ranks, char[] suits) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            set.add(suits[i]);
        }
        if (set.size() == 1) {
            return "Flush";
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(ranks[i], map.getOrDefault(ranks[i], 0) + 1);
            if (map.get(ranks[i]) == 3) {
                return "Three of a Kind";
            }
        }
        if (map.size() == 5) {
            return "High Card";
        } else {
            return "Pair";
        }
    }

}
