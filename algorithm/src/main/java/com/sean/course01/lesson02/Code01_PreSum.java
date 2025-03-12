package com.sean.course01.lesson02;

/**
 * @Author xionghaiyang
 * @Date 2025-03-12 14:22
 * @Description 前缀数组
 * 假设有一个数组arr，用户总是频繁的查询arr中某一段的累加和
 * 你如何组织数据，能让这种查询变得便利和快捷？
 */
public class Code01_PreSum {

    public class RangeSum1 {
        private int[] arr;

        public RangeSum1(int[] arr) {
            this.arr = arr;
        }

        public int rangeSum(int L, int R) {
            int res = 0;
            for (int i = L; i <= R; i++) {
                res += arr[i];
            }
            return res;
        }
    }

    public class RangeSum2 {
        private int[] preSum;

        public RangeSum2(int[] arr) {
            int n = arr.length;
            preSum = new int[n];
            preSum[0] = arr[0];
            for (int i = 1; i < n; i++) {
                preSum[i] = preSum[i - 1] + arr[i];
            }
        }

        public int rangeSum(int L, int R) {
            return L == 0 ? preSum[R] : preSum[R] - preSum[L - 1];
        }
    }

}
