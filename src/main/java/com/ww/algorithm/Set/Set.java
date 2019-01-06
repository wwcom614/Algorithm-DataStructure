package com.ww.algorithm.Set;

//集合接口：定义集合的通用能力方法
public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();


}
