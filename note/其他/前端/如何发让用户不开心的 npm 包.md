https://zhuanlan.zhihu.com/p/462831582



本文将介绍几种有趣的发包方式，如果你还不知道如何让你的用户不开心，可以参考一下。

在产物主入口中加入 .xxss 样式引入

你或许知道你的用户并没有配你常用预处理器 Webpack 插件的习惯，只想 import 你的包然后直接 import 一段你的产物 CSS。

但是你依然会在产物的 index 中义无反顾的 import 'index.xxss' ，用户报错了最主要的原因肯定是没有用包了一切的构建工具，不用同样预处理工具的人不应该有使用这个包的资格。

至于试图 SSR 或者做单元测试的人见到报错，那都是自找不痛快。

除此之外你的用户还可能花时间去处理奇怪的样式顺序、编译报错问题，因为你在四处 import xxss，配合上用户态的各种引入而导致很多未明确的定义的顺序或者样式报错。

把 main 指向一个 es module 的 js 文件

有些构建工具非常有意思，会把给 node 环境的包打成 esm 的格式。我指的不是把 type 写成 module，而是把 main 指向一个 es module 文件。

当用户在 node 环境运行这个包，会非常贴心的收到不能在 node 中运行一个 type 不是 module 的 esm 文件的报错。

虽然它从精神上击穿了 Pure ESM package 的思路，但是在落实的时候出了小小的偏差。不过也没什么，那些试图写单元测试的人一定是工作不饱和，给他们增添一点小小的障碍不值一提。

在包里面直接 require SVG 文件

众所周知，使用 SVG loader 的人一定会比样式预处理器插件的人少，所以光引入 xxss 可能不足以让你的用户收到惊喜。所以你可以在源码里直接 import svg。

我有幸看过一些在 cjs 产物中直接写着 require('./xx/y.svg') 的包，脑中泛起了非常多的困惑，如果你打了一个需要用户配 loader 的 CJS 包，那么还打这份 CJS 包干啥呢？是担心 babel 读不懂 esm 吗，还是嫌 CI 时间太短了。

打一份超过 50Mb 的 CSS

随着计算机性能的发展，软件消耗的性能也会逐渐上涨，总有些包极富探究精神地探寻用户计算机的性能极限。

这些包的源码中写下了这样的 CSS，试图一个搞雪碧图效果。

```javascript
.a1 {
  background-position: x1 y1;
  background-image: url('./sprite.png');
}

...

.a500 {
  background-position: x500 y500;
  background-image: url('./sprite.png');
}
```

然后在预处理器的帮助下，这些 background-image 会变成数百个 base 64，随着包的产物被发出来。

用不了的人大概是电脑性能不够好吧，几十兆的 CSS 而已。

结语

Webpack 和 babel 给的太多了。