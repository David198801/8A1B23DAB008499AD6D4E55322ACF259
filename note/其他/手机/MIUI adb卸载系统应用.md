https://www.zhihu.com/question/546836834/answer/2610518974

pm disable-user 比较稳，uninstall很容易卡开机动画



简单说下如何删MIUI中的系统应用广告吧：

下载官方提供的platform-tools，里面包含了adb工具包，

https://developer.android.com/studio/releases/platform-tools

在手机上打开开发者模式：

设置→我的设备→全部参数→在MIUI版本上连续点击

然后设置→更多设置→开发者选项→向下拉勾选USB调试→弹出框点击确认

在文件目录打开控制台窗口：

shift+鼠标右键-->powershell

复制adb shell

```javascript
adb shell pm uninstall --user 0 com.miui.systemAdSolution（小米系统广告解决方案，必删）
 adb shell pm uninstall --user 0 com.miui.analytics（小米广告分析，必删）
 adb shell pm uninstall --user 0 com.xiaomi.gamecenter.sdk.service （小米游戏中心服务）
 adb shell pm uninstall --user 0 com.xiaomi.gamecenter （小米游戏中心）
 adb shell pm uninstall --user 0 com.miui.player （小米音乐）
 adb shell pm uninstall --user 0 com.miui.video （小米视频）
 adb shell pm uninstall --user 0 com.miui.notes （小米便签）
 adb shell pm uninstall --user 0 com.miui.translation.youdao （有道翻译）
 adb shell pm uninstall --user 0 com.miui.translation.kingsoft （金山翻译）
 adb shell pm uninstall --user 0 com.android.email （邮件）
 adb shell pm uninstall --user 0 com.xiaomi.scanner （小米扫描）
 adb shell pm uninstall --user 0 com.miui.hybrid （混合器）
 adb shell pm uninstall --user 0 com.miui.bugreport （bug 反馈）
 adb shell pm uninstall --user 0 com.milink.service （米连服务）
 adb shell pm uninstall --user 0 com.android.browser （浏览器）
 adb shell pm uninstall --user 0 com.miui.gallery （相册）
 adb shell pm uninstall --user 0 com.miui.yellowpage （黄页）
 adb shell pm uninstall --user 0 com.xiaomi.midrop （小米快传）
 adb shell pm uninstall --user 0 com.miui.virtualsim （小米虚拟器）
 adb shell pm uninstall --user 0 com.xiaomi.payment （小米支付）
 adb shell pm uninstall --user 0 com.mipay.wallet （小米钱包）
 adb shell pm uninstall --user 0 com.android.soundrecorder （录音机）
 adb shell pm uninstall --user 0 com.miui.screenrecorder （屏幕录制）
 adb shell pm uninstall --user 0 com.android.wallpaper （壁纸）
 adb shell pm uninstall --user 0 com.miui.voiceassist （语音助手）
 adb shell pm uninstall --user 0 com.miui.fm （收音机）
 adb shell pm uninstall --user 0 com.miui.touchassistant （悬浮球）
 adb shell pm uninstall --user 0 com.android.cellbroadcastreceiver （小米广播）
 adb shell pm uninstall --user 0 com.xiaomi.mitunes （小米助手）
 adb shell pm uninstall --user 0 com.xiaomi.pass （小米卡包）
 adb shell pm uninstall --user 0 com.android.thememanager （个性主题管理）
 adb shell pm uninstall --user 0 com.android.wallpaper （动态壁纸）
 adb shell pm uninstall --user 0 com.android.wallpaper.livepicker （动态壁纸获取）
 adb shell pm uninstall --user 0 com.miui.klo.bugreport
```

