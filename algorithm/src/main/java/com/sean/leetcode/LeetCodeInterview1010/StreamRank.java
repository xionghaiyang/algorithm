package com.sean.leetcode.LeetCodeInterview1010;

/**
 * @Author xionghaiyang
 * @Date 2026-03-20 12:33
 * @Description https://leetcode.cn/problems/rank-from-stream-lcci
 * 面试题 10.10. 数字流的秩
 * 假设你正在读取一串整数。
 * 每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。
 * 请实现数据结构和算法来支持这些操作，也就是说：
 * 实现 track(int x) 方法，每读入一个数字都会调用该方法；
 * 实现 getRankOfNumber(int x) 方法，返回小于或等于 x 的值的个数。
 * 注意：本题相对原题稍作改动
 * x <= 50000
 * track 和 getRankOfNumber 方法的调用次数均不超过 2000 次
 */
public class StreamRank {

    private static final int N = 50_001;
    private int[] tree;

    public StreamRank() {
        tree = new int[N + 1];
    }

    public void track(int x) {
        add(x + 1, 1);
    }

    public int getRankOfNumber(int x) {
        return query(x + 1);
    }

    private void add(int x, int v) {
        for (int i = x; i < N; i += lowbit(i)) {
            tree[i] += v;
        }
    }

    private int lowbit(int i) {
        return i & (-i);
    }

    private int query(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            res += tree[i];
        }
        return res;
    }

}
