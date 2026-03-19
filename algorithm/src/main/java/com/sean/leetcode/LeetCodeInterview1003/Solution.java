package com.sean.leetcode.LeetCodeInterview1003;

/**
 * @Author xionghaiyang
 * @Date 2026-03-19 18:38
 * @Description https://leetcode.cn/problems/search-rotate-array-lcci
 * 面试题 10.03. 搜索旋转数组
 * 搜索旋转数组。
 * 给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。
 * 请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。
 * 若有多个相同元素，返回索引值最小的一个。
 * arr 长度范围在[1, 1000000]之间
 */
public class Solution {

    public int search(int[] arr, int target) {
        if (arr[0] == target) {
            return 0;
        }
        int n = arr.length;
        int left = 0, right = n - 1, res = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                while (mid > 0 && arr[mid - 1] == arr[mid]) {
                    mid--;
                }
                res = mid;
                break;
            }
            if (arr[mid] < arr[right]) {
                if (arr[mid] < target && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (arr[mid] > arr[right]) {
                if (arr[left] <= target && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                right--;
            }
        }
        return res;
    }

    public int search1(int[] arr, int target) {
        if (arr[0] == target) {
            return 0;
        }
        int n = arr.length;
        int left = 0, right = n - 1, res = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                while (mid > 0 && arr[mid - 1] == arr[mid]) {
                    mid--;
                }
                res = mid;
                break;
            }
            if (arr[left] < arr[mid]) {
                if (arr[left] <= target && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (arr[left] > arr[mid]) {
                if (arr[mid] < target && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                left++;
            }
        }
        return res;
    }

}
