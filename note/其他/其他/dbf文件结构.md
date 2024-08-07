# [dbf文件结构](https://www.cnblogs.com/publiter/p/17187654.html)

.dbf 文件头结构如下：

0字节：表示当前的DBF版本信息
   该文件的值是十六进制’03’，表示是FoxBASE+/Dbase III plus, no memo
  
1~3字节：表示最新的更新日期，按照YYMMDD格式
   第一个字节的值 = 保存时的年 - 1900
   第二个字节的值 = 保存时的月
   第三个字节的值 = 保存时的日
   该文件的第一个字节是十六进制74，对应十进制116，116+1900正好等于当前的年2016
   第二个字节是十六进制07，对应十进制07，第三个字节是03，正好今天是7月3日

4~7字节：Int32类型，表示DBF文件中有多少条记录
   可以看到值是02，正好当前文件确实只有两条记录

8~9字节：Int16类型，表示当前DBF的文件头占用的字节长度
   该文件对应的值是十六进制的A1，对应十进制161

10~11字节：Int16类型，表示一条记录中的字节长度，即每行数据所占的长度
   该文件的值是十六进制数3A，对应十进制的58
   创建该文件时，一共4个字段，分别长度是20、8、8、21
   计算4个列的长度和是57，比58少1
   多出来的一个字节是每条记录最开始的特殊标志字节

12~13字节：保留字节，用于以后添加新的说明性信息时使用，这里用0来填写

14字节：表示未完成的操作

15字节：dBASE IV编密码标记

16~27字节：保留字节用于多用户处理时使用

28字节：DBF文件的MDX标识
   创建一个DBF表时，若使用MDX格式的索引文件，则DBF表头中该字节就自动被设置一个标志
   当你下次试图重新打开这个DBF表的时候，数据引擎会自动识别这个标识
   如果此标示为真，则数据引擎将试图打开相应MDX文件

29字节：页码标记

30~31字节：保留字节，用于以后添加新的说明性信息时使用，这里用0来填写。

32~N(x * 32)，这段长度由表格中的列数（即字段数）决定
   每个字段的长度为32，如果有x列，则占用的长度为x * 32
   这每32个字节里面又按其规则填写每个字段的名称、类型等信息

N+1字节：作为字段定义的终止标志，值为0x0D


=====================================================
ArcGIS中.dbf文件第30个字节表示Language driver ID，代表的编码页如下：

ID    Codepage  Description
1  0x01  437  US MS-DOS
2  0x02  850  International MS-DOS
3  0x03  1252  Windows ANSI Latin I
4  0x04  10000  Standard Macintosh
8  0x08  865  Danish OEM
9  0x09  437  Dutch OEM
10  0x0A  850  Dutch OEM*
11  0x0B  437  Finnish OEM
13  0x0D  437  French OEM
14  0x0E  850  French OEM*
15  0x0F  437  German OEM
16  0x10  850  German OEM*
17  0x11  437  Italian OEM
18  0x12  850  Italian OEM*
19  0x13  932  Japanese Shift-JIS
20  0x14  850  Spanish OEM*
21  0x15  437  Swedish OEM
22  0x16  850  Swedish OEM*
23  0x17  865  Norwegian OEM
24  0x18  437  Spanish OEM
25  0x19  437  English OEM (Great Britain)
26  0x1A  850  English OEM (Great Britain)*
27  0x1B  437  English OEM (US)
28  0x1C  863  French OEM (Canada)
29  0x1D  850  French OEM*
31  0x1F  852  Czech OEM
34  0x22  852  Hungarian OEM
35  0x23  852  Polish OEM
36  0x24  860  Portuguese OEM
37  0x25  850  Portuguese OEM*
38  0x26  866  Russian OEM
55  0x37  850  English OEM (US)*
64  0x40  852  Romanian OEM
77  0x4D  936  Chinese GBK (PRC)
78  0x4E  949  Korean (ANSI/OEM)
79  0x4F  950  Chinese Big5 (Taiwan)
80  0x50  874  Thai (ANSI/OEM)
87  0x57  Current ANSI CP  ANSI
88  0x58  1252  Western European ANSI
89  0x59  1252  Spanish ANSI
100  0x64  852  Eastern European MS-DOS
101  0x65  866  Russian MS-DOS
102  0x66  865  Nordic MS-DOS
103  0x67  861  Icelandic MS-DOS
104  0x68  895  Kamenicky (Czech) MS-DOS
105  0x69  620  Mazovia (Polish) MS-DOS
106  0x6A  737  Greek MS-DOS (437G)
107  0x6B  857  Turkish MS-DOS
108  0x6C  863  French-Canadian MS-DOS
120  0x78  950  Taiwan Big 5
121  0x79  949  Hangul (Wansung)
122  0x7A  936  PRC GBK
123  0x7B  932  Japanese Shift-JIS
124  0x7C  874  Thai Windows/MS–DOS
134  0x86  737  Greek OEM
135  0x87  852  Slovenian OEM
136  0x88  857  Turkish OEM
150  0x96  10007  Russian Macintosh
151  0x97  10029  Eastern European Macintosh
152  0x98  10006  Greek Macintosh
200  0xC8  1250  Eastern European Windows
201  0xC9  1251  Russian Windows
202  0xCA  1254  Turkish Windows
203  0xCB  1253  Greek Windows
204  0xCC  1257  Baltic Windows