package com.sean.leetcode.LeetCodeLCP33;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-22 14:25
 * @Description: https://leetcode.cn/problems/o8SXZn/
 * LCP 33. 蓄水
 * 给定 N 个无限容量且初始均空的水缸，每个水缸配有一个水桶用来打水，第 i 个水缸配备的水桶容量记作 bucket[i]。
 * 小扣有以下两种操作：
 * 升级水桶：选择任意一个水桶，使其容量增加为 bucket[i]+1
 * 蓄水：将全部水桶接满水，倒入各自对应的水缸
 * 每个水缸对应最低蓄水量记作 vat[i]，返回小扣至少需要多少次操作可以完成所有水缸蓄水要求。
 * 注意：实际蓄水量 达到或超过 最低蓄水量，即完成蓄水要求。
 */
public class Solution {

    public int storeWater1(int[] bucket, int[] vat) {
        int n = bucket.length;
        int maxK = Arrays.stream(vat).max().getAsInt();
        if (maxK == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int k = 1; k <= maxK && k < res; k++) {
            int t = 0;
            for (int i = 0; i < n; i++) {
                t += Math.max(0, (vat[i] + k - 1) / k - bucket[i]);
            }
            res = Math.min(res, t + k);
        }
        return res;
    }

    public int storeWater(int[] bucket, int[] vat) {
        int n = bucket.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (bucket[i] == 0 && vat[i] != 0) {
                cnt++;
                bucket[i]++;
            }
            if (vat[i] > 0) {
                pq.offer(new int[]{(vat[i] + bucket[i] - 1) / bucket[i], i});
            }
        }
        if (pq.isEmpty()) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        while (cnt < res) {
            int[] arr = pq.poll();
            int v = arr[0];
            int i = arr[1];
            res = Math.min(res, cnt + v);
            if (v == 1) {
                break;
            }
            int t = (vat[i] + v - 2) / (v - 1);
            cnt += t - bucket[i];
            bucket[i] = t;
            pq.offer(new int[]{(vat[i] + bucket[i] - 1) / bucket[i], i});
        }
        return res;
    }

}
