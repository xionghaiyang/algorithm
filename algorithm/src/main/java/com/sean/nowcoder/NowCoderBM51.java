package com.sean.nowcoder;

import java.util.HashMap;
import java.util.Map;

public class NowCoderBM51 {

    public static int MoreThanHalfNum_Solution(int[] array) {
        int length = array.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : array) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count > length / 2) {
                return num;
            }
            map.put(num, count);
        }
        return -1;
    }

}
