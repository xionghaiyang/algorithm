package com.sean.leetcode.LeetCode2418;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-25 08:09
 * @Description: https://leetcode.cn/problems/sort-the-people/
 * 2418. 按身高排序
 * 给你一个字符串数组 names ，和一个由 互不相同 的正整数组成的数组 heights 。
 * 两个数组的长度均为 n 。
 * 对于每个下标 i，names[i] 和 heights[i] 表示第 i 个人的名字和身高。
 * 请按身高 降序 顺序返回对应的名字数组 names 。
 */
public class Solution {

    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        Arrays.sort(index, (a, b) -> heights[b] - heights[a]);
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            res[i] = names[index[i]];
        }
        return res;
    }

}
