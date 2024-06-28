package com.sean.other;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-05 13:34
 * @Description: 跳表
 */
public class SkipList1 {

    //跳表的节点类
    public class SkipListNode {

        //跳表节点的值，在实际应用中节点类可以加个泛型，这里为了方便介绍，直接使用int 类型
        public int val;
        //指向后面一个节点
        public SkipListNode next;
        //指向下面一层的相同节点
        public SkipListNode down;

        public SkipListNode(int val, SkipListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //跳表的最大层数
    private int MAX_LEVEL = 16;
    //当前跳表的最大层级
    private int curLevelCount = 1;
    //初始化顶一个头节点，指向最上面一层的第一个元素，头节点不存储任何数据
    private SkipListNode head;

    //构造函数
    public SkipList1() {
        head = new SkipListNode(-1, null);
    }

    //打印跳表
    public void printLinked(SkipList1 skipList) {
        SkipListNode cur = skipList.head;
        while (cur != null) {
            SkipListNode tmp = cur.next;
            while (tmp != null) {
                System.out.print(tmp.val + ",");
                tmp = tmp.next;
            }
            System.out.println();
            cur = cur.down;
        }
    }

    //查找值为target的节点
    public boolean search(int target) {
        SkipListNode pre = head;
        while (pre != null) {
            if (pre.val < target) {//如果当前节点值小于target,则需要到右边查找，如果右边没有节点就到下边查找
                if (pre.next == null) {//右边没有节点，到下边查找
                    pre = pre.down;
                } else {
                    pre = pre.next.val > target ? pre.down : pre.next;
                }
            } else if (pre.val == target) {//如果找到直接返回
                return true;
            } else {//如果当前节点值大于target,说明没有，直接返回false
                return false;
            }
        }
        return false;
    }

    //索引层级随机函数
    private int randLevel() {
        int level = 1;
        while (Math.random() < 0.5f && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public void add(int num) {
        //从第几层开始插入，随机数
        int level = randLevel();
        //记录插入节点的前一个节点
        SkipListNode[] preNodes = new SkipListNode[level];
        //第一步：如果跳表层数比较少，在上面添加，层数至少为level。
        if (curLevelCount < level) {
            SkipListNode beforeHead = head;
            //更新head节点
            head = new SkipListNode(-1, null);
            SkipListNode curHead = head;
            //在上面添加每层的头节点
            for (int i = curLevelCount; i < level - 1; i++) {
                SkipListNode node = new SkipListNode(-1, null);
                curHead.down = node;
                curHead = node;
            }
            //最后创建的链表头节点和之前的头节点连在一起
            curHead.down = beforeHead;
        }
        //第二步：从上往下查找每层待插入节点的前一个节点
        SkipListNode pre = head;
        //上层不需要插入的跳过
        for (int i = curLevelCount - 1; i >= level; i--) {
            pre = pre.down;
        }
        //从当前层往下每层都要插入该节点，找出每层待插入节点的前一个节点
        for (int i = level - 1; i >= 0; i--) {
            while (pre.next != null && pre.next.val < num) {
                pre = pre.next;
            }
            //记录前一个节点
            preNodes[i] = pre;
            pre = pre.down;
        }
        //第三步：节点插入，插入的时候不光有next指针，而且还有down指针
        SkipListNode topNode = null;
        //把新建的节点node查到该层下面的每一层
        for (int i = level - 1; i >= 0; i--) {
            //创建节点
            SkipListNode node = new SkipListNode(num, preNodes[i].next);
            //链表的插入，参见单向链表的插入
            preNodes[i].next = node;
            //上下也要连接
            if (topNode != null) {
                topNode.down = pre;
            }
            topNode = node;
        }
        //更新跳表的层级，用来记录当前跳表的层级
        if (level > curLevelCount) {
            curLevelCount = level;
        }
    }

    public boolean remove(int num) {
        //删除链表和插入链表类似，都是需要先找到插入或删除链表的前一个节点
        int topIndex = -1;//从当前层开始往下每层都要删除
        //查找待删除节点的前一个节点，从上面一层开始查找
        SkipListNode pre = head;
        for (int i = curLevelCount - 1; i >= 0; i--) {
            while (pre.next != null && pre.next.val < num) {
                pre = pre.next;
            }
            //如果找到就终止查找，表示在当前层以及他下面的所有层都要删除该节点
            if (pre.next != null && pre.next.val == num && topIndex == -1) {
                topIndex = i;
                break;
            }
            if (pre.down == null) {//如果跳表中没有要删除的节点，返回false
                return false;
            }
            pre = pre.down;//当前层没找到就往下一层继续查找
        }
        if (topIndex == -1) {//如果跳表中没找到要删除的节点，返回false
            return false;
        }
        //从topIndex层开始，他下面的每一层都要删除
        for (int i = topIndex; i >= 0; i--) {
            if (pre.next != null) {//节点删除，参考单向链表的删除
                pre.next = pre.next.next;
            }
            pre = pre.down;//继续下一层的删除
            if (pre != null) {//找到待删除节点的前一个节点
                while (pre.next != null && pre.next.val != num) {
                    pre = pre.next;
                }
            }
        }
        //如果上面一层的节点被删除完了，要更新curLevelCount的值，还要更新head节点
        SkipListNode cur = head;
        while (curLevelCount > 1 && cur.next == null) {
            cur = cur.down;
            head = cur;
            curLevelCount--;
        }
        return true;
    }

}
