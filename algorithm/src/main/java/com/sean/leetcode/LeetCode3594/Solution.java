package com.sean.leetcode.LeetCode3594;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-07-05 13:38
 * @Description https://leetcode.cn/problems/minimum-time-to-transport-all-individuals
 * 3594. 所有人渡河所需的最短时间
 * 有 n 名人员在一个营地，他们需要使用一艘船过河到达目的地。
 * 这艘船一次最多可以承载 k 人。
 * 渡河过程受到环境条件的影响，这些条件以 周期性 的方式在 m 个阶段内变化。
 * 每个阶段 j 都有一个速度倍率 mul[j]：
 * 如果 mul[j] > 1，渡河时间会变长。
 * 如果 mul[j] < 1，渡河时间会缩短。
 * 每个人 i 都有一个划船能力，用 time[i] 表示，即在中性条件下（倍率为 1 时）单独渡河所需的时间（以分钟为单位）。
 * 规则：
 * 从阶段 j 出发的一组人 g 渡河所需的时间（以分钟为单位）为组内成员的 最大 time[i]，乘以 mul[j] 。
 * 该组人渡河所需的时间为 d，阶段会前进 floor(d) % m 步。
 * 如果还有人留在营地，则必须有一人带着船返回。
 * 设返回人的索引为 r，返回所需时间为 time[r] × mul[current_stage]，记为 return_time，阶段会前进 floor(return_time) % m 步。
 * 返回将所有人渡河所需的 最少总时间 。
 * 如果无法将所有人渡河，则返回 -1。
 * 1 <= n == time.length <= 12
 * 1 <= k <= 5
 * 1 <= m <= 5
 * 1 <= time[i] <= 100
 * m == mul.length
 * 0.5 <= mul[i] <= 2.0
 */
public class Solution {

    public class Info {
        double d;
        int stage;
        int mask;

        public Info(double d, int stage, int mask) {
            this.d = d;
            this.stage = stage;
            this.mask = mask;
        }
    }

    public double minTime(int n, int k, int m, int[] time, double[] mul) {
        int u = 1 << n;
        //计算每个time子集的最大值
        int[] maxTime = new int[u];
        for (int i = 0; i < n; i++) {
            int hightBit = 1 << i;
            for (int mask = 0; mask < hightBit; mask++) {
                maxTime[hightBit | mask] = Math.max(maxTime[mask], time[i]);
            }
        }
        //把maxTime中的大小大于k的集合改为inf
        for (int i = 0; i < u; i++) {
            if (Integer.bitCount(i) > k) {
                maxTime[i] = Integer.MAX_VALUE;
            }
        }
        double[][] dis = new double[m][u];
        for (double[] row : dis) {
            Arrays.fill(row, Double.MAX_VALUE);
        }
        PriorityQueue<Info> heap = new PriorityQueue<>((a, b) -> Double.compare(a.d, b.d));
        push(0, 0, u - 1, dis, heap);
        while (!heap.isEmpty()) {
            Info info = heap.poll();
            double d = info.d;
            int stage = info.stage;
            //剩余没有过河的人
            int left = info.mask;
            //所有人都过河了
            if (left == 0) {
                return d;
            }
            if (d > dis[stage][left]) {
                continue;
            }
            //枚举sub这群人坐一艘船
            for (int sub = left; sub > 0; sub = (sub - 1) & left) {
                if (maxTime[sub] == Integer.MAX_VALUE) {
                    continue;
                }
                //sub过河
                double cost = maxTime[sub] * mul[stage];
                //过河后的阶段
                int curStage = (stage + (int) cost) % m;
                //所有人都过河了
                if (sub == left) {
                    push(d + cost, curStage, 0, dis, heap);
                    continue;
                }
                //枚举回来的人（可以是之前过河的人）
                for (int s = (u - 1) ^ left ^ sub, lb = 0; s > 0; s ^= lb) {
                    lb = s & -s;
                    double returnTime = maxTime[lb] * mul[curStage];
                    push(d + cost + returnTime, (curStage + (int) returnTime) % m, left ^ sub ^ lb, dis, heap);
                }
            }
        }
        return -1;
    }

    private void push(double d, int stage, int mask, double[][] dis, PriorityQueue<Info> heap) {
        if (d < dis[stage][mask]) {
            dis[stage][mask] = d;
            heap.offer(new Info(d, stage, mask));
        }
    }

}
