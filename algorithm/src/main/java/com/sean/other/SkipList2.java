package com.sean.other;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-05 14:29
 * @Description: 跳表
 */
public class SkipList2 {

    //跳表的节点
    public class SkipListNode {
        //跳表节点的值，在实际应用中节点类可以加个泛型，这里为了方便介绍，直接用int类型
        public int val;

        public SkipListNode(int val) {
            this.val = val;
        }

        //普通的链表这里是next节点，而跳表这里需要记录每一层的节点，所以是数组
        SkipListNode[] next = new SkipListNode[MAX_LEVEL];
    }

    //跳表的最大层数
    private int MAX_LEVEL = 16;
    //当前跳表的最大层级
    private int curLevelCount = 1;
    //初始化定义一个头节点，指向最上面一层的第一个元素，头节点不存储任何数据
    private SkipListNode head;

    public SkipList2() {//构造函数
        head = new SkipListNode(-1);
    }

    //打印跳表的值
    public void printLinked(SkipList2 skipList) {
        SkipListNode cur = skipList.head;
        for (int i = skipList.curLevelCount - 1; i >= 0; i--) {
            //这里是横向查找，就是当前层往后面查找，这里是第i层
            SkipListNode pre = cur;
            while (pre.next[i] != null) {
                System.out.print(pre.next[i].val + ",");
                pre = pre.next[i];
            }
            System.out.println();
        }
    }

    //查找值为target的节点
    public boolean search(int target) {
        SkipListNode pre = head;
        //这里for循环是逆序的，他是从最上面一层开始查找，for循环是纵向查找，里面的while循环是横向查找
        for (int i = curLevelCount - 1; i >= 0; i--) {
            //这里是横向查找，就是当前层往后面查找，这里是第i层
            while (pre.next[i] != null && pre.next[i].val < target) {
                pre = pre.next[i];
            }
            //如果在当前层查找到，直接返回true
            if (pre.next[i] != null && pre.next[i].val == target) {
                return true;
            }
        }
        return false;//没查找到，返回false
    }

    //索引层级随机函数
    private int randLevel() {
        int level = 1;//1的概率是0.5,2的概率是0.25,3的概率是0.125,4的概率是0.0625,......
        while (Math.random() < 0.5f && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    //添加数据
    public void add(int num) {
        int level = randLevel();//需要插入到第几层
        //每一层初始化默认为head节点
        SkipListNode[] preNodes = new SkipListNode[level];
        for (int i = 0; i < level; i++) {
            preNodes[i] = head;
        }
        //找到每一层待插入节点的前一个节点
        SkipListNode pre = head;
        //从当前层往下每层都要插入该节点
        for (int i = level - 1; i >= 0; i--) {
            while (pre.next[i] != null && pre.next[i].val < num) {
                pre = pre.next[i];
            }
            preNodes[i] = pre;
        }
        //创建新节点
        SkipListNode node = new SkipListNode(num);
        //把新建节点node插到该层以及下面的所有层
        for (int i = level - 1; i >= 0; i--) {
            //链表的插入，参见单向链表的插入
            node.next[i] = preNodes[i].next[i];
            preNodes[i].next[i] = node;
        }
        if (level > curLevelCount) {//更新跳表的层级
            curLevelCount = level;
        }
    }

    public boolean remove(int num) {
        //删除链表和插入链表类似，都是需要先找到插入或删除链表的前一个节点
        SkipListNode[] preNodes = new SkipListNode[curLevelCount];
        int topIndex = -1;//从当前层往下都要移除
        SkipListNode pre = head;
        for (int i = curLevelCount - 1; i >= 0; i--) {
            while (pre.next[i] != null && pre.next[i].val < num) {
                pre = pre.next[i];
            }
            if (pre.next[i] != null && pre.next[i].val == num && topIndex == -1) {
                topIndex = i;
            }
            preNodes[i] = pre;//记录每层待删除节点的前一个节点
        }
        if (topIndex == -1) {//如果没找到，也就是待删除的节点在跳表中不存在，直接返回
            return false;
        }
        //删除操作
        for (int i = topIndex; i >= 0; i--) {
            preNodes[i].next[i] = preNodes[i].next[i].next[i];
        }
        //更新索引层数，如果当前层消失了，curLevelCount要减1
        while (curLevelCount > 1 && head.next[curLevelCount - 1] == null) {
            curLevelCount--;
        }
        return true;
    }

}
