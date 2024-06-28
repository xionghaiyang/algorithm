package com.sean.leetcode.LeetCode1157;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-17 08:35
 * @Description: https://leetcode.cn/problems/online-majority-element-in-subarray/
 * 1157. 子数组中占绝大多数的元素
 * 设计一个数据结构，有效地找到给定子数组的 多数元素 。
 * 子数组的 多数元素 是在子数组中出现 threshold 次数或次数以上的元素。
 * 实现 MajorityChecker 类:
 * MajorityChecker(int[] arr) 会用给定的数组 arr 对 MajorityChecker 初始化。
 * int query(int left, int right, int threshold) 返回子数组中的元素  arr[left...right] 至少出现 threshold 次数，如果不存在这样的元素则返回 -1。
 */
public class MajorityChecker {

    public static final int K = 20;
    private int[] arr;
    private Map<Integer, List<Integer>> loc;
    private Random random;

    public MajorityChecker(int[] arr) {
        this.arr = arr;
        this.loc = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            loc.putIfAbsent(arr[i], new ArrayList<>());
            loc.get(arr[i]).add(i);
        }
        this.random = new Random();
    }

    public int query(int left, int right, int threshold) {
        int length = right - left + 1;
        for (int i = 0; i < K; i++) {
            int x = arr[left + random.nextInt(length)];
            List<Integer> pos = loc.get(x);
            int occ = searchEnd(pos, right) - searchStart(pos, left);
            if (occ >= threshold) {
                return x;
            } else if (occ * 2 >= length) {
                return -1;
            }
        }
        return -1;
    }

    private int searchStart(List<Integer> pos, int target) {
        int low = 0, high = pos.size();
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (pos.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int searchEnd(List<Integer> pos, int target) {
        int low = 0, high = pos.size();
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (pos.get(mid) > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}
