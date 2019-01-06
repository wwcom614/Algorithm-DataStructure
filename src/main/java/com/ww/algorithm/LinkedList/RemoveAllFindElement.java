package com.ww.algorithm.LinkedList;


public class RemoveAllFindElement {

    //循环迭代方式遍历链表
    public LinkedListNode removeAllFindElementByLoop(LinkedListNode head, int findValue) {

        LinkedListNode dummyHead = new LinkedListNode(-1);
        dummyHead.next = head;

        LinkedListNode prev = dummyHead;
        while (prev.next != null) {//还未到达链表末尾
            if (prev.next.val == findValue) {
                //prev.next的节点中存储的就是要删除的值findValue，于是prev.next直接跳过该节点
                prev.next = prev.next.next;
            } else {//prev.next的节点中存储的值不是要删除的值findValue，于是移动到下一个节点重复上述过程
                prev = prev.next;
            }
        }
        //都删除完毕了，返回该链表的头节点
        return dummyHead.next;
    }

    //递归方式遍历链表。depth是为了方便打印递归过程加入的参数，真正使用时可去除。
    public LinkedListNode removeAllFindElementByRecursion(LinkedListNode head, int findValue, int depth) {

        String depthString = generateDepthString(depth);

        System.out.print(depth);
        System.out.println("【Call】 remove：" + findValue + " in " + head);

        if (head == null) {//先求解最基本的问题，确保递归终止条件
            System.out.print(depth);
            System.out.println("【Return】" + head);
            return head;
        } else {//把原问题转化为更小的问题，还是方法调用，不是A调用B，是A调用A自己而已
            LinkedListNode resultNode = removeAllFindElementByRecursion(head.next, findValue, depth+1);
            System.out.print(depth);
            //如下是在最后一次递归后再执行的
            //如果head节点满足删除条件，那么返回的是head后面的resultNode
            if (head.val == findValue) {
                System.out.println("【Back】 remove：" + findValue + ", LinkList：" + resultNode);
                return resultNode;
            } else {//head不满足删除条件无需删除，那么就把后面删除好的链表给head即可
                head.next = resultNode;
                System.out.println("【Back】 remove：" + findValue + ", LinkList：" + head);
                return head;
            }
            //简化写法
            //head.next = removeAllFindElementByRecursion(head.next, findValue);
            //return head.val==findValue ? head.next : head;
        }
    }

    //方便查看每步递归调用打印日志
    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<depth; i++){
            sb.append("--");
        }
        return sb.toString();
    }
}
