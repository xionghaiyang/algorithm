package com.sean.leetcode;

/**
 * @Author xionghaiyang
 * @Date 2022/7/24 22:57
 */
public class LeetCode75 {

    /**
     * https://leetcode.cn/problems/sort-colors/submissions/
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，
     * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * 必须在不使用库的sort函数的情况下解决这个问题。
     */

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }
        int index = 0;
        while (count[0] > 0) {
            nums[index++] = 0;
            count[0]--;
        }
        while (count[1] > 0) {
            nums[index++] = 1;
            count[1]--;
        }
        while (count[2] > 0) {
            nums[index++] = 2;
            count[2]--;
        }
    }

}
