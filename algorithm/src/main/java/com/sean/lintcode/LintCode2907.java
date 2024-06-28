package com.sean.lintcode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class LintCode2907 {

    public static class Solution {
        static String[] day = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        static Map<String, String> map = new ConcurrentHashMap<>();

        static Thread[] getWeekDay() throws Exception {
            Thread[] weekDay = new Thread[7];
            LocalDate startDate = LocalDate.of(2021, 10, 8);
            for (int i = 0; i < weekDay.length; i++) {
                int finalI = i;
                weekDay[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LocalDate startTime = startDate.plusDays(finalI * 1000);
                        for (int j = 0; j < 1000; j++) {
                            String key = startTime.format(DateTimeFormatter.ofPattern("yyyy-M-d"));
                            String value = day[startTime.getDayOfWeek().getValue() == 7 ? 0 : startTime.getDayOfWeek().getValue()];
                            map.put(key, value);
                            startTime = startTime.plusDays(1);
                        }
                    }
                });
            }
            return weekDay;
        }
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        Thread[] thread = Solution.getWeekDay();
        for (Thread t : thread) {
            t.start();
        }
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        TreeMap<String, String> rmap = new TreeMap<>(Solution.map);
        System.out.println("Time limit 200ms: " + ((end - start) < 240));
        System.out.println("Solution.map.size() = " + Solution.map.size());
        System.out.println("Solution.map = " + rmap);
    }

}
