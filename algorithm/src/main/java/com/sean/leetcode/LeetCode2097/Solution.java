package com.sean.leetcode.LeetCode2097;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-18 14:56
 * @Description: https://leetcode.cn/problems/valid-arrangement-of-pairs/
 * 2097. 合法重新排列数对
 * 给你一个下标从 0 开始的二维整数数组 pairs ，其中 pairs[i] = [starti, endi] 。
 * 如果 pairs 的一个重新排列，满足对每一个下标 i （ 1 <= i < pairs.length ）都有 endi-1 == starti ，
 * 那么我们就认为这个重新排列是 pairs 的一个 合法重新排列 。
 * 请你返回 任意一个 pairs 的合法重新排列。
 * 注意：数据保证至少存在一个 pairs 的合法重新排列。
 */
public class Solution {

    Map<Integer, List<Integer>> g;
    List<Integer> list;

    public int[][] validArrangement(int[][] pairs) {
        int n = pairs.length;
        g = new HashMap<>();
        Map<Integer, Integer> in = new HashMap<>();
        Map<Integer, Integer> out = new HashMap<>();
        for (int[] pair : pairs) {
            int start = pair[0];
            int end = pair[1];
            g.putIfAbsent(start, new ArrayList<>());
            g.get(start).add(end);
            in.put(start, in.getOrDefault(start, 0) + 1);
            out.put(end, out.getOrDefault(end, 0) + 1);
        }
        int start = -1;
        for (int key : in.keySet()) {
            if (in.get(key) - out.getOrDefault(key, 0) == 1) {
                start = key;
                break;
            }
        }
        if (start == -1) {
            start = pairs[0][0];
        }
        list = new ArrayList<>();
        dfs(start);
        int[][] res = new int[n][2];
        for (int i = n; i > 0; i--) {
            res[n - i][0] = list.get(i);
            res[n - i][1] = list.get(i - 1);
        }
        return res;
    }

    private void dfs(int start) {
        List<Integer> temp = g.getOrDefault(start, new ArrayList<>());
        while (temp.size() > 0) {
            int x = temp.remove(temp.size() - 1);
            dfs(x);
        }
        list.add(start);
    }

    //用于记录每个数字在int[0]的个数，int[1]的个数
    Map<Integer, int[]> countMap = new HashMap<>();
    //用于记录某个数字和其他数字组成int[],map的key是int[0],Queue中的元素int[1]的可能数字
    Map<Integer, Queue<Integer>> map = new HashMap<>();
    List<Integer> path = new ArrayList<>();

    public int[][] validArrangement1(int[][] pairs) {
        int n = pairs.length;
        int[][] res = new int[n][2];
        for (int[] pair : pairs) {
            int start = pair[0];
            int end = pair[1];
            countMap.putIfAbsent(start, new int[2]);
            countMap.putIfAbsent(end, new int[2]);
            countMap.get(start)[0]++;
            countMap.get(end)[1]++;
            if (!map.containsKey(start)) {
                map.put(start, new LinkedList<>());
            }
            map.get(start).offer(end);
        }
        int start = pairs[0][0];
        for (int num : countMap.keySet()) {
            int[] count = countMap.get(num);
            if (count[0] > count[1]) {
                start = num;
                break;
            }
        }
        process(start);
        for (int i = n; i > 0; i--) {
            res[n - i][0] = path.get(i);
            res[n - i][1] = path.get(i - 1);
        }
        return res;
    }

    private void process(int start) {
        while (map.containsKey(start) && map.get(start).size() > 0) {
            int end = map.get(start).poll();
            process(end);
        }
        path.add(start);
    }

}
