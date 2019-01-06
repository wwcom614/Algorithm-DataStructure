package com.ww.algorithm.BST;

import lombok.extern.java.Log;
import org.junit.Before;
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
public class BSTTreeTest {
    private BSTTree<Integer> bstTree;

    @Before
    public void a_runBeforeEachClass() {
        bstTree = new BSTTree<Integer>();
        int[] array = {28, 16, 30, 13, 22, 29, 42};
        for (int i : array) {
            bstTree.add(i);
        }
        System.out.println(bstTree);
        System.out.println();
    }

/*
28
--16
----13
------null
------null
----22
------null
------null
--30
----29
------null
------null
----42
------null
------null
*/

    @Test
    public void b1_preOrderTraverse() throws Exception {
        bstTree.preOrderTraverse();
        System.out.println();
    }
/*
【preOrderTraverse】：28
【preOrderTraverse】：16
【preOrderTraverse】：13
【preOrderTraverse】：22
【preOrderTraverse】：30
【preOrderTraverse】：29
【preOrderTraverse】：42
  */

    @Test
    public void b2_preOrderTraverseByStack() throws Exception {
        bstTree.preOrderTraverseByStack();
        System.out.println();
    }
/*
【preOrderTraverseByStack】：28
【preOrderTraverseByStack】：16
【preOrderTraverseByStack】：13
【preOrderTraverseByStack】：22
【preOrderTraverseByStack】：30
【preOrderTraverseByStack】：29
【preOrderTraverseByStack】：42
  */

    @Test
    public void c_midOrderTraverse() throws Exception {
        bstTree.midOrderTraverse();
        System.out.println();
    }
/*
【midOrderTraverse】：13
【midOrderTraverse】：16
【midOrderTraverse】：22
【midOrderTraverse】：28
【midOrderTraverse】：29
【midOrderTraverse】：30
【midOrderTraverse】：42
*/

    @Test
    public void d_postOrderTraverse() throws Exception {
        bstTree.postOrderTraverse();
        System.out.println();
    }
/*
【postOrderTraverse】：13
【postOrderTraverse】：22
【postOrderTraverse】：16
【postOrderTraverse】：29
【postOrderTraverse】：42
【postOrderTraverse】：30
【postOrderTraverse】：28
*/

    @Test
    public void e_levelOrderTraverse() throws Exception {
        bstTree.levelOrderTraverse();
        System.out.println();
    }
/*
【levelOrderTraverse】：28
【levelOrderTraverse】：16
【levelOrderTraverse】：30
【levelOrderTraverse】：13
【levelOrderTraverse】：22
【levelOrderTraverse】：29
【levelOrderTraverse】：42
*/

    @Test
    public void f_getSize() throws Exception {
        log.info("【getSize】:" + bstTree.getSize());
    }//【getSize】:7

    @Test
    public void g_isEmpty() throws Exception {
        log.info("【isEmpty】:" + bstTree.isEmpty());
    }//【isEmpty】:false

    @Test
    public void h_contains() throws Exception {
        log.info("【contains(30)】:" + bstTree.contains(30));
    }//【contains(30)】:true

    @Test
    public void i1_getMin() {
        log.info("【getMin】:" + bstTree.getMin());
    }//【getMin】:13

    @Test
    public void i2_getMax() {
        log.info("【getMax】:" + bstTree.getMax());
    }//【getMax】:42

    @Test
    public void j1_removeMin() {
        log.info("【removeMin】:" + bstTree.removeMin());
        bstTree.midOrderTraverse();
    }
/*
【removeMin】:13
【midOrderTraverse】：16
【midOrderTraverse】：22
【midOrderTraverse】：28
【midOrderTraverse】：29
【midOrderTraverse】：30
【midOrderTraverse】：42
*/

    @Test
    public void j2_removeMax() {
        log.info("【removeMax】:" + bstTree.removeMax());
        bstTree.midOrderTraverse();
    }
/*
【removeMax】:42
【midOrderTraverse】：13
【midOrderTraverse】：16
【midOrderTraverse】：22
【midOrderTraverse】：28
【midOrderTraverse】：29
【midOrderTraverse】：30
*/

    @Test
    public void j3_remove() {
        bstTree.remove(30);
        log.info("【remove(30)】:");
        System.out.println(bstTree);
        System.out.println();
    }
/*
【remove(30)】:
28
--16
----13
------null
------null
----22
------null
------null
--42
----29
------null
------null
----null
*/

}