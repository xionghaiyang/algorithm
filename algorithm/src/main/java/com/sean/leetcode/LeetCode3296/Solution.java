package com.sean.leetcode.LeetCode3296;

import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2026-03-13 06:57
 * @Description https://leetcode.cn/problems/minimum-number-of-seconds-to-make-mountain-height-zero
 * 3296. 移山所需的最少秒数
 * 给你一个整数 mountainHeight 表示山的高度。
 * 同时给你一个整数数组 workerTimes，表示工人们的工作时间（单位：秒）。
 * 工人们需要 同时 进行工作以 降低 山的高度。
 * 对于工人 i :
 * 山的高度降低 x，需要花费 workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x 秒。
 * 例如：
 * 山的高度降低 1，需要 workerTimes[i] 秒。
 * 山的高度降低 2，需要 workerTimes[i] + workerTimes[i] * 2 秒，依此类推。
 * 返回一个整数，表示工人们使山的高度降低到 0 所需的 最少 秒数。
 * 1 <= mountainHeight <= 10^5
 * 1 <= workerTimes.length <= 10^4
 * 1 <= workerTimes[i] <= 10^6
 */
public class Solution {

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        for (int workerTime : workerTimes) {
            heap.offer(new long[]{workerTime, 1, workerTime});
        }
        long res = 0;
        while (mountainHeight > 0) {
            long[] worker = heap.poll();
            res = worker[0];
            worker[1]++;
            worker[0] += worker[2] * worker[1];
            heap.offer(worker);
            mountainHeight--;
        }
        return res;
    }

}
