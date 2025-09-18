package com.sean.leetcode.LeetCode3408;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-18 07:28
 * @Description https://leetcode.cn/problems/design-task-manager
 * 3408. 设计任务管理器
 * 一个任务管理器系统可以让用户管理他们的任务，每个任务有一个优先级。
 * 这个系统需要高效地处理添加、修改、执行和删除任务的操作。
 * 请你设计一个 TaskManager 类：
 * TaskManager(vector<vector<int>>& tasks) 初始化任务管理器，初始化的数组格式为 [userId, taskId, priority] ，表示给 userId 添加一个优先级为 priority 的任务 taskId 。
 * void add(int userId, int taskId, int priority) 表示给用户 userId 添加一个优先级为 priority 的任务 taskId ，输入 保证 taskId 不在系统中。
 * void edit(int taskId, int newPriority) 更新已经存在的任务 taskId 的优先级为 newPriority 。
 * 输入 保证 taskId 存在于系统中。
 * void rmv(int taskId) 从系统中删除任务 taskId 。
 * 输入 保证 taskId 存在于系统中。
 * int execTop() 执行所有用户的任务中优先级 最高 的任务，如果有多个任务优先级相同且都为 最高 ，执行 taskId 最大的一个任务。
 * 执行完任务后，taskId 从系统中 删除 。
 * 同时请你返回这个任务所属的用户 userId 。
 * 如果不存在任何任务，返回 -1 。
 * 注意 ，一个用户可能被安排多个任务。
 * 1 <= tasks.length <= 10^5
 * 0 <= userId <= 10^5
 * 0 <= taskId <= 10^5
 * 0 <= priority <= 10^9
 * 0 <= newPriority <= 10^9
 * add ，edit ，rmv 和 execTop 的总操作次数 加起来 不超过 2 * 10^5 次。
 * 输入保证 taskId 是合法的。
 */
public class TaskManager {

    //taskId -> (priority,userId)
    private Map<Integer, int[]> map;
    //(priority,taskId,userId)
    private PriorityQueue<int[]> heap;

    public TaskManager(List<List<Integer>> tasks) {
        map = new HashMap<>();
        heap = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
        for (List<Integer> task : tasks) {
            int userId = task.get(0), taskId = task.get(1), priority = task.get(2);
            add(userId, taskId, priority);
        }
    }

    //给用户 userId 添加一个优先级为 priority 的任务 taskId ，输入 保证 taskId 不在系统中
    public void add(int userId, int taskId, int priority) {
        map.put(taskId, new int[]{priority, userId});
        heap.offer(new int[]{priority, taskId, userId});
    }

    //更新已经存在的任务 taskId 的优先级为 newPriority
    public void edit(int taskId, int newPriority) {
        int userId = map.get(taskId)[1];
        add(userId, taskId, newPriority);
    }

    //从系统中删除任务 taskId
    public void rmv(int taskId) {
        map.get(taskId)[0] = -1;
    }

    public int execTop() {
        while (!heap.isEmpty()) {
            int[] task = heap.poll();
            int priority = task[0], taskId = task[1], userId = task[2];
            int[] arr = map.get(taskId);
            if (arr[0] == priority && arr[1] == userId) {
                rmv(taskId);
                return userId;
            }
        }
        return -1;
    }

}
