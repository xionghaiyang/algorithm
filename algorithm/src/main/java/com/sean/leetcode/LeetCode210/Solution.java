package com.sean.leetcode.LeetCode210;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-06-26 08:33
 * @Description https://leetcode.cn/problems/course-schedule-ii
 * 210. 课程表 II
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。
 * 给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。
 * 可能会有多个正确的顺序，你只要返回 任意一种 就可以了。
 * 如果不可能完成所有课程，返回 一个空数组 。
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 */
public class Solution {

    public class Course {
        int index;
        int in;
        List<Course> nexts;

        public Course(int index) {
            this.index = index;
            in = 0;
            nexts = new ArrayList<>();
        }

        public void add(Course that) {
            this.nexts.add(that);
            that.in++;
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }
        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[0];
            int b = prerequisite[1];
            courses[b].add(courses[a]);
        }
        Queue<Course> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].in == 0) {
                queue.offer(courses[i]);
            }
        }
        int[] res = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            Course cur = queue.poll();
            res[index++] = cur.index;
            for (Course next : cur.nexts) {
                if (--next.in == 0) {
                    queue.offer(next);
                }
            }
        }
        return index == numCourses ? res : new int[]{};
    }

}
