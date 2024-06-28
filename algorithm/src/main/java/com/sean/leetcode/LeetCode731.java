package com.sean.leetcode;

import org.omg.CORBA.TRANSACTION_MODE;

import java.util.*;

public class LeetCode731 {

    class MyCalendarTwo1 {

        List<int[]> booked;
        List<int[]> overlaps;

        public MyCalendarTwo1() {
            booked = new ArrayList<>();
            overlaps = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for (int[] arr : overlaps) {
                int left = arr[0], right = arr[1];
                if (left < end && start < right) {
                    return false;
                }
            }
            for (int[] arr : booked) {
                int left = arr[0], right = arr[1];
                if (left < end && start < right) {
                    overlaps.add(new int[]{Math.max(left, start), Math.min(right, end)});
                }
            }
            booked.add(new int[]{start, end});
            return true;
        }
    }

    class MyCalendarTwo2 {
        Map<Integer, Integer> map;

        public MyCalendarTwo2() {
            map = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            int maxBook = 0;
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int freq = entry.getValue();
                maxBook += freq;
                if (maxBook > 2) {
                    map.put(start, map.getOrDefault(start, 0) - 1);
                    map.put(end, map.getOrDefault(end, 0) + 1);
                    return false;
                }
            }
            return true;
        }
    }


    class MyCalendarTwo {
        Map<Integer, int[]> tree;

        public MyCalendarTwo() {
            tree = new HashMap<>();
        }

        public boolean book(int start, int end) {
            update(start, end - 1, 1, 0, 1000000000, 1);
            tree.putIfAbsent(1, new int[2]);
            if (tree.get(1)[0] > 2) {
                update(start, end - 1, -1, 0, 1000000000, 1);
                return false;
            }
            return true;
        }

        private void update(int start, int end, int val, int left, int right, int index) {
            if (right < start || end < left) {
                return;
            }
            tree.putIfAbsent(index, new int[2]);
            if (start <= left && right <= end) {
                tree.get(index)[0] += val;
                tree.get(index)[1] += val;
            } else {
                int mid = left + ((right - left) >> 1);
                update(start, end, val, left, mid, 2 * index);
                update(start, end, val, mid + 1, right, 2 * index + 1);
                tree.putIfAbsent(2 * index, new int[2]);
                tree.putIfAbsent(2 * index + 1, new int[2]);
                tree.get(index)[0] = tree.get(index)[1] + Math.max(tree.get(2 * index)[0], tree.get(2 * index + 1)[0]);
            }
        }

    }

}
