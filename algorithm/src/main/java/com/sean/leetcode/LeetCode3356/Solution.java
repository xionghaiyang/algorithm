package com.sean.leetcode.LeetCode3356;

/**
 * @Author xionghaiyang
 * @Date 2025-05-21 06:39
 * @Description https://leetcode.cn/problems/zero-array-transformation-ii
 * 3356. 零数组变换 II
 * 给你一个长度为 n 的整数数组 nums 和一个二维数组 queries，其中 queries[i] = [li, ri, vali]。
 * 每个 queries[i] 表示在 nums 上执行以下操作：
 * 将 nums 中 [li, ri] 范围内的每个下标对应元素的值 最多 减少 vali。
 * 每个下标的减少的数值可以独立选择。
 * 零数组 是指所有元素都等于 0 的数组。
 * 返回 k 可以取到的 最小非负 值，使得在 顺序 处理前 k 个查询后，nums 变成 零数组。
 * 如果不存在这样的 k，则返回 -1。
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 5 * 10^5
 * 1 <= queries.length <= 10^5
 * queries[i].length == 3
 * 0 <= li <= ri < nums.length
 * 1 <= vali <= 5
 */
public class Solution {

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int index = -1;
        if ((index = check(nums, index)) == n - 1) {
            return 0;
        }
        for (int k = 0; k < m; k++) {
            for (int i = queries[k][0]; i <= queries[k][1]; i++) {
                nums[i] -= queries[k][2];
            }
            if ((index = check(nums, index)) == n - 1) {
                return k + 1;
            }
        }
        return -1;
    }

    private int check(int[] nums, int index) {
        int n = nums.length;
        while (index + 1 < n && nums[index + 1] <= 0) {
            index++;
        }
        return index;
    }

    public int minZeroArray1(int[] nums, int[][] queries) {
        int left = 0, right = queries.length;
        if (!check(nums, queries, right)) {
            return -1;
        }
        while (left < right) {
            int k = left + ((right - left) >> 1);
            if (check(nums, queries, k)) {
                right = k;
            } else {
                left = k + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int[][] queries, int k) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        for (int i = 0; i < k; i++) {
            diff[queries[i][0]] += queries[i][2];
            diff[queries[i][1] + 1] -= queries[i][2];
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (sum < nums[i]) {
                return false;
            }
        }
        return true;
    }

    public int minZeroArray2(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] diff = new int[n + 1];
        int k = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            while (k < m && sum < nums[i]) {
                int[] query = queries[k];
                int l = query[0], r = query[1], val = query[2];
                diff[l] += val;
                diff[r + 1] -= val;
                if (l <= i && i <= r) {
                    sum += val;
                }
                k++;
            }
            if (sum < nums[i]) {
                return -1;
            }
        }
        return k;
    }

    public class SegmentTree {
        private int[] max;
        private int[] lazy;

        public SegmentTree(int[] nums) {
            int n = nums.length;
            int m = 2 << (32 - Integer.numberOfLeadingZeros(n));
            max = new int[m];
            lazy = new int[m];
            build(1, 0, n - 1, nums);
        }

        private void build(int index, int l, int r, int[] nums) {
            if (l == r) {
                max[index] = nums[l];
                return;
            }
            int mid = l + ((r - l) >> 1);
            build(index << 1, l, mid, nums);
            build(index << 1 | 1, mid + 1, r, nums);
            pushUp(index);
        }

        private void pushUp(int index) {
            max[index] = Math.max(max[index << 1], max[index << 1 | 1]);
        }

        public void update(int index, int l, int r, int L, int R, int val) {
            if (L <= l && r <= R) {
                process(index, val);
                return;
            }
            pushDown(index);
            int mid = l + ((r - l) >> 1);
            if (L <= mid) {
                update(index << 1, l, mid, L, R, val);
            }
            if (mid < R) {
                update(index << 1 | 1, mid + 1, r, L, R, val);
            }
            pushUp(index);
        }

        private void process(int index, int val) {
            max[index] -= val;
            lazy[index] += val;
        }

        private void pushDown(int index) {
            if (lazy[index] != 0) {
                process(index << 1, lazy[index]);
                process(index << 1 | 1, lazy[index]);
                lazy[index] = 0;
            }
        }

        public int query() {
            return max[1];
        }
    }

    public int minZeroArray3(int[] nums, int[][] queries) {
        SegmentTree segmentTree = new SegmentTree(nums);
        if (segmentTree.query() <= 0) {
            return 0;
        }
        int n = nums.length;
        int m = queries.length;
        for (int k = 0; k < m; k++) {
            int[] query = queries[k];
            segmentTree.update(1, 0, n - 1, query[0], query[1], query[2]);
            if (segmentTree.query() <= 0) {
                return k + 1;
            }
        }
        return -1;
    }

}
