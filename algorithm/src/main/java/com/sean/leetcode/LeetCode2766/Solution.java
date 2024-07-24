package com.sean.leetcode.LeetCode2766;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-07-24 08:03
 * @Description https://leetcode.cn/problems/relocate-marbles/
 * 2766. 重新放置石块
 * 给你一个下标从 0 开始的整数数组 nums ，表示一些石块的初始位置。
 * 再给你两个长度 相等 下标从 0 开始的整数数组 moveFrom 和 moveTo 。
 * 在 moveFrom.length 次操作内，你可以改变石块的位置。
 * 在第 i 次操作中，你将位置在 moveFrom[i] 的所有石块移到位置 moveTo[i] 。
 * 完成这些操作后，请你按升序返回所有 有 石块的位置。
 * 注意：
 * 如果一个位置至少有一个石块，我们称这个位置 有 石块。
 * 一个位置可能会有多个石块。
 */
public class Solution {

    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int n = moveFrom.length;
        for (int i = 0; i < n; i++) {
            int from = moveFrom[i];
            int to = moveTo[i];
            if (set.contains(from)) {
                set.remove(from);
                set.add(to);
            }
        }
        List<Integer> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }

}
