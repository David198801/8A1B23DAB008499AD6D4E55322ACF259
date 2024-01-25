LINK : warning LNK4044: unrecognized option "z2"; ignored



LINK : error : Segment reference in fixup recordtest.obj : fatal error



LNK1123: failure during conversion to COFF: file invalid or corrupt



Windows平台下,源代码编译出来的obj主要有两种格式:OMF和COFF



DOS程序编译的obj文件都是omf格式的,omf格式的obj要用Segmented Linker链接,生成的是DOS可执行文件或者NE文件,



而Win32程序编译的obj必须是COFF格式的,要用Incremental Linker链接, 生成的是32位的PE或16/32位混合的LE文件



 masm编译器ml.exe有两个选项 /coff 和 /omf 其中, /coff控制生成的obj为coff格式, /omf类似



在ml的第6版中, 默认选项为omf, 如果编译win32程序忘了指定/coff会出错.



在ml的第7和第8版中, 默认选项为coff, 这个时候如果编译Dos程序忘了指定/omf会出错的。

