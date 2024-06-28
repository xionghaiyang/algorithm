package com.sean.leetcode.LeetCode457;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-01 15:24
 * @Description: https://leetcode.cn/problems/circular-array-loop/description/
 * 457. 环形数组是否存在循环
 * 存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
 * 如果 nums[i] 是正数，向前（下标递增方向）移动 |nums[i]| 步
 * 如果 nums[i] 是负数，向后（下标递减方向）移动 |nums[i]| 步
 * 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
 * 数组中的 循环 由长度为 k 的下标序列 seq 标识：
 * 遵循上述移动规则将导致一组重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * 所有 nums[seq[j]] 应当不是 全正 就是 全负
 * k > 1
 * 如果 nums 中存在循环，返回 true ；否则，返回 false 。
 */
public class Solution {

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (check(nums, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(int[] nums, int start) {
        int n = nums.length;
        int cur = start;
        boolean flag = nums[start] > 0;
        int k = 1;
        while (true) {
            if (k > n) {
                return false;
            }
            int next = ((cur + nums[cur]) % n + n) % n;
            if (flag && nums[next] < 0) {
                return false;
            }
            if (!flag && nums[next] > 0) {
                return false;
            }
            if (next == start) {
                return k > 1;
            }
            cur = next;
            k++;
        }
    }

    public boolean circularArrayLoop2(int[] nums) {
        int n = nums.length;
        int[] visit = new int[n];
        for (int start = 0, index = 1; start < n; start++, index++) {
            if (visit[start] != 0) {
                continue;
            }
            int cur = start;
            boolean flag = nums[cur] > 0;
            while (true) {
                int next = ((cur + nums[cur]) % n + n) % n;
                if (next == cur) {
                    break;
                }
                if (visit[next] != 0) {
                    //如果next点已经被标记过，并且不是在本轮被标记，那么往后的通路必然都被标记，且无环，跳出
                    if (visit[next] != index) {
                        break;
                    } else {
                        //如果next点已被标记，并且是本来被标记，说明找到了环
                        return true;
                    }
                }
                if (flag && nums[next] < 0) {
                    break;
                }
                if (!flag && nums[next] > 0) {
                    break;
                }
                visit[next] = index;
                cur = next;
            }
        }
        return false;
    }

    public boolean circularArrayLoop3(int[] nums) {
        int offset = 1001;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= offset) {
                continue;
            }
            int cur = i;
            int tag = offset + i;
            int last = -1;
            boolean flag = nums[cur] > 0;
            while (true) {
                int next = ((cur + nums[cur]) % n + n) % n;
                last = nums[cur];
                nums[cur] = tag;
                cur = next;
                if (cur == i) {
                    break;
                }
                if (nums[cur] >= offset) {
                    break;
                }
                if (flag && nums[cur] < 0) {
                    break;
                }
                if (!flag && nums[cur] > 0) {
                    break;
                }
            }
            if (last % n != 0 && nums[cur] == tag) {
                return true;
            }
        }
        return false;
    }

}
