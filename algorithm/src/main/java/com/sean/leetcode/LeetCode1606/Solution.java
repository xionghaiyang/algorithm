package com.sean.leetcode.LeetCode1606;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-09 06:36
 * @Description: https://leetcode.cn/problems/find-servers-that-handled-most-number-of-requests
 * 1606. 找到处理最多请求的服务器
 * 你有 k 个服务器，编号为 0 到 k-1 ，它们可以同时处理多个请求组。
 * 每个服务器有无穷的计算能力但是 不能同时处理超过一个请求 。
 * 请求分配到服务器的规则如下：
 * 第 i （序号从 0 开始）个请求到达。
 * 如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。
 * 如果第 (i % k) 个服务器空闲，那么对应服务器会处理该请求。
 * 否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。
 * 比方说，如果第 i 个服务器在忙，那么会查看第 (i+1) 个服务器，第 (i+2) 个服务器等等。
 * 给你一个 严格递增 的正整数数组 arrival ，表示第 i 个任务的到达时间，和另一个数组 load ，其中 load[i] 表示第 i 个请求的工作量（也就是服务器完成它所需要的时间）。
 * 你的任务是找到 最繁忙的服务器 。
 * 最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。
 * 请你返回包含所有 最繁忙服务器 序号的列表，你可以以任意顺序返回这个列表。
 * 1 <= k <= 10^5
 * 1 <= arrival.length, load.length <= 10^5
 * arrival.length == load.length
 * 1 <= arrival[i], load[i] <= 10^9
 * arrival 保证 严格递增 。
 */
public class Solution {

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        if (k >= n) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                res.add(i);
            }
            return res;
        }
        PriorityQueue<Integer> idle = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            idle.offer(i);
        }
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] cnt = new int[k];
        int maxCnt = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int j = busy.poll()[1];
                //j 在[i,i+k)中的同余值
                int v = i + (j - i % k + k) % k;
                idle.offer(v);
            }
            if (idle.isEmpty()) {
                continue;
            }
            int j = idle.poll() % k;
            busy.offer(new int[]{arrival[i] + load[i], j});
            cnt[j]++;
            if (cnt[j] > maxCnt) {
                maxCnt = cnt[j];
                res.clear();
                res.add(j);
            } else if (cnt[j] == maxCnt) {
                res.add(j);
            }
        }
        return res;
    }

}
