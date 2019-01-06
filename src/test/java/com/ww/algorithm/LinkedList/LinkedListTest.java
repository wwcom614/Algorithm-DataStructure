package com.ww.algorithm.LinkedList;

import lombok.extern.java.Log;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LinkedListTest {

    //泛型
    private static LinkedList<Integer> linkedList;

    @BeforeClass
    public static void a_runOnceBeforeClass() {
        linkedList = new LinkedList<>();
        for(int i=0; i<6; i++){
            linkedList.addFirst(i);
        }
        log.info("【linkedList】:" + linkedList);
    }//【linkedList】:size=6
    // 5->4->3->2->1->0->null

    @Test
    public void b_isEmpty() throws Exception {
        log.info("【isEmpty】:" + linkedList.isEmpty());
    }//【isEmpty】:false

    @Test
    public void c_add() throws Exception {
        linkedList.add(2, 222);
        log.info("【add(2, 222)】:" + linkedList);
    }//【add(2, 222)】:size=7
    //5->4->222->3->2->1->0->null

    @Test
    public void d_addLast() throws Exception {
        linkedList.addLast(888);
        log.info("【addLast(888)】:" + linkedList);
    }//【addLast(888)】:size=8
    //5->4->222->3->2->1->0->888->null

    @Test
    public void e_get() throws Exception {
        log.info("【get(2)】:" + linkedList.get(2));
    }//【get(2)】:222

    @Test
    public void f_getFirst() throws Exception {
        log.info("【getFirst】:" + linkedList.getFirst());
    }//【getFirst】:5

    @Test
    public void g_getLast() throws Exception {
        log.info("【getLast】:" + linkedList.getLast());
    }//【getLast】:888

    @Test
    public void h_set() throws Exception {
        linkedList.set(3,333);
        log.info("【set(3,333)】:" + linkedList);
    }//【set(3,333)】:size=8
    //5->4->222->333->2->1->0->888->null

    @Test
    public void i_contains() throws Exception {
        log.info("【contains(333)】:" + linkedList.contains(333));
    }//【contains(333)】:true

    @Test
    public void j_remove() throws Exception {
        log.info("【remove(2)】："+linkedList.remove(2));
        log.info("【remove(2)】:" + linkedList);
    }//remove(2)】：222
    //【remove(2)】:size=7
    // 5->4->333->2->1->0->888->null

    @Test
    public void k_removeFirst() throws Exception {
        log.info("【removeFirst】："+linkedList.removeFirst());
        log.info("【removeFirst】:" + linkedList);
    }//【removeFirst】：5
    //【removeFirst】:size=6
    // 4->333->2->1->0->888->null

    @Test
    public void m_removeLast() throws Exception {
        log.info("【removeLast】："+linkedList.removeLast());
        log.info("【removeLast】:" + linkedList);
    }//【removeLast】：888
    //【removeLast】:size=5
    // 4->333->2->1->0->null

    @Test
    public void n_removeFirstElement() throws Exception {
        linkedList.removeFirstElement(333);
        log.info("【removeFirstElement(333)】:" + linkedList);
    }
    //【removeFirstElement(333)】:size=5
    // 4->2->1->0->null
}