package com.sean.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/7/26 9:04
 */
public class LeetCode1206 {

    /**
     * https://leetcode.cn/problems/design-skiplist/
     * 不使用任何库函数，设计一个 跳表 。
     * 跳表 是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。
     * 跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
     * 跳表中有很多层，每一层是一个短的链表。
     * 在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。
     * 跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
     * 在本题中，你的设计应该要包含这些函数：
     * bool search(int target) : 返回target是否存在于跳表中。
     * void add(int num): 插入一个元素到跳表。
     * bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
     * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
     */

    class SkiplistNode {
        int val;
        SkiplistNode[] forward;

        public SkiplistNode(int val, int maxLevel) {
            this.val = val;
            this.forward = new SkiplistNode[maxLevel];
        }
    }

    class Skiplist {

        private static final int MAX_LEVEL = 32;
        private static final double P_FACTOR = 0.25;
        private SkiplistNode head;
        private int level;
        private Random random;

        public Skiplist() {
            this.head = new SkiplistNode(-1, MAX_LEVEL);
            this.level = 0;
            this.random = new Random();
        }

        public boolean search(int target) {
            SkiplistNode cur = this.head;
            for (int i = level - 1; i >= 0; i--) {
                while (cur.forward[i] != null && cur.forward[i].val < target) {
                    cur = cur.forward[i];
                }
            }
            cur = cur.forward[0];
            if (cur != null && cur.val == target) {
                return true;
            }
            return false;
        }

        public void add(int num) {
            SkiplistNode[] update = new SkiplistNode[MAX_LEVEL];
            Arrays.fill(update, head);
            SkiplistNode cur = this.head;
            for (int i = level - 1; i >= 0; i--) {
                while (cur.forward[i] != null && cur.forward[i].val < num) {
                    cur = cur.forward[i];
                }
                update[i] = cur;
            }
            int lv = randomLevel();
            this.level = Math.max(this.level, lv);
            SkiplistNode newNode = new SkiplistNode(num, lv);
            for (int i = 0; i < lv; i++) {
                newNode.forward[i] = update[i].forward[i];
                update[i].forward[i] = newNode;
            }
        }

        public boolean erase(int num) {
            SkiplistNode[] update = new SkiplistNode[MAX_LEVEL];
            SkiplistNode cur = this.head;
            for (int i = level - 1; i >= 0; i--) {
                while (cur.forward[i] != null && cur.forward[i].val < num) {
                    cur = cur.forward[i];
                }
                update[i] = cur;
            }
            cur = cur.forward[0];
            if (cur == null || cur.val != num) {
                return false;
            }
            for (int i = 0; i < level; i++) {
                if (update[i].forward[i] != cur) {
                    break;
                }
                update[i].forward[i] = cur.forward[i];
            }
            while (level > 1 && head.forward[level - 1] == null) {
                level--;
            }
            return true;
        }

        private int randomLevel() {
            int res = 1;
            while (random.nextDouble() < P_FACTOR && res < MAX_LEVEL) {
                res++;
            }
            return res;
        }

    }


}
