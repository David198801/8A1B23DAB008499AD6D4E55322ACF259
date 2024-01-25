Arrays.asList()返回无法改变长度的List



Arrays.asList() 返回的不是java.utils.ArrayList的对象，而是内部类Arrays$ArrayList，没有重写AbstractList中的add、remove，仅支持那些不会改变数组大小的操作