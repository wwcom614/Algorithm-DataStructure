package com.ww.algorithm.Array;

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
public class ArrayTest {
    //泛型
    private static Array<Integer> arr;

    @BeforeClass
    public static void a_runOnceBeforeClass() {
        arr = new Array<>(5);
        for(int i=0; i<10; i++){
            arr.addLast(i);
        }
        log.info("【arr】:" + arr);
    }

    @Test
    public void b_getSize() throws Exception {
        log.info("【getSize】:" + arr.getSize());
        log.info("【arr】:" + arr);
    }//【getSize】:10

    @Test
    public void c_isEmpty() throws Exception {
        log.info("【isEmpty】:" + arr.isEmpty());
    }//【isEmpty】:false

    @Test
    public void d_add() throws Exception {
        arr.add(1,111);
        log.info("【add(1,111)】:" + arr);
    }//【add(1,111)】:Array: size=11, capacity=20
    //[0, 111, 1, 2, 3, 4, 5, 6, 7, 8, 9]

    @Test
    public void e_getCapacity() throws Exception {
        log.info("【getCapacity】:" + arr.getCapacity());
    }//【getCapacity】:20

    @Test
    public void f_addLast() throws Exception {
        arr.addLast(1000);
        log.info("【addLast(1000)】:" + arr);
    }//【addLast(1000)】:Array: size=12, capacity=20
    // [0, 111, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1000]

    @Test
    public void g_addFirst() throws Exception {
        arr.addFirst(-2);
        log.info("【addFirst(-2)】:" + arr);
    }//【addFirst(-2)】:Array: size=13, capacity=20
    // [-2, 0, 111, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1000]

    @Test
    public void h_get() throws Exception {
        log.info("【get(2)】:" + arr.get(2));
    }//【get(2)】:111

    @Test
    public void i_getLast() throws Exception {
        log.info("【getLast】:" + arr.getLast());
    }//【getLast】:1000

    @Test
    public void j_getFirst() throws Exception {
        log.info("【getFirst】:" + arr.getFirst());
    }//【getFirst】:-2

    @Test
    public void k_set() throws Exception {
        arr.set(5,555);
        log.info("【set(5,555)】:" + arr);
    }//【set(5,555)】:Array: size=13, capacity=20
    //[-2, 0, 111, 1, 2, 555, 4, 5, 6, 7, 8, 9, 1000]

    @Test
    public void l_contains() throws Exception {
        log.info("【contains(111)】:" + arr.contains(111));
    }//【contains(111)】:true

    @Test
    public void m_findFirstElement() throws Exception {
        log.info("【findFirstElement(7)】:" + arr.findFirstElement(7));
        log.info("【findFirstElement(7)】:" + arr);
    }//【findFirstElement(7)】:9
    //【findFirstElement(7)】:Array: size=13, capacity=20
    // [-2, 0, 111, 1, 2, 555, 4, 5, 6, 7, 8, 9, 1000]

    @Test
    public void n_remove() throws Exception {
        log.info("【remove(1)】:" + arr.remove(1));
        log.info("【remove(2)】:" + arr.remove(2));
        log.info("【remove(3)】:" + arr.remove(3));
        log.info("【remove(1,2,3)】:" + arr);
    }//【remove(1)】:0
    //【remove(2)】:1
    //【remove(3)】:555
    //【remove(1,2,3)】:Array: size=10, capacity=20
    // [-2, 111, 2, 4, 5, 6, 7, 8, 9, 1000]

    @Test
    public void o_removeFirst() throws Exception {
        log.info("【removeFirst】:" + arr.removeFirst());
        log.info("【removeFirst】:" + arr.removeFirst());
        log.info("【removeFirst】:" + arr);
    }//【removeFirst】:-2
    //【removeFirst】:111
    //【removeFirst】:Array: size=8, capacity=20
    // [2, 4, 5, 6, 7, 8, 9, 1000]

    @Test
    public void p_removeLast() throws Exception {
        log.info("【removeLast】:" + arr.removeLast());
        log.info("【removeLast】:" + arr.removeLast());
        log.info("【removeLast】:" + arr);
    }//【removeLast】:1000
    //【removeLast】:9
    //【removeLast】:Array: size=6, capacity=20
    //[2, 4, 5, 6, 7, 8]

    @Test
    public void q_removeFirstElement() throws Exception {
        arr.removeFirstElement(8);
        log.info("【removeFirstElement(8)】:" + arr);
    }//【removeFirstElement(8)】:Array: size=5, capacity=10
    //  [2, 4, 5, 6, 7]

    @Test
    public void r_arrStudent() throws Exception {
    //验证泛型正确性
    Array<Student> arrStudent = new Array<>(10);
        arrStudent.addLast(new Student("ww1", 100.00));
        arrStudent.addLast(new Student("ww2", 66.88));
        arrStudent.addLast(new Student("ww3", 88.66));
        log.info("【arrStudent】:" +arrStudent);
    }//【arrStudent】:Array: size=3, capacity=10
    // [【Student】name=ww1, score=100.000000, 【Student】name=ww2, score=66.880000, 【Student】name=ww3, score=88.660000]
}