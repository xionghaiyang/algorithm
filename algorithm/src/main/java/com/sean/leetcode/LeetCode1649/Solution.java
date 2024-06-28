package com.sean.leetcode.LeetCode1649;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-01 14:13
 * @Description: https://leetcode.cn/problems/create-sorted-array-through-instructions/description/
 * 1649. 通过指令创建有序数组
 * 给你一个整数数组 instructions ，你需要根据 instructions 中的元素创建一个有序数组。
 * 一开始你有一个空的数组 nums ，你需要 从左到右 遍历 instructions 中的元素，将它们依次插入 nums 数组中。
 * 每一次插入操作的 代价 是以下两者的 较小值 ：
 * nums 中 严格小于  instructions[i] 的数字数目。
 * nums 中 严格大于  instructions[i] 的数字数目。
 * 比方说，如果要将 3 插入到 nums = [1,2,3,5] ，那么插入操作的 代价 为 min(2, 1)
 * (元素 1 和  2 小于 3 ，元素 5 大于 3 ），插入后 nums 变成 [1,2,3,3,5] 。
 * 请你返回将 instructions 中所有元素依次插入 nums 后的 总最小代价 。
 * 由于答案会很大，请将它对 10^9 + 7 取余 后返回。
 */
public class Solution {

    //使用树状数组，每次求小于他的值与大于他的值中的较小值
    class arrTrie {
        private int[] arr;
        private int n;

        public arrTrie(int n) {
            this.n = n;
            arr = new int[n];
        }

        private int lowBit(int x) {
            return x & (-x);
        }

        public int query(int x) {
            int res = 0;
            for (int i = x; i > 0; i -= lowBit(i)) {
                res += arr[i];
            }
            return res;
        }

        public void update(int x, int add) {
            for (int i = x; i < n; i += lowBit(i)) {
                arr[i] += add;
            }
        }
    }

    public int createSortedArray(int[] instructions) {
        int mod = 1000000007;
        //先进行离散化
        Map<Integer, Integer> map = new HashMap<>();
        TreeSet<Integer> set = new TreeSet<>();
        for (int instruction : instructions) {
            set.add(instruction);
        }
        int index = 1;
        while (!set.isEmpty()) {
            map.put(set.pollFirst(), index++);
        }
        arrTrie tree = new arrTrie(map.size() + 1);
        int res = 0;
        for (int i = 0; i < instructions.length; i++) {
            int l = map.get(instructions[i]);
            int less = tree.query(l - 1);
            int more = i - tree.query(l);
            int temp = Math.min(less, more);
            res = res % mod + temp % mod;
            tree.update(l, 1);
        }
        return res;
    }

}
