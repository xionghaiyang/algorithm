package com.sean.leetcode.LeetCode2274;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-01-06 20:40
 * @Description https://leetcode.cn/problems/maximum-consecutive-floors-without-special-floors/
 * 2274. 不含特殊楼层的最大连续楼层数
 * Alice 管理着一家公司，并租用大楼的部分楼层作为办公空间。
 * Alice 决定将一些楼层作为 特殊楼层 ，仅用于放松。
 * 给你两个整数 bottom 和 top ，表示 Alice 租用了从 bottom 到 top（含 bottom 和 top 在内）的所有楼层。
 * 另给你一个整数数组 special ，其中 special[i] 表示  Alice 指定用于放松的特殊楼层。
 * 返回不含特殊楼层的 最大 连续楼层数。
 * 1 <= special.length <= 10^5
 * 1 <= bottom <= special[i] <= top <= 10^9
 * special 中的所有值 互不相同
 */
public class Solution {

    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int n = special.length;
        int res = 0;
        res = Math.max(res, special[0] - bottom);
        for (int i = 1; i < n; i++) {
            res = Math.max(res, special[i] - special[i - 1] - 1);
        }
        res = Math.max(res, top - special[n - 1]);
        return res;
    }

}
