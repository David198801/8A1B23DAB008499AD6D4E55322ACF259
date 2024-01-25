常见JAVA类概念介绍

PO：持久对象 (persistent object)。

是ORM(Objevt Relational Mapping)框架中Entity，PO属性和数据库中表的字段形成一一对应关系。

VO：值对象(Value Object)。

表现层对象(View Object)，通常用于业务层之间的数据传递，由new创建，由GC回收。和PO一样也是仅仅包含数据而已，但应是抽象出的业务对象，可以和表对应，也可以不是。

BO：业务对象层的缩写(Business Object)。

BO把业务逻辑封转为一个对象，通过调用DAO方法，结合PO或VO进行业务操作。PO组合，如投保人是一个PO，被保险人是一个PO，险种信息是一个PO等等，他们组合气来是第一张保单的BO。

DTO：数据传输对象(Data Transfer Object)。

就是接口之间传递的数据封装，DTO作用，一是能提高数据传输的速度(减少了传输字段)，二能隐藏后端表结构。类似与VO，用于页面展示时就是VO。

POJO：简单无规则java对象(Plain Ordinary Java Object)。

一个POJO持久化以后就是PO。直接用它传递、传递过程中就是DTO。直接用来对应表示层就是VO。

Entity：

实体，和PO的功能类似，和数据表一一对应，一个实体一张表。

JavaBean：

是一种Java语言写成的可重用组件,它的方法命名，构造及行为必须符合特定的约定。