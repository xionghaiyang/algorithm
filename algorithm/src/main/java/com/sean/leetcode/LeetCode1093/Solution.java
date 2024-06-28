package com.sean.leetcode.LeetCode1093;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-29 08:13
 * @Description: https://leetcode.cn/problems/statistics-from-a-large-sample/
 * 1093. 大样本统计
 * 我们对 0 到 255 之间的整数进行采样，并将结果存储在数组 count 中：count[k] 就是整数 k 在样本中出现的次数。
 * 计算以下统计数据:
 * minimum ：样本中的最小元素。
 * maximum ：样品中的最大元素。
 * mean ：样本的平均值，计算为所有元素的总和除以元素总数。
 * median ：
 * 如果样本的元素个数是奇数，那么一旦样本排序后，中位数 median 就是中间的元素。
 * 如果样本中有偶数个元素，那么中位数median 就是样本排序后中间两个元素的平均值。
 * mode ：样本中出现次数最多的数字。保众数是 唯一 的。
 * 以浮点数数组的形式返回样本的统计信息 [minimum, maximum, mean, median, mode] 。
 * 与真实答案误差在 10-5 内的答案都可以通过。
 */
public class Solution {

    public double[] sampleStats(int[] count) {
        int minimum = 255;
        int maximum = 0;
        long sum = 0;
        int cnt0 = 0;
        int mod = -1, maxCount = 0;
        int n = count.length;
        for (int i = 0; i < n; i++) {
            if (count[i] > 0) {
                minimum = Math.min(minimum, i);
                maximum = Math.max(maximum, i);
                sum += (long) i * count[i];
                cnt0 += count[i];
                if (count[i] > maxCount) {
                    maxCount = count[i];
                    mod = i;
                }
            }
        }
        double median = 0;
        int cnt1 = 0;
        if (cnt0 % 2 == 0) {
            int mid1 = -1;
            int mid2 = -1;
            for (int i = 0; i < n; i++) {
                if (count[i] > 0) {
                    cnt1 += count[i];
                    if (mid1 == -1) {
                        if (cnt1 > cnt0 / 2) {
                            mid1 = i;
                            mid2 = i;
                            break;
                        } else if (cnt1 == cnt0 / 2) {
                            mid1 = i;
                        }
                    } else {
                        mid2 = i;
                        break;
                    }
                }
            }
            median = ((double) mid1 + (double) mid2) / 2;
        } else {
            for (int i = 0; i < n; i++) {
                if (count[i] > 0) {
                    cnt1 += count[i];
                    if (cnt1 > cnt0 / 2) {
                        median = i;
                        break;
                    }
                }
            }
        }
        return new double[]{minimum, maximum, (double) sum / cnt0, median, mod};
    }

}
