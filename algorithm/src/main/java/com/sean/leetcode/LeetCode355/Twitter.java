package com.sean.leetcode.LeetCode355;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-04-06 19:21
 * @Description https://leetcode.cn/problems/design-twitter
 * 355. 设计推特
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近 10 条推文。
 * 实现 Twitter 类：
 * Twitter() 初始化简易版推特对象
 * void postTweet(int userId, int tweetId) 根据给定的 tweetId 和 userId 创建一条新推文。
 * 每次调用此函数都会使用一个不同的 tweetId 。
 * List<Integer> getNewsFeed(int userId) 检索当前用户新闻推送中最近  10 条推文的 ID 。
 * 新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。
 * 推文必须 按照时间顺序由最近到最远排序 。
 * void follow(int followerId, int followeeId) ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户。
 * void unfollow(int followerId, int followeeId) ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户。
 * 1 <= userId, followerId, followeeId <= 500
 * 0 <= tweetId <= 10^4
 * 所有推特的 ID 都互不相同
 * postTweet、getNewsFeed、follow 和 unfollow 方法最多调用 3 * 10^4 次
 * 用户不能关注自己
 */
public class Twitter {

    public class Node {
        private int id;
        private int timestamp;
        private Node next;

        public Node(int id, int timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
    }

    private Map<Integer, Node> twitter;
    private Map<Integer, Set<Integer>> followings;
    private int timestamp;
    private PriorityQueue<Node> heap;

    public Twitter() {
        twitter = new HashMap<>();
        followings = new HashMap<>();
        timestamp = 0;
        heap = new PriorityQueue<>((a, b) -> b.timestamp - a.timestamp);
    }

    public void postTweet(int userId, int tweetId) {
        timestamp++;
        if (twitter.containsKey(userId)) {
            Node oldHead = twitter.get(userId);
            Node newHead = new Node(tweetId, timestamp);
            newHead.next = oldHead;
            twitter.put(userId, newHead);
        } else {
            twitter.put(userId, new Node(tweetId, timestamp));
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        heap.clear();
        if (twitter.containsKey(userId)) {
            heap.offer(twitter.get(userId));
        }
        if (followings.containsKey(userId)) {
            for (int id : followings.get(userId)) {
                if (twitter.containsKey(id)) {
                    heap.offer(twitter.get(id));
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        int count = 0;
        while (!heap.isEmpty() && count < 10) {
            Node node = heap.poll();
            res.add(node.id);
            if (node.next != null) {
                heap.offer(node.next);
            }
            count++;
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        if (!followings.containsKey(followerId)) {
            Set<Integer> set = new HashSet<>();
            set.add(followeeId);
            followings.put(followerId, set);
        } else {
            followings.get(followerId).add(followeeId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        if (followings.containsKey(followerId)) {
            followings.get(followerId).remove(followeeId);
        }
    }

}
