package com.sean.course03.lesson07;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-04-11 14:31
 * @Description 矩形覆盖
 */
public class Code04_CoverMax {

    public class Rectangle {
        public int up;
        public int down;
        public int left;
        public int right;

        public Rectangle(int up, int down, int left, int right) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }
    }

    public int maxCover(Rectangle[] recs) {
        if (recs == null || recs.length == 0) {
            return 0;
        }
        //根据down（底）排序
        Arrays.sort(recs, new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                return o1.down != o2.down ? (o1.down - o2.down) : o1.toString().compareTo(o2.toString());
            }
        });
        //可能会对底边的公共局域，产生影响的矩形
        TreeSet<Rectangle> leftOrdered = new TreeSet<>(new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                return o1.left != o2.left ? (o1.left - o2.left) : o1.toString().compareTo(o2.toString());
            }
        });
        int res = 0;
        //一次考察每一个矩形的底边
        for (int i = 0; i < recs.length; ) {
            //同样底边的矩形一批处理
            do {
                leftOrdered.add(recs[i++]);
            } while (i < recs.length && recs[i].down == recs[i - 1].down);
            //清除顶<=当前底的矩形
            removeLowerOnCurDown(leftOrdered, recs[i - 1].down);
            TreeSet<Rectangle> rightOrdered = new TreeSet<>(new Comparator<Rectangle>() {
                @Override
                public int compare(Rectangle o1, Rectangle o2) {
                    return o1.right != o2.right ? (o1.right - o2.right) : o1.toString().compareTo(o2.toString());
                }
            });
            for (Rectangle rec : leftOrdered) {
                removeLeftOnCurLeft(rightOrdered, rec.left);
                rightOrdered.add(rec);
                res = Math.max(res, rightOrdered.size());
            }
        }
        return res;
    }

    private void removeLowerOnCurDown(TreeSet<Rectangle> set, int curDown) {
        List<Rectangle> removes = new ArrayList<>();
        for (Rectangle rec : set) {
            if (rec.up <= curDown) {
                removes.add(rec);
            }
        }
        for (Rectangle rec : removes) {
            set.remove(rec);
        }
    }

    private void removeLeftOnCurLeft(TreeSet<Rectangle> set, int curLeft) {
        List<Rectangle> removes = new ArrayList<>();
        for (Rectangle rec : set) {
            if (rec.right > curLeft) {
                break;
            }
            removes.add(rec);
        }
        for (Rectangle rec : removes) {
            set.remove(rec);
        }
    }

}
