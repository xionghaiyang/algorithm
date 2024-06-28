package com.sean.leetcode.LeetCode2732;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-06-25 08:57
 * @Description https://leetcode.cn/problems/find-a-good-subset-of-the-matrix/
 * 2732. 找到矩阵中的好子集
 * 给你一个下标从 0 开始大小为 m x n 的二进制矩阵 grid 。
 * 从原矩阵中选出若干行构成一个行的 非空 子集，如果子集中任何一列的和至多为子集大小的一半，那么我们称这个子集是 好子集。
 * 更正式的，如果选出来的行子集大小（即行的数量）为 k，那么每一列的和至多为 floor(k / 2) 。
 * 请你返回一个整数数组，它包含好子集的行下标，请你将子集中的元素 升序 返回。
 * 如果有多个好子集，你可以返回任意一个。如果没有好子集，请你返回一个空数组。
 * 一个矩阵 grid 的行 子集 ，是删除 grid 中某些（也可能不删除）行后，剩余行构成的元素集合。
 */
public class Solution {

    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            int st = 0;
            for (int j = 0; j < n; j++) {
                st |= (grid[i][j] << j);
            }
            map.put(st, i);
        }
        if (map.containsKey(0)) {
            res.add(map.get(0));
            return res;
        }
        for (Map.Entry<Integer, Integer> entry1 : map.entrySet()) {
            int x = entry1.getKey(), i = entry1.getValue();
            for (Map.Entry<Integer, Integer> entry2 : map.entrySet()) {
                int y = entry2.getKey(), j = entry2.getValue();
                if ((x & y) == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(Math.min(i, j));
                    list.add(Math.max(i, j));
                    return list;
                }
            }
        }
        return res;
    }

}
