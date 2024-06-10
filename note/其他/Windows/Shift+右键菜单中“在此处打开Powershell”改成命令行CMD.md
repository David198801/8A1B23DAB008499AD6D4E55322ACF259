步骤如下

1，按下window + R, 输入regedit，打开注册表编辑器，定位到

计算机\HKEY_CLASSES_ROOT\Directory\Background\shell\cmd

2，在该项上右键，点击权限-高级-所有者-更改，输入你的用户名，然后一路点击确定。

3, 在该项上右键, 再打开一次权限窗口，选中你的用户名或Users或“Administrators”，勾选“完全控制”，确定。

4，将右侧项“HideBasedOnVelocityId”重命名为“ShowBasedOnVelocityId”，这样在文件资源管理器里Shift+右键弹出的菜单中就会显示“在此处打开命令窗口”。

注意，有的系统上要隐藏“在此处打开Powershell”才可以。

5，如果想要隐藏“在此处打开Powershell”。

定位到

计算机\HKEY_CLASSES_ROOT\Directory\Background\shell\Powershell

就在刚才位置的下面。只要把“ShowBasedOnVelocityId”重命名为“HideBasedOnVelocityId”，“在此处打开Powershell”就会隐藏了。