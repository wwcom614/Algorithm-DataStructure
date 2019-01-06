package com.ww.algorithm.LinkedList;

import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class RemoveAllFindElementTest {
    private LinkedListNode head;

    @Before
    public void initBeforeEachTest(){
        int[] array = {1,2,6,3,4,5,6};
        head = new LinkedListNode(array);
    }

    @Test
    public void removeAllFindElementByLoop() throws Exception {
        log.info("【removeAllFindElementByLoop】Remove Before："+ head);
        //【removeAllFindElementByLoop】Remove Before：Front[1->2->6->3->4->5->6->null]Tail

        LinkedListNode afterRemove = (new RemoveAllFindElement()).removeAllFindElementByLoop(head, 6);

        log.info("【removeAllFindElementByLoop】Remove After："+ afterRemove);
        //【removeAllFindElementByLoop】Remove After：Front[1->2->3->4->5->null]Tail
    }

    @Test
    public void removeAllFindElementByRecursion() throws Exception {
        log.info("【removeAllFindElementByRecursion】Remove Before："+ head);
        //【removeAllFindElementByRecursion】Remove Before：Front[1->2->6->3->4->5->6->null]Tail

        LinkedListNode afterRemove = (new RemoveAllFindElement()).removeAllFindElementByRecursion(head, 6, 0);

        log.info("【removeAllFindElementByRecursion】Remove After："+ afterRemove);
        //【removeAllFindElementByRecursion】Remove After：Front[1->2->3->4->5->null]Tail
        /*
        0【Call】 remove：6 in Front[1->2->6->3->4->5->6->null]Tail
        1【Call】 remove：6 in Front[2->6->3->4->5->6->null]Tail
        2【Call】 remove：6 in Front[6->3->4->5->6->null]Tail
        3【Call】 remove：6 in Front[3->4->5->6->null]Tail
        4【Call】 remove：6 in Front[4->5->6->null]Tail
        5【Call】 remove：6 in Front[5->6->null]Tail
        6【Call】 remove：6 in Front[6->null]Tail
        7【Call】 remove：6 in null
        7【Return】null
        6【Back】 remove：6, LinkList：null
        5【Back】 remove：6, LinkList：Front[5->null]Tail
        4【Back】 remove：6, LinkList：Front[4->5->null]Tail
        3【Back】 remove：6, LinkList：Front[3->4->5->null]Tail
        2【Back】 remove：6, LinkList：Front[3->4->5->null]Tail
        1【Back】 remove：6, LinkList：Front[2->3->4->5->null]Tail
        0【Back】 remove：6, LinkList：Front[1->2->3->4->5->null]Tail
        */
    }

}