https://www.runoob.com/mongodb/nosql.html



Not Only SQL

泛指非关系型的数据库



特点：

1.易扩展，没有关系型的特征

2.大数据量高性能，一般的cache，数据更新就会失效，nosql的cache是记录级的，粒度更细，性能更高

3.数据模型多样灵活，无需建立字段，可以随时储存自定义的格式





数据库的瓶颈

1.数据总量大，一台机器放不下

2.数据库索引大，内存放不下

3.访问量大，一个实例无法承受



随着数据量增大，技术的发展

1.单机mysql

2.缓存+mysql多实例，垂直拆分

3.缓存+mysql多实例，主/从、读/写分离

4.缓存+水平拆分+集群

5.nosql



CAP定理（CAP theorem）

在计算机科学中, CAP定理（CAP theorem）, 又被称作 布鲁尔定理（Brewer's theorem）, 它指出对于一个分布式计算系统来说，不可能同时满足以下三点:

- 一致性(Consistency) (所有节点在同一时间具有相同的数据)

- 可用性(Availability) (保证每个请求不管成功或者失败都有响应)

- 分隔容忍(Partition tolerance) (系统中任意信息的丢失或失败不会影响系统的继续运作)

CAP理论的核心是：一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求，最多只能同时较好的满足两个。

因此，根据 CAP 原理将 NoSQL 数据库分成了满足 CA 原则、满足 CP 原则和满足 AP 原则三 大类：

- CA - 单点集群，满足一致性，可用性的系统，通常在可扩展性上不太强大。

- CP - 满足一致性，分区容忍性的系统，通常性能不是特别高。

- AP - 满足可用性，分区容忍性的系统，通常可能对一致性要求低一些。



BASE

BASE：Basically Available, Soft-state, Eventually Consistent。 由 Eric Brewer 定义。

CAP理论的核心是：一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求，最多只能同时较好的满足两个。

BASE是NoSQL数据库通常对可用性及一致性的弱要求原则:

- Basically Available --基本可用

- Soft-state --软状态/柔性事务。 "Soft state" 可以理解为"无连接"的, 而 "Hard state" 是"面向连接"的

- Eventually Consistency -- 最终一致性， 也是 ACID 的最终目的。





NoSQL 数据库分类

| 类型 | 部分代表 | 特点 |
| - | - | - |
| 列存储 | Hbase<br>Cassandra<br>Hypertable | 顾名思义，是按列存储数据的。最大的特点是方便存储结构化和半结构化数据，方便做数据压缩，对针对某一列或者某几列的查询有非常大的IO优势。 |
| 文档存储 | MongoDB<br>CouchDB | 文档存储一般用类似json的格式存储，存储的内容是文档型的。这样也就有机会对某些字段建立索引，实现关系数据库的某些功能。 |
| key-value存储 | Tokyo Cabinet / Tyrant<br>Berkeley DB<br>MemcacheDB<br>Redis | 可以通过key快速查询到其value。一般来说，存储不管value的格式，照单全收。（Redis包含了其他功能） |
| 图存储 | Neo4J<br>FlockDB | 图形关系的最佳存储。使用传统关系数据库来解决的话性能低下，而且设计使用不方便。 |
| 对象存储 | db4o<br>Versant | 通过类似面向对象语言的语法操作数据库，通过对象的方式存取数据。 |
| xml数据库 | Berkeley DB XML<br>BaseX | 高效的存储XML数据，并支持XML的内部查询语法，比如XQuery,Xpath。 |


