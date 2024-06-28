package com.sean.lintcode;

import java.util.*;

public class LintCode1143 {

    public static String[] findRestaurant1(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        Map<Integer, ArrayList<String>> resMap = new HashMap<>();
        int minIndexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int key = map.get(list2[i]) + i;
                if (resMap.containsKey(key)) {
                    resMap.get(key).add(list2[i]);
                } else {
                    ArrayList<String> val = new ArrayList<>();
                    val.add(list2[i]);
                    resMap.put(key, val);
                }
                minIndexSum = Math.min(minIndexSum, key);
            }
        }
        ArrayList<String> list = resMap.get(minIndexSum);
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        ArrayList<String> list = new ArrayList<>();
        int minIndexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int key = map.get(list2[i]) + i;
                if (key < minIndexSum) {
                    list.clear();
                    list.add(list2[i]);
                    minIndexSum = key;
                } else if (key == minIndexSum) {
                    list.add(list2[i]);
                }
            }
        }
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
