package com.sean.nowcoder;

import java.util.HashMap;
import java.util.Map;

public class NowCoderBM50 {

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.isEmpty()) {
                map.put(numbers[i], i + 1);
            } else if (map.containsKey(target - numbers[i])) {
                return new int[]{map.get(target - numbers[i]), i + 1};
            } else {
                map.put(numbers[i], i + 1);
            }
        }
        return null;
    }

}
