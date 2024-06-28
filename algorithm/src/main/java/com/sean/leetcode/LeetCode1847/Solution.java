package com.sean.leetcode.LeetCode1847;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-27 11:10
 * @Description: https://leetcode.cn/problems/closest-room/description/
 * 1847. 最近的房间
 * 一个酒店里有 n 个房间，这些房间用二维整数数组 rooms 表示，其中 rooms[i] = [roomIdi, sizei]
 * 表示有一个房间号为 roomIdi 的房间且它的面积为 sizei 。
 * 每一个房间号 roomIdi 保证是 独一无二 的。
 * 同时给你 k 个查询，用二维数组 queries 表示，其中 queries[j] = [preferredj, minSizej] 。
 * 第 j 个查询的答案是满足如下条件的房间 id ：
 * 房间的面积 至少 为 minSizej ，且
 * abs(id - preferredj) 的值 最小 ，其中 abs(x) 是 x 的绝对值。
 * 如果差的绝对值有 相等 的，选择 最小 的 id 。
 * 如果 没有满足条件的房间 ，答案为 -1 。
 * 请你返回长度为 k 的数组 answer ，其中 answer[j] 为第 j 个查询的结果。
 */
public class Solution {

    public int[] closestRoom(int[][] rooms, int[][] queries) {
        //按房间大小排序
        Arrays.sort(rooms, (a, b) -> b[1] - a[1]);
        List<int[]> list = new ArrayList<>();
        int m = rooms.length;
        int n = queries.length;
        for (int i = 0; i < n; i++) {
            list.add(new int[]{i, queries[i][0], queries[i][1]});
        }
        Collections.sort(list, (a, b) -> b[2] - a[2]);
        TreeSet<Integer> roomIds = new TreeSet<>();
        int[] res = new int[n];
        int id = 0;
        for (int[] query : list) {
            int pos = query[0];
            int preferred = query[1];
            int minSize = query[2];
            while (id < m && rooms[id][1] >= minSize) {
                roomIds.add(rooms[id][0]);
                id++;
            }
            //获取请求结果
            if (roomIds.isEmpty()) {
                res[pos] = -1;
            } else {
                Integer ceiling = roomIds.ceiling(preferred);
                Integer floor = roomIds.floor(preferred);
                if (ceiling == null) {
                    res[pos] = floor;
                } else if (floor == null) {
                    res[pos] = ceiling;
                } else {
                    res[pos] = preferred - floor <= ceiling - preferred ? floor : ceiling;
                }
            }
        }
        return res;
    }

}
