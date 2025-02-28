package com.sean.leetcode.LeetCode2353;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-02-28 08:27
 * @Description https://leetcode.cn/problems/design-a-food-rating-system/
 * 2353. 设计食物评分系统
 * 设计一个支持下述操作的食物评分系统：
 * 修改 系统中列出的某种食物的评分。
 * 返回系统中某一类烹饪方式下评分最高的食物。
 * 实现 FoodRatings 类：
 * FoodRatings(String[] foods, String[] cuisines, int[] ratings) 初始化系统。
 * 食物由 foods、cuisines 和 ratings 描述，长度均为 n 。
 * foods[i] 是第 i 种食物的名字。
 * cuisines[i] 是第 i 种食物的烹饪方式。
 * ratings[i] 是第 i 种食物的最初评分。
 * void changeRating(String food, int newRating) 修改名字为 food 的食物的评分。
 * String highestRated(String cuisine) 返回指定烹饪方式 cuisine 下评分最高的食物的名字。如果存在并列，返回 字典序较小 的名字。
 * 注意，字符串 x 的字典序比字符串 y 更小的前提是：x 在字典中出现的位置在 y 之前，也就是说，要么 x 是 y 的前缀，或者在满足 x[i] != y[i] 的第一个位置 i 处，x[i] 在字母表中出现的位置在 y[i] 之前。
 * 1 <= n <= 2 * 10^4
 * n == foods.length == cuisines.length == ratings.length
 * 1 <= foods[i].length, cuisines[i].length <= 10
 * foods[i]、cuisines[i] 由小写英文字母组成
 * 1 <= ratings[i] <= 10^8
 * foods 中的所有字符串 互不相同
 * 在对 changeRating 的所有调用中，food 是系统中食物的名字。
 * 在对 highestRated 的所有调用中，cuisine 是系统中 至少一种 食物的烹饪方式。
 * 最多调用 changeRating 和 highestRated 总计 2 * 10^4 次
 */
public class FoodRatings {

    class Info {
        String food;
        String cuisine;
        int rating;

        public Info(String food, String cuisine, int rating) {
            this.food = food;
            this.cuisine = cuisine;
            this.rating = rating;
        }
    }

    private Map<String, Info> foodMap;
    private Map<String, PriorityQueue<Info>> ratingMap;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;
        foodMap = new HashMap<>();
        ratingMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];
            Info info = new Info(food, cuisine, rating);
            foodMap.put(food, info);
            ratingMap.computeIfAbsent(cuisine, k -> new PriorityQueue<Info>((a, b) -> {
                if (a.rating != b.rating) {
                    return b.rating - a.rating;
                }
                return a.food.compareTo(b.food);
            })).add(info);
        }
    }

    public void changeRating(String food, int newRating) {
        Info oldInfo = foodMap.get(food);
        String cuisine = oldInfo.cuisine;
        Info newInfo = new Info(food, cuisine, newRating);
        foodMap.put(food, newInfo);
        ratingMap.get(cuisine).add(newInfo);
    }

    public String highestRated(String cuisine) {
        PriorityQueue<Info> pq = ratingMap.get(cuisine);
        while (!pq.isEmpty()) {
            Info info = pq.peek();
            if (info.rating == foodMap.get(info.food).rating) {
                return info.food;
            } else {
                pq.poll();
            }
        }
        return "";
    }

}
