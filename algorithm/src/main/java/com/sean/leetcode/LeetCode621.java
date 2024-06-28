package com.sean.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LeetCode621 {

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        //最多的执行次数
        int maxExec = 0;
        for (char task : tasks) {
            int exec = map.getOrDefault(task, 0) + 1;
            map.put(task, exec);
            maxExec = Math.max(maxExec, exec);
        }
        //具有最多执行任务次数的任务数量
        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        for (Map.Entry<Character, Integer> entry : entries) {
            int value = entry.getValue();
            if (value == maxExec) {
                maxCount++;
            }
        }
        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }

}
