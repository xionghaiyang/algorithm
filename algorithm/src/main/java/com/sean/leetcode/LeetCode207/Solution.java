package com.sean.leetcode.LeetCode207;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-06-11 11:28
 * @Description https://leetcode.cn/problems/course-schedule
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。
 * 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
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

        public void add(Course course) {
            this.nexts.add(course);
            course.in++;
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }
        for (int[] prerequisite : prerequisites) {
            courses[prerequisite[1]].add(courses[prerequisite[0]]);
        }
        int rest = numCourses;
        Queue<Course> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].in == 0) {
                queue.offer(courses[i]);
            }
        }
        while (rest > 0 && !queue.isEmpty()) {
            int size = queue.size();
            rest -= size;
            for (int i = 0; i < size; i++) {
                Course course = queue.poll();
                for (Course next : course.nexts) {
                    if (--next.in == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return rest == 0;
    }

}
