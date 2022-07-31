package com.sean.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class NowCoderBM55 {

    public static void swap(ArrayList<Integer> nums, int i, int j) {
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }

    public static void process(ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> nums, int index) {
        if (index == nums.size() - 1) {
            ans.add(nums);
        } else {
            for (int i = index; i < nums.size(); i++) {
                swap(nums, i, index);
                process(ans, nums, index + 1);
                swap(nums, i, index);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            nums.add(num[i]);
        }
        process(ans, nums, 0);
        return ans;
    }

}
