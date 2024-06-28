package com.sean.nowcoder;

import java.util.HashMap;
import java.util.Map;

public class NowCoderBM92 {

    public static int maxLength(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int left = 0, right = 0; right < arr.length; right++) {
            if (map.containsKey(arr[right])) {
                map.put(arr[right], map.get(arr[right]) + 1);
            } else {
                map.put(arr[right], 1);
            }
            while (map.get(arr[right]) > 1) {
                map.put(arr[left], map.get(arr[left++]) - 1);
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

}
