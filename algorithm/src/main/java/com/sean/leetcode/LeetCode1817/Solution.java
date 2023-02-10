package com.sean.leetcode.LeetCode1817;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-28 10:04
 * @Description: https://leetcode.cn/problems/finding-the-users-active-minutes/
 * 1817. 查找用户活跃分钟数
 * 给你用户在 LeetCode 的操作日志，和一个整数 k 。
 * 日志用一个二维整数数组 logs 表示，其中每个 logs[i] = [IDi, timei] 表示 ID 为 IDi 的用户在 timei 分钟时执行了某个操作。
 * 多个用户 可以同时执行操作，单个用户可以在同一分钟内执行 多个操作 。
 * 指定用户的 用户活跃分钟数（user active minutes，UAM） 定义为用户对 LeetCode 执行操作的 唯一分钟数 。
 * 即使一分钟内执行多个操作，也只能按一分钟计数。
 * 请你统计用户活跃分钟数的分布情况，统计结果是一个长度为 k 且 下标从 1 开始计数 的数组 answer ，
 * 对于每个 j（1 <= j <= k），answer[j] 表示 用户活跃分钟数 等于 j 的用户数。
 * 返回上面描述的答案数组 answer 。
 */
public class Solution {

    public int[] findingUsersActiveMinutes1(int[][] logs, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int[] log : logs) {
            int id = log[0];
            int time = log[1];
            String info = id + "_" + time;
            if (!set.contains(info)) {
                map.put(id, map.getOrDefault(id, 0) + 1);
                set.add(info);
            }
        }
        int[] answer = new int[k];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (value >= 1 && value <= k) {
                answer[value - 1]++;
            }
        }
        return answer;
    }

    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] log : logs) {
            int id = log[0];
            int time = log[1];
            if (!map.containsKey(id)) {
                map.put(id, new HashSet<>());
            }
            map.get(id).add(time);
        }
        int[] answer = new int[k];
        for (Map.Entry<Integer, HashSet<Integer>> entry : map.entrySet()) {
            int value = entry.getValue().size();
            if (value >= 1 && value <= k) {
                answer[value - 1]++;
            }
        }
        return answer;
    }

}
