package com.sean.leetcode.LeetCode1912;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-21 09:58
 * @Description https://leetcode.cn/problems/design-movie-rental-system
 * 1912. 设计电影租借系统
 * 你有一个电影租借公司和 n 个电影商店。
 * 你想要实现一个电影租借系统，它支持查询、预订和返还电影的操作。
 * 同时系统还能生成一份当前被借出电影的报告。
 * 所有电影用二维整数数组 entries 表示，其中 entries[i] = [shopi, moviei, pricei] 表示商店 shopi 有一份电影 moviei 的拷贝，租借价格为 pricei 。
 * 每个商店有 至多一份 编号为 moviei 的电影拷贝。
 * 系统需要支持以下操作：
 * Search：找到拥有指定电影且 未借出 的商店中 最便宜的 5 个 。
 * 商店需要按照 价格 升序排序，如果价格相同，则 shopi 较小 的商店排在前面。
 * 如果查询结果少于 5 个商店，则将它们全部返回。
 * 如果查询结果没有任何商店，则返回空列表。
 * Rent：从指定商店借出指定电影，题目保证指定电影在指定商店 未借出 。
 * Drop：在指定商店返还 之前已借出 的指定电影。
 * Report：返回 最便宜的 5 部已借出电影 （可能有重复的电影 ID），将结果用二维列表 res 返回，其中 res[j] = [shopj, moviej] 表示第 j 便宜的已借出电影是从商店 shopj 借出的电影 moviej 。
 * res 中的电影需要按 价格 升序排序；如果价格相同，则 shopj 较小 的排在前面；如果仍然相同，则 moviej 较小 的排在前面。
 * 如果当前借出的电影小于 5 部，则将它们全部返回。
 * 如果当前没有借出电影，则返回一个空的列表。
 * 请你实现 MovieRentingSystem 类：
 * MovieRentingSystem(int n, int[][] entries) 将 MovieRentingSystem 对象用 n 个商店和 entries 表示的电影列表初始化。
 * List<Integer> search(int movie) 如上所述，返回 未借出 指定 movie 的商店列表。
 * void rent(int shop, int movie) 从指定商店 shop 借出指定电影 movie 。
 * void drop(int shop, int movie) 在指定商店 shop 返还之前借出的电影 movie 。
 * List<List<Integer>> report() 如上所述，返回最便宜的 已借出 电影列表。
 * 注意：测试数据保证 rent 操作中指定商店拥有 未借出 的指定电影，且 drop 操作指定的商店 之前已借出 指定电影。
 * 1 <= n <= 3 * 105
 * 1 <= entries.length <= 105
 * 0 <= shopi < n
 * 1 <= moviei, pricei <= 104
 * 每个商店 至多 有一份电影 moviei 的拷贝。
 * search，rent，drop 和 report 的调用 总共 不超过 105 次。
 */
public class MovieRentingSystem {

    //(shop,movie) -> price
    private Map<Long, Integer> shopMovie2Price;
    //movie -> {(price,shop)}
    private Map<Integer, TreeSet<int[]>> unrentedMovie2PriceShop;
    //{(price,shop,movie)}
    private TreeSet<int[]> rentedMovies;

    //n 个商店
    //entries[i] = [shopi, moviei, pricei] 表示商店 shopi 有一份电影 moviei 的拷贝，租借价格为 pricei
    public MovieRentingSystem(int n, int[][] entries) {
        shopMovie2Price = new HashMap<>();
        unrentedMovie2PriceShop = new HashMap<>();
        rentedMovies = new TreeSet<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : (a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]));
        for (int[] entry : entries) {
            int shop = entry[0], movie = entry[1], price = entry[2];
            shopMovie2Price.put((long) shop << 32 | movie, price);
            unrentedMovie2PriceShop.computeIfAbsent(movie, e -> new TreeSet<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]))
                    .add(new int[]{price, shop});
        }
    }

    //找到拥有指定电影且 未借出 的商店中 最便宜的 5 个
    //商店需要按照 价格 升序排序，如果价格相同，则 shopi 较小 的商店排在前面
    //如果查询结果少于 5 个商店，则将它们全部返回
    //如果查询结果没有任何商店，则返回空列表
    public List<Integer> search(int movie) {
        if (!unrentedMovie2PriceShop.containsKey(movie)) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>(5);
        for (int[] ps : unrentedMovie2PriceShop.get(movie)) {
            res.add(ps[1]);
            if (res.size() == 5) {
                break;
            }
        }
        return res;
    }

    //从指定商店借出指定电影，题目保证指定电影在指定商店 未借出
    public void rent(int shop, int movie) {
        int price = shopMovie2Price.get((long) shop << 32 | movie);
        unrentedMovie2PriceShop.get(movie).remove(new int[]{price, shop});
        rentedMovies.add(new int[]{price, shop, movie});
    }

    //在指定商店返还 之前已借出 的指定电影
    public void drop(int shop, int movie) {
        int price = shopMovie2Price.get((long) shop << 32 | movie);
        rentedMovies.remove(new int[]{price, shop, movie});
        unrentedMovie2PriceShop.get(movie).add(new int[]{price, shop});
    }

    //返回 最便宜的 5 部已借出电影 （可能有重复的电影 ID），将结果用二维列表 res 返回，其中 res[j] = [shopj, moviej] 表示第 j 便宜的已借出电影是从商店 shopj 借出的电影 moviej
    //如果当前借出的电影小于 5 部，则将它们全部返回
    //如果当前没有借出电影，则返回一个空的列表
    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>(5);
        for (int[] e : rentedMovies) {
            List<Integer> list = new ArrayList<>();
            list.add(e[1]);
            list.add(e[2]);
            res.add(list);
            if (res.size() == 5) {
                break;
            }
        }
        return res;
    }

}
