package com.ww.algorithm.HashTable;

public class Person {
    String name;
    int age;
    Double salary;

    Person(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    //Java默认使用对象的引用地址做hashCode，比较的是引用地址，new出来2个对象，hashCode一定不同也就一定不等。使用HashSet或HashMap时一定需注意。
    //自定义重写Person对象的hashCode方法，忽略字符大小写，对值进行哈希。
    @Override
    public int hashCode() {
        int M = 97;
        int hash = 0;
        //name相同判断，不区分大小写
        hash = hash * M + name.toLowerCase().hashCode();
        hash = hash * M + ((Integer) age).hashCode();
        hash = hash * M + salary.hashCode();
        return hash;
    }

    //自定义重写Person对象的equals方法：如果2个对象hashCode相同(哈希冲突)时，再进行值比较看看是否真的相同
    @Override
    public boolean equals(Object o) {

        if (o == null) {//如果要比较的对象是null，无需比较，直接返回不同
            return false;
        } else if (this == o) {//如果引用地址相同，肯定是相同的对象
            return true;
        } else if (getClass() != o.getClass()) {//如果要比较的2个对象类不同，直接返回不同
            return false;
        } else {//如果上述情况都不是，入参Object强制类型转换为Person对象，对每个属性逐个比较
            Person oPerson = (Person) o;
            return this.name.toLowerCase().equals(oPerson.name.toLowerCase()) &&
                    this.age == oPerson.age &&
                    this.salary.equals(oPerson.salary);
        }
    }
}
