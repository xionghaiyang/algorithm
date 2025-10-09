package com.sean.leetcode.LeetCodeInterview0306;

import java.util.LinkedList;

/**
 * @Author xionghaiyang
 * @Date 2025-10-09 12:24
 * @Description https://leetcode.cn/problems/animal-shelter-lcci
 * 面试题 03.06. 动物收容所
 * 动物收容所。
 * 有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。
 * 在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。
 * 换言之，收养人不能自由挑选想收养的对象。
 * 请创建适用于这个系统的数据结构，实现各种操作方法，比如enqueue、dequeueAny、dequeueDog和dequeueCat。
 * 允许使用Java内置的LinkedList数据结构。
 * enqueue方法有一个animal参数，animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。
 * dequeue*方法返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]。
 * 收纳所的最大容量为20000
 */
public class AnimalShelf {

    private LinkedList<int[]> catQueue;
    private LinkedList<int[]> dogQueue;

    public AnimalShelf() {
        catQueue = new LinkedList<>();
        dogQueue = new LinkedList<>();
    }

    public void enqueue(int[] animal) {
        if (animal[1] == 0) {
            catQueue.addLast(animal);
        } else if (animal[1] == 1) {
            dogQueue.addLast(animal);
        }
    }

    public int[] dequeueAny() {
        int[] cat;
        if (!catQueue.isEmpty()) {
            cat = catQueue.getFirst();
        } else if (!dogQueue.isEmpty()) {
            return dogQueue.pollFirst();
        } else {
            return new int[]{-1, -1};
        }
        int[] dog;
        if (!dogQueue.isEmpty()) {
            dog = dogQueue.getFirst();
        } else {
            return catQueue.pollFirst();
        }
        if (cat[0] <= dog[0]) {
            return catQueue.pollFirst();
        } else {
            return dogQueue.pollFirst();
        }
    }

    public int[] dequeueDog() {
        if (!dogQueue.isEmpty()) {
            return dogQueue.pollFirst();
        } else {
            return new int[]{-1, -1};
        }
    }

    public int[] dequeueCat() {
        if (!catQueue.isEmpty()) {
            return catQueue.pollFirst();
        } else {
            return new int[]{-1, -1};
        }
    }

}
