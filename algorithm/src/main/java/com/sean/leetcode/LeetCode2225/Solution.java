package com.sean.leetcode.LeetCode2225;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-05-22 09:22
 * @Description https://leetcode.cn/problems/find-players-with-zero-or-one-losses/
 * 2225. 找出输掉零场或一场比赛的玩家
 * 给你一个整数数组 matches 其中 matches[i] = [winneri, loseri] 表示在一场比赛中 winneri 击败了 loseri 。
 * 返回一个长度为 2 的列表 answer ：
 * answer[0] 是所有 没有 输掉任何比赛的玩家列表。
 * answer[1] 是所有恰好输掉 一场 比赛的玩家列表。
 * 两个列表中的值都应该按 递增 顺序返回。
 * 注意：
 * 只考虑那些参与 至少一场 比赛的玩家。
 * 生成的测试用例保证 不存在 两场比赛结果 相同 。
 */
public class Solution {

    public List<List<Integer>> findWinners(int[][] matches) {
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];
            if (!map.containsKey(winner)) {
                set.add(winner);
            }
            if (set.contains(loser)) {
                set.remove(loser);
            }
            map.put(loser, map.getOrDefault(loser, 0) + 1);
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> res0 = new ArrayList<>(set);
        Collections.sort(res0);
        res.add(res0);
        List<Integer> res1 = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (value == 1) {
                res1.add(entry.getKey());
            }
        }
        Collections.sort(res1);
        res.add(res1);
        return res;
    }

    public List<List<Integer>> findWinners1(int[][] matches) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];
            map.putIfAbsent(winner, 0);
            map.put(loser, map.getOrDefault(loser, 0) + 1);
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < 2; i++) {
            res.add(new ArrayList<>());
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (value < 2) {
                res.get(value).add(entry.getKey());
            }
        }
        Collections.sort(res.get(0));
        Collections.sort(res.get(1));
        return res;
    }

}
