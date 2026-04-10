package com.sean.leetcode.LeetCode914;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2026-04-10 13:10
 * @Description https://leetcode.cn/problems/x-of-a-kind-in-a-deck-of-cards
 * 914. 卡牌分组
 * 给定一副牌，每张牌上都写着一个整数。
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 * 1 <= deck.length <= 10^4
 * 0 <= deck[i] < 10^4
 */
public class Solution {

    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : deck) {
            map.merge(num, 1, Integer::sum);
        }
        int x = -1;
        for (int value : map.values()) {
            if (x == -1) {
                x = value;
            } else {
                x = gcd(x, value);
            }
        }
        return x >= 2;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

}
