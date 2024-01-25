python3.3前使用wchar_t32位，3.3后使用动态1、2、4字节类型

可用\uxxxx表示

s = "\u4F60";，则s为"你"

s = r"\u4F60";，则s为"\\u4F60"



使用utf-8转义，则默认latin1解码

如s = '\xE4\xBD\xA0'，则s = "ä½ "