char，使用utf16(bmp)

辅助平面使用char数组表示



String，使用char数组属性表示(jdk9开始使用byte数组)



可用\u表示

char c = '\u4F60';，则c为'你'

String s = "\u4F60";，则s为"你"



new String(byte[])解码默认编码：latin1