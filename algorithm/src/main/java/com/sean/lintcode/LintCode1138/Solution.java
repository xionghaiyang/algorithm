package com.sean.lintcode.LintCode1138;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-02 12:27
 * @Description: https://www.lintcode.com/problem/1138/?showListFe=true&page=1&pageSize=50
 * 假设你有一个长花圃，其中有些地块已经被种植了，但是有些地块没有。
 * 但是，花不能够在相邻的地块下种植 - 他们会争夺水从而导致两者的死亡。
 * 给定一个花圃（用一个包含0和1的数组来表示，其中0代表空，1代表非空），
 * 和一个数字n，返回n朵新的花在这个花圃上以能否在不违反“无相邻花”的规则种植。
 */
public class Solution {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int m = flowerbed.length;
        int prev = -1;
        for (int i = 0; i < m; i++) {
            if (flowerbed[i] == 1) {
                if (prev < 0) {
                    count += i / 2;
                } else {
                    count += (i - prev - 2) / 2;
                }
                prev = i;
            }
        }
        if (prev < 0) {
            count += (m + 1) / 2;
        } else {
            count += (m - prev - 1) / 2;
        }
        return count >= n;
    }

}
