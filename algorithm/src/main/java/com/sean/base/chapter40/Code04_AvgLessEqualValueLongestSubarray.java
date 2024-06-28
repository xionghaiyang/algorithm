package com.sean.base.chapter40;


import java.util.TreeMap;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-20 21:10
 * @Description: TODO
 */
public class Code04_AvgLessEqualValueLongestSubarray {

    //暴力解，时间复杂度O(N^3)，用于做对数器
    public int ways1(int[] arr, int v) {
        int res = 0;
        for (int L = 0; L < arr.length; L++) {
            for (int R = L; R < arr.length; R++) {
                int sum = 0;
                int k = R - L + 1;
                for (int i = L; i <= R; i++) {
                    sum += arr[i];
                }
                double avg = (double) sum / (double) k;
                if (avg <= v) {
                    res = Math.max(res, k);
                }
            }
        }
        return res;
    }

    //想实现的解法2,时间复杂度O(N*logN)
    public int ways2(int[] arr, int v) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        TreeMap<Integer, Integer> origins = new TreeMap<>();
        int ans = 0;
        int modify = 0;
        for (int i = 0; i < arr.length; i++) {
            int p1 = arr[i] <= v ? 1 : 0;
            int p2 = 0;
            int query = -arr[i] - modify;
            if (origins.floorKey(query) != null) {
                p2 = i - origins.get(origins.floorKey(query)) + 1;
            }
            ans = Math.max(ans, Math.max(p1, p2));
            int curOrigin = -modify - v;
            if (origins.floorKey(curOrigin) == null) {
                origins.put(curOrigin, i);
            }
            modify += arr[i] - v;
        }
        return ans;
    }

    //想实现的解法3，时间复杂度O(N)
    public int ways3(int[] arr, int v) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= v;
        }
        return maxLengthAwesome(arr, 0);
    }

    private int maxLengthAwesome(int[] arr, int k) {
        int N = arr.length;
        int[] sums = new int[N];
        int[] ends = new int[N];
        sums[N - 1] = arr[N - 1];
        ends[N - 1] = N - 1;
        for (int i = N - 2; i >= 0; i--) {
            if (sums[i + 1] < 0) {
                sums[i] = arr[i] + sums[i + 1];
                ends[i] = ends[i + 1];
            } else {
                sums[i] = arr[i];
                ends[i] = i;
            }
        }
        int end = 0;
        int sum = 0;
        int res = 0;
        for (int i = 0; i < N; i++) {
            while (end < N && sum + sums[end] <= k) {
                sum += sums[end];
                end = ends[end] + 1;
            }
            res = Math.max(res, end - i);
            if (end > i) {
                sum -= arr[i];
            } else {
                end = i + 1;
            }
        }
        return res;
    }

    public int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxValue);
        }
        return ans;
    }

    public int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code04_AvgLessEqualValueLongestSubarray solution = new Code04_AvgLessEqualValueLongestSubarray();
        System.out.println("测试开始");
        int maxLen = 20;
        int maxValue = 100;
        int testTime = 500000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = solution.randomArray(maxLen, maxValue);
            int value = (int) (Math.random() * maxValue);
            int[] arr1 = solution.copyArray(arr);
            int[] arr2 = solution.copyArray(arr);
            int[] arr3 = solution.copyArray(arr);
            int res1 = solution.ways1(arr1, value);
            int res2 = solution.ways2(arr1, value);
            int res3 = solution.ways3(arr1, value);
            if (res1 != res2 || res1 != res3) {
                System.out.println("测试出错!");
                System.out.println("测试数组:");
                solution.printArray(arr);
                System.out.println("子数组平均值不小于:" + value);
                System.out.println("方法1得到的最大长度:" + res1);
                System.out.println("方法2得到的最大长度:" + res2);
                System.out.println("方法3得到的最大长度:" + res3);
                System.out.println("===========================");
            }
        }
        System.out.println("测试结束");
    }

}
