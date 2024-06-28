package com.sean.nowcoder;

public class NowCoderBM21 {

    public static int minNumberInRotateArray(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else if (array[mid] == array[right]) {
                right--;
            } else {
                right = mid;
            }
        }
        return array[left];
    }

}
