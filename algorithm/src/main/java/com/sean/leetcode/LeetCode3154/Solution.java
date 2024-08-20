package com.sean.leetcode.LeetCode3154;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-08-20 08:18
 * @Description https://leetcode.cn/problems/find-number-of-ways-to-reach-the-k-th-stair/
 * 3154. 到达第 K 级台阶的方案数
 * 给你有一个 非负 整数 k 。
 * 有一个无限长度的台阶，最低 一层编号为 0 。
 * Alice 有一个整数 jump ，一开始值为 0 。
 * Alice 从台阶 1 开始，可以使用 任意 次操作，目标是到达第 k 级台阶。
 * 假设 Alice 位于台阶 i ，一次 操作 中，Alice 可以：
 * 向下走一级到 i - 1 ，但该操作 不能 连续使用，如果在台阶第 0 级也不能使用。
 * 向上走到台阶 i + 2^jump 处，然后 jump 变为 jump + 1 。
 * 请你返回 Alice 到达台阶 k 处的总方案数。
 * 注意，Alice 可能到达台阶 k 处后，通过一些操作重新回到台阶 k 处，这视为不同的方案。
 * 0 <= k <= 10^9
 */
public class Solution {

    public int waysToReachStair(int k) {
        return process(1, 0, 0, k, new HashMap<>());
    }

    private int process(int i, int jump, int preDown, int k, Map<Long, Integer> map) {
        if (i > k + 1) {//无法到达终点k
            return 0;
        }
        //把状态(i,jump,preDown)压缩成一个long
        long mask = (long) i << 32 | jump << 1 | preDown;
        if (map.containsKey(mask)) {
            return map.get(mask);
        }
        int res = i == k ? 1 : 0;
        res += process(i + (1 << jump), jump + 1, 0, k, map);//操作二
        if (preDown == 0 && i > 0) {
            res += process(i - 1, jump, 1, k, map);//操作一
        }
        map.put(mask, res);
        return res;
    }

}
