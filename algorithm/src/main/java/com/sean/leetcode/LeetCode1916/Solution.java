package com.sean.leetcode.LeetCode1916;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-18 06:22
 * @Description: https://leetcode.cn/problems/count-ways-to-build-rooms-in-an-ant-colony
 * 1916. 统计为蚁群构筑房间的不同顺序
 * 你是一只蚂蚁，负责为蚁群构筑 n 间编号从 0 到 n-1 的新房间。
 * 给你一个 下标从 0 开始 且长度为 n 的整数数组 prevRoom 作为扩建计划。
 * 其中，prevRoom[i] 表示在构筑房间 i 之前，你必须先构筑房间 prevRoom[i] ，并且这两个房间必须 直接 相连。
 * 房间 0 已经构筑完成，所以 prevRoom[0] = -1 。
 * 扩建计划中还有一条硬性要求，在完成所有房间的构筑之后，从房间 0 可以访问到每个房间。
 * 你一次只能构筑 一个 房间。
 * 你可以在 已经构筑好的 房间之间自由穿行，只要这些房间是 相连的 。
 * 如果房间 prevRoom[i] 已经构筑完成，那么你就可以构筑房间 i。
 * 返回你构筑所有房间的 不同顺序的数目 。
 * 由于答案可能很大，请返回对 10^9 + 7 取余 的结果。
 * n == prevRoom.length
 * 2 <= n <= 10^5
 * prevRoom[0] == -1
 * 对于所有的 1 <= i < n ，都有 0 <= prevRoom[i] < n
 * 题目保证所有房间都构筑完成后，从房间 0 可以访问到每个房间
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private long prod = 1;

    public int waysToBuildRooms(int[] prevRoom) {
        int n = prevRoom.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        long fac = 1;
        for (int i = 1; i < n; i++) {
            fac = fac * (i + 1) % MOD;
            g[prevRoom[i]].add(i);
        }
        dfs(g, 0);
        return (int) (fac * pow(prod, MOD - 2) % MOD);
    }

    private int dfs(List<Integer>[] g, int x) {
        int size = 1;
        for (int y : g[x]) {
            size += dfs(g, y);
        }
        prod = prod * size % MOD;
        return size;
    }

    //x^n
    private long pow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }

}
