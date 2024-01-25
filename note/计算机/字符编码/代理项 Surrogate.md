概述

代理项（Surrogate），是一种仅在 UTF-16 中用来表示补充字符的方法。在 UTF-16 中，为补充字符分配两个 16 位的 Unicode 代码单元：

- 第一个代码单元，被称为高代理项代码单元或前导代码单元；

- 第二个代码单元，被称为低代理项代码单元或尾随代码单元。

这两个代码单元组合在一起，就被称为代理项对。





相关术语

为了能更好地理解“代理项”这一概念，就需要先了解相关的 Unicode 术语：[1]

- 代码点（Code Point）：

1. Unicode 代码空间中的任何值，即从 0 到 10FFFF 的整数范围。但并非所有代码点都分配给编码字符。

1. 一个字符在任何编码字符集中的值或位置。

- 代码单元（Code Unit）：最小的数位组合，可以表示用于处理或交换的编码文本的单位。在 Unicode 标准中，UTF-8 编码格式采用 8 位编码单元，UTF-16 编码格式采用 16 位编码单元，UTF-32 编码格式采用 32 位编码单元。

- BMP 字符（BMP Character）：位于 BMP（Basic Multilingual Plane，多语种基本面）代码点的 Unicode 编码字符。

- BMP 代码点（BMP Code Point）：在 U+0000 到 U+FFFF 范围内的 Unicode 代码点。

- 补充字符（Supplementary Character）：位于补充代码点的 Unicode 编码字符。

- 补充代码点（Supplementary Code Point）：在 U+10000 到 U+10FFFF 范围内的 Unicode 代码点。

- 高代理项代码点（High-Surrogate Code Point）：在 U+D800 到 U+DBFF 范围内的 Unicode 代码点。

- 高代理项代码单元（High-Surrogate Code Unit）：在 D800 到 DBFF 范围内的 16 位代码单元，在 UTF-16 中用作代理项对的前导代码单元（Leading Code Unit）。

- 低代理项代码点（Low-Surrogate Code Point）：在 U+DC00 到 U+DFFF 范围内的 Unicode 代码点。

- 低代理项代码单元（Low-Surrogate Code Unit）：在 DC00 到 DFFF 范围内的 16 位代码单元，在 UTF-16 中用作代理项对的尾随代码单元（Trailing Code Unit）。

- 代理项对（Surrogate Pair）：由两个 16 位代码单元组成，其中第一个是高代理项代码单元，第二个是低代理项代码单元。

- 代理项字符（Surrogate Character）：用词不当。这表示一个编码字符只有一个代理项代码点，这是不可能的。所以请勿使用这个词。

特别说明：[2]

1. 高代理项代码点和低代理项代码点，仅指定用于此用途（即字面意义所指的用途，不作他用）。

1. 高代理项代码单元和低代理项代码单元，仅在 UTF-16 中使用。

1. 代理项对，仅在 UTF-16 中使用。

释义

关于代理项的概念，通俗来讲，就是为补充字符找两个“代理人”。由于补充字符体格壮硕，到了 UTF-16 这个地方就需要占用两个 16 位的座位。为了避免因“占座纠纷”导致意外发生，就需要为补充字符找来两个“代理人”，代替他来占用两个座位，这样就能皆大欢喜了。

- 两个“代理人”，就是高代理项和低代理项。

- 两个“代理人”占用的座位，就是 16 位的高代理项代码单元和低代理项代码单元。

- 两个“代理人”所在的位置，就是高代理项代码点和低代理项代码点。

- 两个“代理人”组合在一起，就是代理项对。

- “代理人”一定是出双入对的。单身的“代理人”是不能表示补充字符的，只能被称为未配对代理项（Unpaired Surrogate）。

代理项仅在 UTF-16 中用来表示补充字符，是指：

1. 不在 UTF-8 中使用。补充字符到了 UTF-8 这个地方就需要占用四个 8 位的座位，而实际给补充字符找的是两个 16 位的“代理人”，对不上号。

1. 不在 UTF-32 中使用。在 UTF-32 这个地方提供的座位，完全符合补充字符的体形，无需再为其另找“代理人”占座。

1. 不表示 BMP 字符。在补充字符没有出现时，并没有“代理人”这一概念，也就是说“代理人”并不是给 BMP 字符找的。

在 Java 中的应用

以下内容来自 Java API 官方文档中的《Character》一节：[3]

char 数据类型以及 Character 对象封装的值，都是基于最初的 Unicode 规范，该规范将字符定义为固定宽度的 16 位实体。随着 Unicode 标准的不断更新，超过 16 位的字符已被允许表示。合法代码点的范围已扩展到 U+10FFFF。

在 char 数组、String 类和 StringBuffer 类中，都采用 UTF-16 来表示字符。在这种表示法中，补充字符被表示为一对 char 值，第一个来自高代理项区间（\uD800 - \uDBFF），第二个来自低代理项区间（\uDC00 - \uDFFF）。

因此，char 值可表示 BMP 代码点、代理项代码点或 UTF-16 编码的代码单元。而所有的 Unicode 代码点，包括补充代码点，则用 int 值来表示。int 值中的低 21 位用来表示 Unicode 代码点，而高 11 位必须为零。

参考

1. ^Unicode 词汇表 http://www.unicode.org/glossary/

1. ^Unicode 标准 http://www.unicode.org/versions/Unicode13.0.0/ch03.pdf#G2630

1. ^Unicode 字符表示法 https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/lang/Character.html#unicode