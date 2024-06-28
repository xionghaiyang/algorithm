package com.sean.leetcode.LeetCode2092;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-26 15:50
 * @Description: https://leetcode.cn/problems/find-all-people-with-secret/description/
 * 2092. 找出知晓秘密的所有专家
 * 给你一个整数 n ，表示有 n 个专家从 0 到 n - 1 编号。
 * 另外给你一个下标从 0 开始的二维整数数组 meetings ，
 * 其中 meetings[i] = [xi, yi, timei] 表示专家 xi 和专家 yi 在时间 timei 要开一场会。
 * 一个专家可以同时参加 多场会议 。
 * 最后，给你一个整数 firstPerson 。
 * 专家 0 有一个 秘密 ，最初，他在时间 0 将这个秘密分享给了专家 firstPerson 。
 * 接着，这个秘密会在每次有知晓这个秘密的专家参加会议时进行传播。
 * 更正式的表达是，每次会议，如果专家 xi 在时间 timei 时知晓这个秘密，那么他将会与专家 yi 分享这个秘密，反之亦然。
 * 秘密共享是 瞬时发生 的。
 * 也就是说，在同一时间，一个专家不光可以接收到秘密，还能在其他会议上与其他专家分享。
 * 在所有会议都结束之后，返回所有知晓这个秘密的专家列表。
 * 你可以按 任何顺序 返回答案。
 */
public class Solution {

    //并查集数组，记录每个元素的祖先节点
    private int[] parent;

    //查找每个元素的祖先，（路径压缩）
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        parent = new int[n + 1];
        //祖先数组初始化，将每个元素的祖先标记为自己
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        //合并0号专家与firstPerson
        parent[firstPerson] = 0;
        Map<Integer, List<int[]>> map = new TreeMap<>();
        //构造以时刻为key，会议列表为value的map,TreeMap将自动按照key升序排序
        for (int[] meeting : meetings) {
            List<int[]> list = map.getOrDefault(meeting[2], new ArrayList<>());
            list.add(new int[]{meeting[0], meeting[1]});
            map.put(meeting[2], list);
        }
        for (int time : map.keySet()) {
            for (int[] arr : map.get(time)) {
                int x = arr[0];
                int y = arr[1];
                if (parent[find(x)] == 0 || parent[find(y)] == 0) {
                    parent[find(x)] = 0;
                    parent[find(y)] = 0;
                }
                parent[find(y)] = parent[find(x)];
            }
            for (int[] arr : map.get(time)) {
                int x = arr[0];
                int y = arr[1];
                //场景一:两位专家在前面的会议均不知道秘密，后面遍历中其中议围专家知道了秘密，瞬时共享，两人都将知道秘密
                if (parent[find(x)] == 0 || parent[find(y)] == 0) {
                    parent[find(x)] = 0;
                    parent[find(y)] = 0;
                } else {//场景二：两位专家在该时刻始终都不知道秘密，将合并的集合分离开，防止后面时刻有一个专家知道秘密，将秘密分享给另一个专家
                    parent[x] = x;
                    parent[y] = y;
                }
            }

        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (parent[find(i)] == 0) {
                res.add(i);
            }
        }
        return res;
    }

}
