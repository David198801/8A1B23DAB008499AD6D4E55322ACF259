用 Lazarus 做 GUI 程序合适吗？ - 韦易笑的回答 - 知乎
https://www.zhihu.com/question/54905309/answer/2355684869



也许你没留意，很多你经常用的桌面软件是用 Lazarus 开发的。

作为 Delphi 的开源替代品，我一直是比较喜欢 Lazarus 的，虽然有些小众。技术有两种，有些是用来挣钱养家，用来赶进度大规模集团作战的；还有一类是出于兴趣，单纯觉得好玩，会不自觉的有空就翻出来当爱好的，比如 Lazarus 就是一个很好玩的玩具。

因此不用成天纠结 “谁是 GUI 天下第一”之类内卷的问题，抱着轻松和评测+鉴赏的心情了解下或许也不错。

在此之前，得先说两句 Delphi，姚冬老师，知乎编程板块无人不知，给了 Delphi 这样的评价：

Delphi 是神作，它在 RAD（快速应用开发）领域长时间没有对手，直到BS架构取代CS架构。Delphi 的特点就是简单、开发快，单纯就写个基本可用的应用来说，可能至今都没有比他更快的。

为啥作为国内最早使用 Qt 的人，却会给 Delphi 那么高的评价呢？中小应用开发 “至今没有比他更快的技术”这么高的评价，必然是有原因的，无独有偶，另一位 Qt 大神也发表示过对 Delphi 继承者 Lazarus 的喜爱：

![](assets/用%20Lazarus%20做%20GUI%20程序合适吗？%20-%20韦易笑的回答%20-%20知乎httpswww.zhihu.comquestion54905309answer2355_image_0.jpeg)

Delphi 的继承人有两个，第一个是 C# Winform，几乎是把 Delphi 的整个开发模式迁移过去了；第二个就是 Lazarus。你用 C# Winform 没有任何问题，但 Lazarus 作为另外一个选择，它有其不可替代性：

1. 免费开源，跨平台，支持：Windows, Linux, macOS, FreeBSD。

1. Native 语言做 GUI 更加硬朗：更快响应，更低内存占用，不易破解。

1. 高度还原 Delphi 开发方式，控件丰富。

Lazarus 使用 Free Pascal 和 Free VCL (LCL) 基本做到和 Delphi 项目源代码级别的兼容了，喜欢 Delphi 开发的，迁移 Lazarus 基本上都很容易，配张图感受下：

![](assets/用%20Lazarus%20做%20GUI%20程序合适吗？%20-%20韦易笑的回答%20-%20知乎httpswww.zhihu.comquestion54905309answer2355_image_1.jpeg)

看看上边那一排丰富的控件，左边熟悉的控件属性面板，是的，原汁原味的 Delphi/BCB 的感觉。

Lazarus 在 2012 年发布 1.0 后一年内 Source Forge 上突破了 400 万的下载次数，持续迭代了多个版本后，于 2022 年发布了 2.2.0 版本，比前作更加稳定和完善。Lazarus 免费并且开源，允许用于商业开发的，这意味着你不需要花几万元购买 DELPHI 或者 VS 就可以开发商业应用。

（VS 和 DELPHI 11 目前都是有社区版的，但是社区版都不能开发商业应用）。

先进的界面技术

Lazarus LCL （Free VCL）架构图如下：

![](assets/用%20Lazarus%20做%20GUI%20程序合适吗？%20-%20韦易笑的回答%20-%20知乎httpswww.zhihu.comquestion54905309answer2355_image_2.jpeg)

这货可以同时跑在：Windows API，Gtk，Qt 4/5，Cocoa/Carbon 上面，跨平台做到这个份上，没几个库做得到如此完善，在发布 Lazarus 1.0 之前，两位作者花了十年的时间开发 LCL。

Free Pascal

基于 Free Pascal ，虽然比起 C++ 更啰嗦（没有 Java 那么啰嗦），但是相对简单，上手容易，可读性强，不易出错，语言特性也没掉链子（类，泛型，RTTI，反射机制，模块和属性等），另外还兼容 Delphi 对 obj-pascal 的扩充语法。

丰富的社区组件

吸收了 Delphi 7 - 11 的优秀基因，引入了很多现代组件的同时还有丰富的第三方组件：

- SQLite / PSQL 操作数据库，并直接关联界面表格。

- Python4Laz：可以用 Python 混合编程，Pascal 负责界面交互，Python 负责逻辑。

- Lua4Laz：可以用 Lua 混合编程，Pascal 负责界面交互，Lua 负责逻辑。

- Pascal Script：一种基于 Pascal 的动态脚本。

- 界面库 SkinCate，一键换肤，类似当年 Delphi/BCB 的 vclskin 组件。

- 第三方控件：验证码、拖拽、数学库，二维码、图表，网页，Excel 显示。

- Backport 的 Delphi 组件：比如 DelphiMoon。

不少付费组件现在也都同时支持 Delphi 和 Lazarus，感兴趣看这里：

- https://github.com/Fr0sT-Brutal/awesome-pascal



那么哪些软件是 Lazarus 开发的呢？除去一些行业软件外，还有一些大家耳熟能详的：

Total Commander

![](assets/用%20Lazarus%20做%20GUI%20程序合适吗？%20-%20韦易笑的回答%20-%20知乎httpswww.zhihu.comquestion54905309answer2355_image_3.jpeg)

老牌硬核的桌面效率工具，最初使用 Delphi 开发，2010 年开始移植 Lazarus，目前 64 位版本完全使用 Lazarus 开发，Total Commander 的开发记录 history.txt 里可以找到很多记录：

14.06.11 Fixed: Lazarus combobox with dropped down list: ENTER or ESC closed entire dialog box instead of just closing the dropped down part (64)
14.07.10 Added: Start work on conversion to Lazarus/Free Pascal in preparation for 64-bit version   

作者 Ghisler 也表示过，Total Commander 的 32 位还保持 Delphi 编译的原因是后者输出二进制略小，但是代码在十年前就已经完成移植，可以同时在 Lazarus 和 Delphi 下面编译。

Cheat Engine

![](assets/用%20Lazarus%20做%20GUI%20程序合适吗？%20-%20韦易笑的回答%20-%20知乎httpswww.zhihu.comquestion54905309answer2355_image_4.jpeg)

著名的游戏修改器，国内俗称 CE，得益于 Lazarus 的跨平台性，这货也支持 macOS。

Beyond Compare

![](assets/用%20Lazarus%20做%20GUI%20程序合适吗？%20-%20韦易笑的回答%20-%20知乎httpswww.zhihu.comquestion54905309answer2355_image_5.jpeg)

你们会发现 Delphi / Lazarus 很擅长开发类似 Total Commander 和 Beyond Compare 这种硬核的桌面应用，丰富的界面表现，重度 GUI 界面操作。

Pea Zip

![](assets/用%20Lazarus%20做%20GUI%20程序合适吗？%20-%20韦易笑的回答%20-%20知乎httpswww.zhihu.comquestion54905309answer2355_image_6.jpeg)

Pea Zip 以启动快，占用少，开源免费而闻名，支持 Windows (Native+UWP），macOS，GNome （包括 Cinnamon + Mate），KDE，XFCE 等多种桌面环境。

Double Commander

![](assets/用%20Lazarus%20做%20GUI%20程序合适吗？%20-%20韦易笑的回答%20-%20知乎httpswww.zhihu.comquestion54905309answer2355_image_7.jpeg)

这是高仿 Total Commander 的免费开源文件管理器，支持 Linux / macOS，立项开始就用 Lazarus 进行开发。

CudaText

高性能文本编辑器 CudaText ，sublime text 的开源代替品：

![](assets/用%20Lazarus%20做%20GUI%20程序合适吗？%20-%20韦易笑的回答%20-%20知乎httpswww.zhihu.comquestion54905309answer2355_image_8.jpeg)

和 sublime text 类似，这货：

- 高性能 + Native 语言，启动速度快，内存占用低，丝滑体验。

- 高颜值，自带 270+ 种语言高亮文件。

- sublime 经典的 Minimap / Command Palette

- 自带二进制编辑模式。

- 大文件支持，打开 20G 的日志文件毫无压力。

- 同 sublime 一样用 Python 写扩展，大量社区扩展。

你可以用扩展管理器来安装各类扩展，完成：编译运行当前项目/文件，LSP 语义补全，Snippets （代码片段），项目符号搜索 等等功能，功能比 NotePad++ 强多了。

--

桌面开发技术千千万，你用啥都可以，如果你不喜欢动态语言开发 GUI 那种软绵绵的感觉，喜爱 Native 那种硬朗的风格，那么除了 Qt 外你还是可以玩玩 Lazarus 的。