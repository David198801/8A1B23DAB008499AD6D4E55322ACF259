常见加壳软件 及脱壳工具_YJ博客-CSDN博客_加壳软件

1.程序编写语言：

常见的程序制作语言有：

Borland Delphi 6.0 - 7.0

Microsoft Visual C++ 6.0

Microsoft Visual Basic 5.0 / 6.0

还有汇编、易语言等。 很多软件都通过加壳保护来提高软件的破解难度，下面我们简单的介绍一下加壳工具。



一些脱壳工具：http://down.52pojie.cn/Tools/Unpackers/

2.软件加壳工具介绍：

II 压缩壳介绍：



常见压缩壳有：ASPack、UPX、PeCompact、NsPack(国产北斗壳)等，压缩壳主要为了压缩软件本身的体积（压缩率）和压缩后软件运行的稳定性，谈不上有太多加密强度，脱壳比较简单。推荐那些需要压缩体积的软件使用。 Fly大哥在一蓑烟雨论坛上做了一个《压缩壳之王——The King of PE Compressor》的调查。该帖对压缩壳（大约31款）做了一个全面的整理和调查，从投票结果上来看目前UPX V2.X处在首位.

（!EP (ExE Pack) V1.0、32LiTE v0.02d、ASPack V2.12、bambam V0.01、BeRoEXEPacker V1.00、dePack V1.0、Dxpack V0.86、Exe32Pack V1.42、eXPressor V1.4.5.1、EZip V1.0、FSG V2.0、hmimys-Packer V1.0、KByS V0.28、MEW 11 SE V1.2、NeoLite V2.0、nPack V1.X、 Packman V1.0、PCShrink V0.71、PeCompact V2.7X、PE-PaCK V1.0、Petite V2.3、PeX V0.99b、 PKLite32 V1.1、SimplePack V1.X、Software Compress V1.4、UPX V2.X、WinUPack V0.39、WWPack32 V1.20等）

详见该帖：压缩壳之王——The King of PE Compressor： http://bbs.unpack.cn/thread-7912-1-1.html



III 加密壳介绍：



压缩壳主要就是针对软件的加密保护，也有一些人用加密壳来做一些木马的免杀，导致一些杀软件查到该壳就误报病毒，这点大家还是需要注意下的。加密壳的种类有很多，其中有不少精品，如果手工脱和修复，即使高手来脱，也是需要费很大力气的。

以下五款加密壳的知识介绍参考kanxue老师写的《加壳软件简介》一文。

1. ASProtect加密壳 俄国人Alexey Solodovnikov开发的一款强壳。官方站点www.aspack.com

ASProtect SKE系列己采用了部分虚拟机技术，主要是在Protect Original EntryPoint与SDK上。保护过程中建议大量里使用SDK， SDK使用请参考其帮助文档，在使用时注意SDK不要嵌套，并且同一组标签用在同一个子程序段里。ASProtect使用相当的简单，打开被保护的EXE/DLL文件后，选上保护的选项。再单击菜单Modes，单击Add Mode按钮，将Is this Mode Avtive选上，最后，单击Protection标签，对软件进行保护即可。ASProtect加壳过程中也可外挂用户自己写的DLL文件，方法是在上图中的External Options选项加上目标DLL即可。这样，用户可以在DLL加入自己的反跟踪代码，以提高软件的反跟踪能力。 强度评介：由于ASProtect名气太大，研究它的人很多，因此很容易被脱壳，不推荐使用。 补充一点：ASPr的完美脱壳脚本VolX大侠已经写出。

2.Armadillo加密壳 Armadillo也称穿山甲，是一款应用面较广的壳。可以运用各种手段来保护你的软件，同时也可以为软件加上种种限制，包括时间、次数，启动画面等等！很多商用软件采用其加壳。Armadillo对外发行时有Public，Custom两个版本。Public是公开演示的版本，Custom是注册用户拿到的版本。只有Custom才有完整的功能，Public版有功能限制，没什么强度，不建议采用。 强度评介：Armadillo中比较强大的保护选项是Nanomites保护（即CC保护），用的好能提高强度，其他选项没什么强度。 补充一点：这些天看到一萧烟雨论坛的NewBBQ刚放出了一个有效修复CC的一个工具，期待完美脱壳机。

3.EXECryptor加密壳 EXECryptor也是一款猛壳，可能由于兼容性等原因，采用其保护的商业软件不是太多。这款壳的特点是Anti-Debug做的比较隐蔽，另外就是采用了虚拟机保护一些关键代码。 强度评介：用好EXECryptor 虚拟机保护功能，将关键敏感代码用虚拟机保护起来，能提高强度。EXECryptor 壳能脱的人很多，但对付其虚拟机代码的人不多。

4.Themida加密壳 Themida是Oreans的一款商业壳，官方链接：www.oreans.com。Themida 1.1以前版本带驱动，稳定性有些影响。Themida最大特点就是其虚拟机保护技术，因此在程序中擅用SDK，将关键的代码让Themida用虚拟机保护起来。Themida最大的缺点就是生成的软件有些大。WinLicense这款壳和Themida是同一公司的一个系列产品，WinLicense主要多了一个协议，可以设定使用时间，运行次数等功能，两者核心保护是一样的。 强度评介：用好其虚拟机保护功能，将关键敏感代码用虚拟机保护起来，能提高强度。

5.VM Protect

VMProtect是一款纯虚拟机保护软件，官方链接：www.VMProtect.ru。它是当前最强的虚拟机保护软件，经VMProtect处理过的代码，至今还没有人公开宣称能还原。但也有缺点，就是会影响程序速度，因此在一些对速度要求很高的场合就不适合用了。VMProtect 1.22.3之前是免费版，可以支持EXE，DLL等文件。更高版本需要购买，其支持驱动的保护。现在流行的做法，先用VMProtect将你的核心代码处理一下，再选用一款兼容性好的壳保护。

注意事项： 1.用VMProtect处理，请多测试，如果不稳定，请调整被保护代码的范围。 2.VMProtect对双线程支持不是太好，请同一次仅处理一个线程内的代码



6.Code Virtualizer（简称CV的壳，一款使用虚拟机技术的强壳）

7.EncryptPE（CCG老王写的一款强壳）

8.PE-Armor （CCG成员hying写的一款强壳）

9.SVK Protector 、ACProtect、Dbpe（幻影壳，好久没更新了）、MoleBox、ORiEN 等等。

