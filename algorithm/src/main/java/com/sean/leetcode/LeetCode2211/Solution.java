package com.sean.leetcode.LeetCode2211;

/**
 * @Author xionghaiyang
 * @Date 2025-12-04 09:48
 * @Description https://leetcode.cn/problems/count-collisions-on-a-road
 * 2211. 统计道路上的碰撞次数
 * 在一条无限长的公路上有 n 辆汽车正在行驶。
 * 汽车按从左到右的顺序按从 0 到 n - 1 编号，每辆车都在一个 独特的 位置。
 * 给你一个下标从 0 开始的字符串 directions ，长度为 n 。
 * directions[i] 可以是 'L'、'R' 或 'S' 分别表示第 i 辆车是向 左 、向 右 或者 停留 在当前位置。
 * 每辆车移动时 速度相同 。
 * 碰撞次数可以按下述方式计算：
 * 当两辆移动方向 相反 的车相撞时，碰撞次数加 2 。
 * 当一辆移动的车和一辆静止的车相撞时，碰撞次数加 1 。
 * 碰撞发生后，涉及的车辆将无法继续移动并停留在碰撞位置。
 * 除此之外，汽车不能改变它们的状态或移动方向。
 * 返回在这条道路上发生的 碰撞总次数 。
 * 1 <= directions.length <= 10^5
 * directions[i] 的值为 'L'、'R' 或 'S'
 */
public class Solution {

    public int countCollisions(String directions) {
        int n = directions.length();
        char[] str = directions.toCharArray();
        //前缀向左的车不会发生碰撞
        int left = 0;
        while (left < n && str[left] == 'L') {
            left++;
        }
        //后缀向右的车不会发生碰撞
        int right = n;
        while (right > left && str[right - 1] == 'R') {
            right--;
        }
        //剩下非静止的车必然会碰撞
        int res = 0;
        for (int i = left; i < right; i++) {
            if (str[i] != 'S') {
                res++;
            }
        }
        return res;
    }

}
