package com.sean.leetcode;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/3/30 14:50
 * @Description: https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests/
 * 找到处理最多请求的服务器
 * @version: 1.0
 */
public class LeetCode1606 {

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] requests = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                available.add(busy.poll()[1]);
            }
            if (available.isEmpty()) {
                continue;
            }
            Integer p = available.ceiling(i % k);
            if (p == null) {
                p = available.first();
            }
            requests[p]++;
            busy.offer(new int[]{arrival[i] + load[i], p});
            available.remove(p);
        }
        int maxRequest = Arrays.stream(requests).max().getAsInt();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == maxRequest) {
                res.add(i);
            }
        }
        return res;
    }

}
