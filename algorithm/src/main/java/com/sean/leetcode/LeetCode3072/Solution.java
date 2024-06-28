package com.sean.leetcode.LeetCode3072;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-06-06 07:38
 * @Description https://leetcode.cn/problems/distribute-elements-into-two-arrays-ii
 * 3072. 将元素分配到两个数组中 II
 * 给你一个下标从 1 开始、长度为 n 的整数数组 nums 。
 * 现定义函数 greaterCount ，使得 greaterCount(arr, val) 返回数组 arr 中 严格大于 val 的元素数量。
 * 你需要使用 n 次操作，将 nums 的所有元素分配到两个数组 arr1 和 arr2 中。
 * 在第一次操作中，将 nums[1] 追加到 arr1 。
 * 在第二次操作中，将 nums[2] 追加到 arr2 。
 * 之后，在第 i 次操作中：
 * 如果 greaterCount(arr1, nums[i]) > greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr1 。
 * 如果 greaterCount(arr1, nums[i]) < greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr2 。
 * 如果 greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i]) ，将 nums[i] 追加到元素数量较少的数组中。
 * 如果仍然相等，那么将 nums[i] 追加到 arr1 。
 * 连接数组 arr1 和 arr2 形成数组 result 。
 * 例如，如果 arr1 == [1,2,3] 且 arr2 == [4,5,6] ，那么 result = [1,2,3,4,5,6] 。
 * 返回整数数组 result 。
 */
public class Solution {

    class BinaryIndexedTree {
        private int[] tree;

        public BinaryIndexedTree(int n) {
            tree = new int[n + 1];
        }

        public void add(int i) {
            while (i < tree.length) {
                tree[i]++;
                i += i & -i;
            }
        }

        public int get(int i) {
            int res = 0;
            while (i > 0) {
                res += tree[i];
                i -= i & -i;
            }
            return res;
        }
    }

    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] sortedNums = Arrays.copyOf(nums, n);
        Arrays.sort(sortedNums);
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(sortedNums[i], i + 1);
        }
        List<Integer> arr1 = new ArrayList<>();
        arr1.add(nums[0]);
        List<Integer> arr2 = new ArrayList<>();
        arr2.add(nums[1]);
        BinaryIndexedTree tree1 = new BinaryIndexedTree(n);
        tree1.add(index.get(nums[0]));
        BinaryIndexedTree tree2 = new BinaryIndexedTree(n);
        tree2.add(index.get(nums[1]));
        for (int i = 2; i < n; i++) {
            int count1 = arr1.size() - tree1.get(index.get(nums[i]));
            int count2 = arr2.size() - tree2.get(index.get(nums[i]));
            if (count1 > count2 || (count1 == count2 && arr1.size() <= arr2.size())) {
                arr1.add(nums[i]);
                tree1.add(index.get(nums[i]));
            } else {
                arr2.add(nums[i]);
                tree2.add(index.get(nums[i]));
            }
        }
        int[] res = new int[n];
        int i = 0;
        for (int num : arr1) {
            res[i++] = num;
        }
        for (int num : arr2) {
            res[i++] = num;
        }
        return res;
    }

}
