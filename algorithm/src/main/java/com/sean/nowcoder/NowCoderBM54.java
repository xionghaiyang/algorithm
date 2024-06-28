package com.sean.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class NowCoderBM54 {

    public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (num == null || num.length < 3) {
            return ans;
        }
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            if (i != 0 && num[i] == num[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = num.length - 1;
            int target = -num[i];
            while (left < right) {
                if (num[left] + num[right] == target) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(num[i]);
                    temp.add(num[left]);
                    temp.add(num[right]);
                    ans.add(temp);
                    while (left + 1 < right && num[left] == num[left + 1]) {
                        left++;
                    }
                    while (right - 1 > left && num[right] == num[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (num[left] + num[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }

}
