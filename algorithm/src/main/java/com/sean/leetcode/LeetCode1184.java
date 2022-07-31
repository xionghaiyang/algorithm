package com.sean.leetcode;

/**
 * @Author xionghaiyang
 * @Date 2022/7/24 8:38
 */
public class LeetCode1184 {

    /**
     * https://leetcode.cn/problems/distance-between-bus-stops/
     * 环形公交路线上有 n 个站，按次序从 0 到 n - 1 进行编号。
     * 我们已知每一对相邻公交站之间的距离，distance[i] 表示编号为 i 的车站和编号为 (i + 1) % n 的车站之间的距离。
     * 环线上的公交车都可以按顺时针和逆时针的方向行驶。
     * 返回乘客从出发点 start 到目的地 destination 之间的最短距离。
     */

    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start == destination) {
            return 0;
        }
        if (start > destination) {
            return distanceBetweenBusStops(distance, destination, start);
        }
        int res1 = 0, res2 = 0, n = distance.length;
        for (int i = 0; i < n; i++) {
            if (i >= start && i < destination) {
                res1 += distance[i];
            } else {
                res2 += distance[i];
            }
        }
        return Math.min(res1, res2);
    }

}
