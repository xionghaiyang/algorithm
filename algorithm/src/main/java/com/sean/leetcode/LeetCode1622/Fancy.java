package com.sean.leetcode.LeetCode1622;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-03-15 08:00
 * @Description https://leetcode.cn/problems/fancy-sequence
 * 1622. 奇妙序列
 * 请你实现三个 API append，addAll 和 multAll 来实现奇妙序列。
 * 请实现 Fancy 类 ：
 * Fancy() 初始化一个空序列对象。
 * void append(val) 将整数 val 添加在序列末尾。
 * void addAll(inc) 将所有序列中的现有数值都增加 inc 。
 * void multAll(m) 将序列中的所有现有数值都乘以整数 m 。
 * int getIndex(idx) 得到下标为 idx 处的数值（下标从 0 开始），并将结果对 10^9 + 7 取余。
 * 如果下标大于等于序列的长度，请返回 -1 。
 * 1 <= val, inc, m <= 100
 * 0 <= idx <= 10^5
 * 总共最多会有 10^5 次对 append，addAll，multAll 和 getIndex 的调用。
 */
public class Fancy {

    private static final int MOD = 1_000_000_007;
    private List<Integer> list;
    private long add = 0;
    private long mul = 1;

    public Fancy() {
        list = new ArrayList<>();
    }

    public void append(int val) {
        list.add((int) (((val - add + MOD) * pow(mul, MOD - 2)) % MOD));
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= list.size()) {
            return -1;
        }
        return (int) (((list.get(idx) * mul) % MOD + add) % MOD);
    }

    //x^n
    private long pow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = (res * x) % MOD;
            }
            x = (x * x) % MOD;
            n >>= 1;
        }
        return res;
    }
}
