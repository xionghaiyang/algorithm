package com.sean.leetcode.LeetCode2747;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-06 06:23
 * @Description: https://leetcode.cn/problems/count-zero-request-servers
 * 2747. 统计没有收到请求的服务器数目
 * 给你一个整数 n ，表示服务器的总数目，再给你一个下标从 0 开始的 二维 整数数组 logs ，其中 logs[i] = [server_id, time] 表示 id 为 server_id 的服务器在 time 时收到了一个请求。
 * 同时给你一个整数 x 和一个下标从 0 开始的整数数组 queries  。
 * 请你返回一个长度等于 queries.length 的数组 arr ，其中 arr[i] 表示在时间区间 [queries[i] - x, queries[i]] 内没有收到请求的服务器数目。
 * 注意时间区间是个闭区间。
 * 1 <= n <= 10^5
 * 1 <= logs.length <= 10^5
 * 1 <= queries.length <= 10^5
 * logs[i].length == 2
 * 1 <= logs[i][0] <= n
 * 1 <= logs[i][1] <= 10^6
 * 1 <= x <= 10^5
 * x < queries[i] <= 10^6
 */
public class Solution {

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);
        Integer[] index = new Integer[queries.length];
        Arrays.setAll(index, i -> i);
        Arrays.sort(index, (i, j) -> queries[i] - queries[j]);
        int[] cnt = new int[n + 1];
        int left = 0, right = 0, notReceives = n;
        for (int id : index) {
            int cur = queries[id];
            while (right < logs.length && logs[right][1] <= cur) {
                if (++cnt[logs[right][0]] == 1) {
                    notReceives--;
                }
                right++;
            }
            while (left < logs.length && logs[left][1] < cur - x) {
                if (--cnt[logs[left][0]] == 0) {
                    notReceives++;
                }
                left++;
            }
            queries[id] = notReceives;
        }
        return queries;
    }

}
