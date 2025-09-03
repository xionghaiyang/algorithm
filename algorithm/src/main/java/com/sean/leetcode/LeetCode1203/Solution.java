package com.sean.leetcode.LeetCode1203;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-03 14:21
 * @Description https://leetcode.cn/problems/sort-items-by-groups-respecting-dependencies
 * 1203. 项目管理
 * 有 n 个项目，每个项目或者不属于任何小组，或者属于 m 个小组之一。
 * group[i] 表示第 i 个项目所属的小组，如果第 i 个项目不属于任何小组，则 group[i] 等于 -1。
 * 项目和小组都是从零开始编号的。
 * 可能存在小组不负责任何项目，即没有任何项目属于这个小组。
 * 请你帮忙按要求安排这些项目的进度，并返回排序后的项目列表：
 * 同一小组的项目，排序后在列表中彼此相邻。
 * 项目之间存在一定的依赖关系，我们用一个列表 beforeItems 来表示，其中 beforeItems[i] 表示在进行第 i 个项目前（位于第 i 个项目左侧）应该完成的所有项目。
 * 如果存在多个解决方案，只需要返回其中任意一个即可。
 * 如果没有合适的解决方案，就请返回一个 空列表 。
 * 1 <= m <= n <= 3 * 10^4
 * group.length == beforeItems.length == n
 * -1 <= group[i] <= m - 1
 * 0 <= beforeItems[i].length <= n - 1
 * 0 <= beforeItems[i][j] <= n - 1
 * i != beforeItems[i][j]
 * beforeItems[i] 不含重复元素
 */
public class Solution {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        List<List<Integer>> groupItem = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < n + m; i++) {
            groupItem.add(new ArrayList<>());
            groupGraph.add(new ArrayList<>());
            items.add(i);
        }
        List<List<Integer>> itemGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }
        int[] groupDegree = new int[n + m];
        int[] itemDegree = new int[n];
        for (int i = 0, j = m; i < n; i++) {
            if (group[i] == -1) {
                group[i] = j++;
            }
            groupItem.get(group[i]).add(i);
        }
        for (int i = 0; i < n; i++) {
            int curGroupId = group[i];
            for (int item : beforeItems.get(i)) {
                int beforeGroupId = group[item];
                if (beforeGroupId == curGroupId) {
                    itemDegree[i]++;
                    itemGraph.get(item).add(i);
                } else {
                    groupDegree[curGroupId]++;
                    groupGraph.get(beforeGroupId).add(curGroupId);
                }
            }
        }
        List<Integer> groupTopSort = topSort(groupDegree, groupGraph, items);
        if (groupTopSort.size() == 0) {
            return new int[0];
        }
        int[] res = new int[n];
        int index = 0;
        for (int curGroupId : groupTopSort) {
            int size = groupItem.get(curGroupId).size();
            if (size == 0) {
                continue;
            }
            List<Integer> itemTopSort = topSort(itemDegree, itemGraph, groupItem.get(curGroupId));
            if (itemTopSort.size() == 0) {
                return new int[0];
            }
            for (int item : itemTopSort) {
                res[index++] = item;
            }
        }
        return res;
    }

    private List<Integer> topSort(int[] degree, List<List<Integer>> graph, List<Integer> items) {
        Queue<Integer> queue = new LinkedList<>();
        for (int item : items) {
            if (degree[item] == 0) {
                queue.offer(item);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            res.add(u);
            for (int v : graph.get(u)) {
                if (--degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return res.size() == items.size() ? res : new ArrayList<>();
    }

}
