package com.sean.leetcode.LeetCode3508;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-20 09:58
 * @Description https://leetcode.cn/problems/implement-router
 * 3508. 设计路由器
 * 请你设计一个数据结构来高效管理网络路由器中的数据包。
 * 每个数据包包含以下属性：
 * source：生成该数据包的机器的唯一标识符。
 * destination：目标机器的唯一标识符。
 * timestamp：该数据包到达路由器的时间戳。
 * 实现 Router 类：
 * Router(int memoryLimit)：初始化路由器对象，并设置固定的内存限制。
 * memoryLimit 是路由器在任意时间点可以存储的 最大 数据包数量。
 * 如果添加一个新数据包会超过这个限制，则必须移除 最旧的 数据包以腾出空间。
 * bool addPacket(int source, int destination, int timestamp)：将具有给定属性的数据包添加到路由器。
 * 如果路由器中已经存在一个具有相同 source、destination 和 timestamp 的数据包，则视为重复数据包。
 * 如果数据包成功添加（即不是重复数据包），返回 true；否则返回 false。
 * int[] forwardPacket()：以 FIFO（先进先出）顺序转发下一个数据包。
 * 从存储中移除该数据包。
 * 以数组 [source, destination, timestamp] 的形式返回该数据包。
 * 如果没有数据包可以转发，则返回空数组。
 * int getCount(int destination, int startTime, int endTime)：
 * 返回当前存储在路由器中（即尚未转发）的，且目标地址为指定 destination 且时间戳在范围 [startTime, endTime]（包括两端）内的数据包数量。
 * 注意：对于 addPacket 的查询会按照 timestamp 的递增顺序进行。
 * 2 <= memoryLimit <= 10^5
 * 1 <= source, destination <= 2 * 10^5
 * 1 <= timestamp <= 10^9
 * 1 <= startTime <= endTime <= 10^9
 * addPacket、forwardPacket 和 getCount 方法的总调用次数最多为 10^5。
 * 对于 addPacket 的查询，timestamp 按递增顺序给出。
 */
public class Router {

    private class Packet {
        private int source;
        private int destination;
        private int timestamp;

        public Packet(int source, int destination, int timestamp) {
            this.source = source;
            this.destination = destination;
            this.timestamp = timestamp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, destination, timestamp);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Packet packet = (Packet) obj;
            return source == packet.source && destination == packet.destination && timestamp == packet.timestamp;
        }
    }

    private class Pair {
        private List<Integer> timestamps;
        private int head;

        public Pair() {
            timestamps = new ArrayList<>();
            head = 0;
        }
    }

    private final int memoryLimit;
    private Queue<Packet> queue;
    private Set<Packet> set;
    private Map<Integer, Pair> map;
    
    //初始化路由器对象，并设置固定的内存限制。
    //memoryLimit 是路由器在任意时间点可以存储的 最大 数据包数量。
    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        queue = new LinkedList<>();
        set = new HashSet<>();
        map = new HashMap<>();
    }

    //将具有给定属性的数据包添加到路由器。
    //如果路由器中已经存在一个具有相同 source、destination 和 timestamp 的数据包，则视为重复数据包。
    //如果数据包成功添加（即不是重复数据包），返回 true；否则返回 false
    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);
        if (!set.add(packet)) {
            return false;
        }
        if (queue.size() == memoryLimit) {
            forwardPacket();
        }
        queue.offer(packet);
        map.computeIfAbsent(destination, k -> new Pair()).timestamps.add(timestamp);
        return true;
    }

    //以 FIFO（先进先出）顺序转发下一个数据包。
    //从存储中移除该数据包。
    //以数组 [source, destination, timestamp] 的形式返回该数据包。
    //如果没有数据包可以转发，则返回空数组。
    public int[] forwardPacket() {
        if (queue.isEmpty()) {
            return new int[]{};
        }
        Packet packet = queue.poll();
        set.remove(packet);
        Pair pair = map.get(packet.destination);
        pair.head++;
        return new int[]{packet.source, packet.destination, packet.timestamp};
    }

    //返回当前存储在路由器中（即尚未转发）的，且目标地址为指定 destination 且时间戳在范围 [startTime, endTime]（包括两端）内的数据包数量。
    public int getCount(int destination, int startTime, int endTime) {
        Pair pair = map.get(destination);
        if (pair == null) {
            return 0;
        }
        int left = binarySearch(pair.timestamps, startTime, pair.head);
        int right = binarySearch(pair.timestamps, endTime + 1, pair.head);
        return right - left;
    }

    private int binarySearch(List<Integer> list, int target, int left) {
        int right = list.size() - 1, res = list.size();
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (list.get(mid) >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
