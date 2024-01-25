python2 str、使用ascii





python2 unicode、使用wchat_t32位

可用u"\uxxxx"表示

s = "\u4F60" 或 s = r"\u4F60"，则s为"\\u4F60"

s = u"\u4F60";，则s为"你"



使用utf-8转义，则输出bytes，若终端支持则可以显示

如s = '\xE4\xBD\xA0'，则可能输出"你"