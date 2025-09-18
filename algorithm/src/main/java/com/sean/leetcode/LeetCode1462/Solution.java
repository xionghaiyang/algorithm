package com.sean.leetcode.LeetCode1462;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-18 18:03
 * @Description https://leetcode.cn/problems/course-schedule-iv
 * 1462. 课程表 IV
 * 你总共需要上 numCourses 门课，课程编号依次为 0 到 numCourses-1 。
 * 你会得到一个数组 prerequisite ，其中 prerequisites[i] = [ai, bi] 表示如果你想选 bi 课程，你 必须 先选 ai 课程。
 * 有的课会有直接的先修课程，比如如果想上课程 1 ，你必须先上课程 0 ，那么会以 [0,1] 数对的形式给出先修课程数对。
 * 先决条件也可以是 间接 的。
 * 如果课程 a 是课程 b 的先决条件，课程 b 是课程 c 的先决条件，那么课程 a 就是课程 c 的先决条件。
 * 你也得到一个数组 queries ，其中 queries[j] = [uj, vj]。
 * 对于第 j 个查询，您应该回答课程 uj 是否是课程 vj 的先决条件。
 * 返回一个布尔数组 answer ，其中 answer[j] 是第 j 个查询的答案。
 * 2 <= numCourses <= 100
 * 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
 * prerequisites[i].length == 2
 * 0 <= ai, bi <= numCourses - 1
 * ai != bi
 * 每一对 [ai, bi] 都 不同
 * 先修课程图中没有环。
 * 1 <= queries.length <= 10^4
 * 0 <= ui, vi <= numCourses - 1
 * ui != vi
 */
public class Solution {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] g = new ArrayList[numCourses];
        Arrays.setAll(g, e -> new ArrayList<>());
        int[] indgree = new int[numCourses];
        boolean[][] isPre = new boolean[numCourses][numCourses];
        for (int[] prerequisite : prerequisites) {
            indgree[prerequisite[1]]++;
            g[prerequisite[0]].add(prerequisite[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indgree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : g[u]) {
                isPre[u][v] = true;
                for (int i = 0; i < numCourses; i++) {
                    isPre[i][v] |= isPre[i][u];
                }
                if (--indgree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(isPre[query[0]][query[1]]);
        }
        return res;
    }

}
