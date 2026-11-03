package com.sean.leetcode.LeetCode3505;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-04 06:20
 * @Description: https://leetcode.cn/problems/minimum-operations-to-make-elements-within-k-subarrays-equal
 * 3505. 使 K 个子数组内元素相等的最少操作数
 * 给你一个整数数组 nums 和两个整数 x 和 k。
 * 你可以执行以下操作任意次（包括零次）：
 * 将 nums 中的任意一个元素加 1 或减 1。
 * 返回为了使 nums 中 至少 包含 k 个长度 恰好 为 x 的不重叠子数组（每个子数组中的所有元素都相等）所需要的 最少 操作数。
 * 子数组 是数组中连续、非空的一段元素。
 * 2 <= nums.length <= 10^5
 * -10^6 <= nums[i] <= 10^6
 * 2 <= x <= nums.length
 * 1 <= k <= 15
 * 2 <= k * x <= nums.length
 */
public class Solution {

    public class LazyHeap extends PriorityQueue<Integer> {
        //每个元素剩余需要删除的次数
        private final Map<Integer, Integer> removeCnt = new HashMap<>();
        //实际大小
        private int size = 0;
        //堆中元素总和
        private long sum = 0;

        public LazyHeap(Comparator<Integer> comparator) {
            super(comparator);
        }

        public int size() {
            return size;
        }

        public long sum() {
            return sum;
        }

        //删除
        public void remove(int x) {
            removeCnt.merge(x, 1, Integer::sum);
            size--;
            sum -= x;
        }

        //正式执行删除操作
        private void applyRemove() {
            while (removeCnt.getOrDefault(peek(), 0) > 0) {
                removeCnt.merge(poll(), -1, Integer::sum);
            }
        }

        //查看堆顶
        public int top() {
            applyRemove();
            return peek();
        }

        //出堆
        public int pop() {
            applyRemove();
            size--;
            sum -= peek();
            return poll();
        }

        //入堆
        public void push(int x) {
            int cnt = removeCnt.getOrDefault(x, 0);
            if (cnt > 0) {
                removeCnt.put(x, cnt - 1);
            } else {
                offer(x);
            }
            size++;
            sum += x;
        }

        //push(x)然后pop()
        public int pushPop(int x) {
            applyRemove();
            sum += x;
            offer(x);
            sum -= peek();
            return poll();
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] dis = medianSlidingWindow(nums, x);
        //f[i][j]:下标区间[0,j-1]中选i个长为x的子数组的最小操作次数
        long[][] f = new long[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            f[i][i * x - 1] = Long.MAX_VALUE;
            for (int j = i * x; j <= n - (k - i) * x; j++) {
                f[i][j] = Math.min(f[i][j - 1], f[i - 1][j - x] + dis[j - x]);
            }
        }
        return f[k][n];
    }

    //返回nums的所有长为k的子数组（到子数组中位数的）距离和
    private long[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        long[] res = new long[n - k + 1];
        //最大堆
        LazyHeap left = new LazyHeap((a, b) -> Integer.compare(b, a));
        //最小堆
        LazyHeap right = new LazyHeap(Integer::compare);
        for (int i = 0; i < n; i++) {
            int in = nums[i];
            if (left.size == right.size()) {
                left.push(right.pushPop(in));
            } else {
                right.push(left.pushPop(in));
            }
            int l = i + 1 - k;
            if (l < 0) {
                continue;
            }
            long v = left.top();
            long s1 = v * left.size() - left.sum();
            long s2 = right.sum() - v * right.size();
            res[l] = s1 + s2;
            int out = nums[l];
            if (out <= left.top()) {
                left.remove(out);
                if (left.size() < right.size()) {
                    left.push(right.pop());
                }
            } else {
                right.remove(out);
                if (left.size() > right.size() + 1) {
                    right.push(left.pop());
                }
            }
        }
        return res;
    }

}
