package com.sean.lintcode;

import java.util.concurrent.CountDownLatch;

public class LintCode2504 {

    public static class Main {
        public static void parseLog(int num) {

        }
    }

    public static void createLog() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(16);
        for (int i = 1; i < 17; i++) {
            final int temp = i;
            Thread thread = new Thread(() -> {
                try {
                    Main.parseLog(temp);
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
    }

}
