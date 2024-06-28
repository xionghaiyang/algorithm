package com.sean.leetcode.LeetCode768;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2022-08-13 13:30
 * @Description https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/
 * 768. 最多能完成排序的块 II
 * 这个问题和“最多能完成排序的块”相似，但给定数组中的元素可以重复，输入数组最大长度为2000，其中的元素最大为10**8。
 * arr是一个可能包含重复元素的整数数组，我们将这个数组分割成几个“块”，并将这些块分别进行排序。
 * 之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
 * 我们最多能将数组分成多少块？
 */
public class Solution {

    public int maxChunksToSorted1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int[] sortedArr = new int[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);
        Arrays.sort(sortedArr);
        for (int i = 0; i < sortedArr.length; i++) {
            int x = arr[i], y = sortedArr[i];
            map.put(x, map.getOrDefault(x, 0) + 1);
            if (map.get(x) == 0) {
                map.remove(x);
            }
            map.put(y, map.getOrDefault(y, 0) - 1);
            if (map.get(y) == 0) {
                map.remove(y);
            }
            if (map.isEmpty()) {
                res++;
            }
        }
        return res;
    }

    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num : arr) {
            if (stack.isEmpty() || num >= stack.peek()) {
                stack.push(num);
            } else {
                int max = stack.pop();
                while (!stack.isEmpty() && stack.peek() > num) {
                    stack.pop();
                }
                stack.push(max);
            }
        }
        return stack.size();
    }

}
