package com.sean.leetcode.LeetCode1079;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-19 08:26
 * @Description: https://leetcode.cn/problems/letter-tile-possibilities/
 * 1079. 活字印刷
 * 你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。
 * 返回你可以印出的非空字母序列的数目。
 * 注意：本题中，每个活字字模只能使用一次。
 */
public class Solution {

    public int numTilePossibilities1(String tiles) {
        if (tiles == null || tiles.length() == 0) {
            return 0;
        }
        int n = tiles.length();
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char c = tiles.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            set.add(c);
        }
        return dfs(n, map, set) - 1;
    }

    private int dfs(int i, Map<Character, Integer> map, Set<Character> set) {
        if (i == 0) {
            return 1;
        }
        int res = 1;
        for (Character c : set) {
            if (map.get(c) > 0) {
                map.put(c, map.get(c) - 1);
                res += dfs(i - 1, map, set);
                map.put(c, map.get(c) + 1);
            }
        }
        return res;
    }

    public int numTilePossibilities(String tiles) {
        if (tiles == null || tiles.length() == 0) {
            return 0;
        }
        int n = tiles.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            char c = tiles.charAt(i);
            count[c - 'A']++;
        }
        return process(n, count) - 1;
    }

    private int process(int i, int[] count) {
        if (i == 0) {
            return 1;
        }
        int res = 1;
        for (int j = 0; j < 26; j++) {
            if (count[j] > 0) {
                count[j]--;
                res += process(i - 1, count);
                count[j]++;
            }
        }
        return res;
    }

}
