package com.ww.algorithm.SegmentTree;

//定义一个接口，让调用者来实现：对两个子节点数据如何融合成父节点数据的业务逻辑
public interface Merger<E> {
    E merge(E a, E b);
}
