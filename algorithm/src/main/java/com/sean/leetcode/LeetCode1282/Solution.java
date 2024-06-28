package com.sean.leetcode.LeetCode1282;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-12 11:31
 * @Description: https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/
 * 1282. 用户分组
 * 有 n 个人被分成数量未知的组。每个人都被标记为一个从 0 到 n - 1 的唯一ID 。
 * 给定一个整数数组 groupSizes ，其中 groupSizes[i] 是第 i 个人所在的组的大小。
 * 例如，如果 groupSizes[1] = 3 ，则第 1 个人必须位于大小为 3 的组中。
 * 返回一个组列表，使每个人 i 都在一个大小为 groupSizes[i] 的组中。
 * 每个人应该 恰好只 出现在 一个组 中，并且每个人必须在一个组中。
 * 如果有多个答案，返回其中 任何 一个。可以 保证 给定输入 至少有一个 有效的解。
 */
public class Solution {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
//            if (map.containsKey(groupSizes[i])) {
//                map.get(groupSizes[i]).add(i);
//            } else {
//                map.put(groupSizes[i], new ArrayList<>());
//                map.get(groupSizes[i]).add(i);
//            }
            map.putIfAbsent(groupSizes[i], new ArrayList<>());
            map.get(groupSizes[i]).add(i);
        }
        List<List<Integer>> res = new ArrayList<>();
//        Iterator<Integer> iterator = map.keySet().iterator();
//        while (iterator.hasNext()) {
//            int key = iterator.next();
//            List<Integer> list = map.get(key);
//            int n = list.size() / key;
//            for (int i = 1; i <= n; i++) {
//                List<Integer> temp = new ArrayList<>();
//                for (int j = (i - 1) * key; j < i * key; j++) {
//                    temp.add(list.get(j));
//                }
//                res.add(temp);
//            }
//        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            List<Integer> list = entry.getValue();
            int n = list.size() / key;
            for (int i = 1; i <= n; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = (i - 1) * key; j < i * key; j++) {
                    temp.add(list.get(j));
                }
                res.add(temp);
            }
        }
        return res;
    }

}
