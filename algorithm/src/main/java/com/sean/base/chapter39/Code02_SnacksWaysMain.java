package com.sean.base.chapter39;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-14 17:02
 * @Description: TODO
 */
public class Code02_SnacksWaysMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int bag = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        long ways = ways(arr, bag);
        System.out.println(ways);
        sc.close();
    }

    private static long ways(int[] arr, int bag) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] <= bag ? 2 : 1;
        }
        int mid = (arr.length - 1) >> 1;
        TreeMap<Long, Long> lmap = new TreeMap<>();
        long ways = process(arr, 0, 0, mid, bag, lmap);
        TreeMap<Long, Long> rmap = new TreeMap<>();
        ways += process(arr, mid + 1, 0, arr.length - 1, bag, rmap);
        TreeMap<Long, Long> rpre = new TreeMap<>();
        long pre = 0;
        for (Map.Entry<Long, Long> entry : rmap.entrySet()) {
            pre += entry.getValue();
            rpre.put(entry.getKey(), pre);
        }
        for (Map.Entry<Long, Long> entry : lmap.entrySet()) {
            long lweight = entry.getKey();
            long lways = entry.getValue();
            Long floor = rpre.floorKey(bag - lweight);
            if (floor != null) {
                long rways = rpre.get(floor);
                ways += lways * rways;
            }
        }
        return ways + 1;
    }

    private static long process(int[] arr, int index, long w, int end, int bag, TreeMap<Long, Long> map) {
        if (w > bag) {
            return 0;
        }
        if (index > end) {
            if (w != 0) {
                if (!map.containsKey(w)) {
                    map.put(w, 1L);
                } else {
                    map.put(w, map.get(w) + 1);
                }
                return 1;
            } else {
                return 0;
            }
        } else {
            long ways = process(arr, index + 1, w, end, bag, map);
            ways += process(arr, index + 1, w + arr[index], end, bag, map);
            return ways;
        }
    }

    public long func(int[] arr, int index, int end, long sum, long bag, TreeMap<Long, Long> map) {
        if (sum > bag) {
            return 0;
        }
        //sum<=bag
        if (index > end) {//多有商品自由选择完了
            //sum
            if (sum != 0) {
                if (!map.containsKey(sum)) {
                    map.put(sum, 1L);
                } else {
                    map.put(sum, map.get(sum) + 1);
                }
                return 1;
            } else {
                return 0;
            }
        }
        //sum<=bag并且index<=end(还有货)
        //1)不要当前index位置的货
        long ways = func(arr, index + 1, end, sum, bag, map);
        //2)要当前index位置的货
        ways += func(arr, index + 1, end, sum + arr[index], bag, map);
        return ways;
    }

}
