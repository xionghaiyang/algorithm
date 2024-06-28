package com.sean.leetcode.LeetCode1010;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-08 08:41
 * @Description: https://leetcode.cn/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 * 1010. 总持续时间可被 60 整除的歌曲
 * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
 * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。
 * 形式上，我们希望下标数字 i 和 j 满足  i < j 且有 (time[i] + time[j]) % 60 == 0。
 */
public class Solution {

    public int numPairsDivisibleBy60(int[] time) {
        int n = time.length;
        int[] cnt = new int[60];
        for (int i = 0; i < n; i++) {
            cnt[time[i] % 60]++;
        }
        int res = 0;
        for (int i = 1; i < 30; i++) {
            res += cnt[i] * cnt[60 - i];
        }
        res += (long) cnt[0] * (cnt[0] - 1) / 2 + (long) cnt[30] * (cnt[30] - 1) / 2;
        return res;
    }

}
