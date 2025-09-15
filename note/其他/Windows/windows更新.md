# 注册表推迟更新

步骤一：按快捷键Win+R后在运行框中输入regedit启动注册表编辑器



步骤二：打开后找到以下相关路径（左侧面板依次打开或在搜索栏直接搜索）
计算机\HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\WindowsUpdate\UX\Settings



步骤三：右边页面空白处右键-新建-DWORD(32位)值(D)，输入数值名称FlightSettingsMaxPauseDays


步骤四：双击点开新建的数值，选择十进制，并将数值数据修改为36500，即延迟时间最大到100年，点击确认

步骤五：打开设置，点击Windows更新，点开暂停更新的向下箭头，耐心等待一会出现暂停时间，下划至最后选择即可。





# 关闭自动更新软件

InControl

WUB：Windows Update Blocker