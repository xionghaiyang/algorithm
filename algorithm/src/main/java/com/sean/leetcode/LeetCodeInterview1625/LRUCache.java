package com.sean.leetcode.LeetCodeInterview1625;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2026-03-24 15:34
 * @Description https://leetcode.cn/problems/lru-cache-lcci
 * 面试题 16.25. LRU 缓存
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。
 * 缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。
 * 当缓存被填满时，它应该删除最近最少使用的项目。
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 */
public class LRUCache {

    public class Node {
        private int key;
        private int value;
        private Node prev;
        private Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addLast(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (get(key) != -1) {
            map.get(key).value = value;
            return;
        }
        addLast(new Node(key, value));
    }

    private void addLast(Node node) {
        if (map.size() == capacity) {
            removeFirst();
        }
        map.put(node.key, node);
        Node prev = tail.prev;
        tail.prev = node;
        node.next = tail;
        prev.next = node;
        node.prev = prev;
    }

    private void removeFirst() {
        if (head.next == tail) {
            return;
        }
        removeNode(head.next);
    }

    private void removeNode(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

}
