package com.sean.course02.lesson02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-03-27 21:29
 * @Description 一个数组中有一种数出现了K次，其他数都出现了M次，怎么找到这种数
 */
public class Code03_KM {

    public static int test(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }

    private static Map<Integer, Integer> map = new HashMap<>();

    //请保证arr中，只有一种数出现了k次，其他数都出现了m次
    public static int onlyKTimes(int[] arr, int k, int m) {
        if (map.size() == 0) {
            mapCreater();
        }
        int[] cnt = new int[32];
        for (int num : arr) {
            while (num != 0) {
                int rightOne = num & ((~num) + 1);
                cnt[map.get(rightOne)]++;
                num ^= rightOne;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (cnt[i] % m != 0) {
                if (cnt[i] % m == k) {
                    res |= (1 << i);
                } else {
                    return -1;
                }
            }
        }
        if (res == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return res;
    }

    private static void mapCreater() {
        int value = 1;
        for (int i = 0; i < 32; i++) {
            map.put(value, i);
            value <<= 1;
        }
    }

    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int ktimeNum = randomNumber(range);
        int times = Math.random() < 0.5 ? k : ((int) ((m - 1) * Math.random()) + 1);
        int numKinds = (int) (maxKinds * Math.random()) + 2;
        //k * 1 + (numKinds - 1) * m
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;
        Set<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        //arr填好了
        for (int i = 0; i < arr.length; i++) {
            //i位置的数，我想随机和j位置的数做交换
            int j = (int) (arr.length * Math.random());
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    private static int randomNumber(int range) {
        return ((int) (range * Math.random()) + 1) - ((int) (range * Math.random()) + 1);
    }

    public static void main(String[] args) {
        int kinds = 5;
        int range = 30;
        int testTime = 100000;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (max * Math.random()) + 1;
            int b = (int) (max * Math.random()) + 1;
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            //k<=m
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            int res1 = test(arr, k, m);
            int res2 = onlyKTimes(arr, k, m);
            if (res1 != res2) {
                System.out.println(res1);
                System.out.println(res2);
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
