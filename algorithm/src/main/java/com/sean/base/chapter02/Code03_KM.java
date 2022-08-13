package com.sean.base.chapter02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2022-08-13 15:20
 * @Description 一个数组中有一种数出现K次，其他数都出现了M次，
 * M > 1,  K < M
 * 找到，出现了K次的数，
 * 要求，额外空间复杂度O(1)，时间复杂度O(N)
 */
public class Code03_KM {

    public static int test(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int res = 0;
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                res = num;
                break;
            }
        }
        return res;
    }

    public static Map<Integer, Integer> map = new HashMap<>();

    //请保证arr中，只有一种数出现了K次，其他数都出现了M次
    public static int onlyKTimes(int[] arr, int k, int m) {
        if (map.size() == 0) {
            mapCreate(map);
        }
        int[] t = new int[32];
        for (int num : arr) {
            while (num != 0) {
                int rightOne = num & (-num);
                t[map.get(rightOne)]++;
                num ^= rightOne;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {
                res |= (1 << i);
            }
        }
        return res;
    }

    public static void mapCreate(Map<Integer, Integer> map) {
        int value = 1;
        for (int i = 0; i < 32; i++) {
            map.put(value, i);
            value <<= 1;
        }
    }

    public static int km(int[] arr, int k, int m) {
        int[] help = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                help[i] += (num >> i) & 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            help[i] %= m;
            if (help[i] != 0) {
                res |= 1 << i;
            }
        }
        return res;
    }

    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int ktimeNum = (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
        int times = k;
        int numKinds = (int) (Math.random() * maxKinds) + 1;
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;
        Set<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    public static void main(String[] args) {
        int kinds = 5;
        int range = 30;
        int testTime = 500000;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1;
            int b = (int) (Math.random() * max) + 1;
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            int res1 = test(arr, k, m);
            int res2 = onlyKTimes(arr, k, m);
            int res3 = km(arr, k, m);
            if (res1 != res2 || res1 != res3) {
                System.out.println(res1);
                System.out.println(res2);
                System.out.println(res3);
                System.out.println("出错了");
            }
        }
        System.out.println("测试结束");
    }
}
