package com.sean.leetcode.LeetCode380;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-13 14:39
 * @Description: https://leetcode.cn/problems/insert-delete-getrandom-o1/?envType=study-plan-v2&envId=top-interview-150
 * 380. O(1) 时间插入、删除和获取随机元素
 * 实现RandomizedSet 类：
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 */
public class RandomizedSet {

    private Map<Integer, Integer> map;
    private int[] nums;
    private Random random;
    private int index;

    public RandomizedSet() {
        map = new HashMap<>();
        nums = new int[200010];
        random = new Random();
        index = -1;
    }


    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        nums[++index] = val;
        map.put(val, index);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int loc = map.remove(val);
        if (loc != index) {
            map.put(nums[index], loc);
        }
        nums[loc] = nums[index--];
        return true;
    }

    public int getRandom() {
        return nums[random.nextInt(index + 1)];
    }

}
