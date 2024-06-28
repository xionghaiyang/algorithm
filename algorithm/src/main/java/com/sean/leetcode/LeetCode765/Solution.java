package com.sean.leetcode.LeetCode765;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-11-11 10:04
 * @Description: https://leetcode.cn/problems/couples-holding-hands/
 * 765. 情侣牵手
 * n 对情侣坐在连续排列的 2n 个座位上，想要牵到对方的手。
 * 人和座位由一个整数数组 row 表示，其中 row[i] 是坐在第 i 个座位上的人的 ID。
 * 情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2n-2, 2n-1)。
 * 返回 最少交换座位的次数，以便每对情侣可以并肩坐在一起。
 * 每次交换可选择任意两人，让他们站起来交换座位。
 */
public class Solution {

    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int total = n / 2;
        List<Integer>[] graph = new List[total];
        for (int i = 0; i < total; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i += 2) {
            int l = row[i] / 2;
            int r = row[i + 1] / 2;
            if (l != r) {
                graph[l].add(r);
                graph[r].add(l);
            }
        }
        boolean[] visited = new boolean[total];
        int res = 0;
        for (int i = 0; i < total; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new LinkedList<>();
                visited[i] = true;
                queue.offer(i);
                int cnt = 0;
                while (!queue.isEmpty()) {
                    int x = queue.poll();
                    cnt++;
                    for (int y : graph[x]) {
                        if (!visited[y]) {
                            visited[y] = true;
                            queue.offer(y);
                        }
                    }
                }
                res += cnt - 1;
            }
        }
        return res;
    }

}
