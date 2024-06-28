package com.sean.leetcode.LeetCode2748;

/**
 * @Author xionghaiyang
 * @Date 2024-06-20 07:40
 * @Description https://leetcode.cn/problems/number-of-beautiful-pairs/
 * 2748. 美丽下标对的数目
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 如果下标对 i、j 满足 0 ≤ i < j < nums.length ，如果 nums[i] 的 第一个数字 和 nums[j] 的 最后一个数字 互质 ，
 * 则认为 nums[i] 和 nums[j] 是一组 美丽下标对 。
 * 返回 nums 中 美丽下标对 的总数目。
 * 对于两个整数 x 和 y ，如果不存在大于 1 的整数可以整除它们，则认为 x 和 y 互质 。
 * 换而言之，如果 gcd(x, y) == 1 ，则认为 x 和 y 互质，其中 gcd(x, y) 是 x 和 y 的 最大公因数 。
 */
public class Solution {

    public int countBeautifulPairs(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (nums[i] >= 10) {
                nums[i] /= 10;
            }
            for (int j = i + 1; j < n; j++) {
                if (gcd(nums[i], nums[j] % 10) == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int countBeautifulPairs1(int[] nums) {
        int res = 0;
        int[] cnt = new int[10];
        for (int x : nums) {
            for (int y = 1; y <= 9; y++) {
                if (gcd(x % 10, y) == 1) {
                    res += cnt[y];
                }
            }
            while (x >= 10) {
                x /= 10;
            }
            cnt[x]++;
        }
        return res;
    }

}
