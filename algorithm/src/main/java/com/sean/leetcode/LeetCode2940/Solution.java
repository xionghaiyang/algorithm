package com.sean.leetcode.LeetCode2940;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-08-10 09:08
 * @Description https://leetcode.cn/problems/find-building-where-alice-and-bob-can-meet/
 * 2940. 找到 Alice 和 Bob 可以相遇的建筑
 * 给你一个下标从 0 开始的正整数数组 heights ，其中 heights[i] 表示第 i 栋建筑的高度。
 * 如果一个人在建筑 i ，且存在 i < j 的建筑 j 满足 heights[i] < heights[j] ，那么这个人可以移动到建筑 j 。
 * 给你另外一个数组 queries ，其中 queries[i] = [ai, bi] 。
 * 第 i 个查询中，Alice 在建筑 ai ，Bob 在建筑 bi 。
 * 请你能返回一个数组 ans ，其中 ans[i] 是第 i 个查询中，Alice 和 Bob 可以相遇的 最左边的建筑 。
 * 如果对于查询 i ，Alice 和 Bob 不能相遇，令 ans[i] 为 -1 。
 * 1 <= heights.length <= 5 * 10^4
 * 1 <= heights[i] <= 10^9
 * 1 <= queries.length <= 5 * 10^4
 * queries[i] = [ai, bi]
 * 0 <= ai, bi <= heights.length - 1
 */
public class Solution {

    private int[] zd;

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        zd = new int[n * 4];
        buid(1, n, 1, heights);
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == b || heights[a] < heights[b]) {
                res[i] = b;
                continue;
            }
            res[i] = query(b + 1, heights[a], 1, n, 1) - 1;
        }
        return res;
    }

    private void buid(int left, int right, int rt, int[] heights) {
        if (left == right) {
            zd[rt] = heights[left - 1];
            return;
        }
        int mid = (left + right) >> 1;
        buid(left, mid, rt << 1, heights);
        buid(mid + 1, right, rt << 1 | 1, heights);
        zd[rt] = Math.max(zd[rt << 1], zd[rt << 1 | 1]);
    }

    private int query(int pos, int val, int left, int right, int rt) {
        if (val >= zd[rt]) {
            return 0;
        }
        if (left == right) {
            return left;
        }
        int mid = (left + right) >> 1;
        if (pos <= mid) {
            int res = query(pos, val, left, mid, rt << 1);
            if (res != 0) {
                return res;
            }
        }
        return query(pos, val, mid + 1, right, rt << 1 | 1);
    }

    public int[] leftmostBuildingQueries1(int[] heights, int[][] queries) {
        int n = heights.length;
        int m = queries.length;
        List<int[]>[] query = new List[n];
        for (int i = 0; i < n; i++) {
            query[i] = new ArrayList<>();
        }
        int[] res = new int[m];
        List<Integer> stack = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == b || heights[a] < heights[b]) {
                res[i] = b;
                continue;
            }
            query[b].add(new int[]{i, heights[a]});
        }
        int top = -1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < query[i].size(); j++) {
                int q = query[i].get(j)[0];
                int val = query[i].get(j)[1];
                if (top == -1 || heights[stack.get(0)] <= val) {
                    res[q] = -1;
                    continue;
                }
                int left = 0, right = top;
                while (left <= right) {
                    int mid = (left + right) >> 1;
                    if (heights[stack.get(mid)] > val) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                res[q] = stack.get(right);
            }
            while (top >= 0 && heights[stack.get(top)] <= heights[i]) {
                stack.remove(stack.size() - 1);
                top--;
            }
            stack.add(i);
            top++;
        }
        return res;
    }

}
