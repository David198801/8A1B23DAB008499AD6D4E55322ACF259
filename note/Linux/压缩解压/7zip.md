在 Linux 上解压 `.7z` 文件，你需要安装 `p7zip` 工具。可以使用以下命令安装：

### 1. 安装 `p7zip`（如果尚未安装）

#### 对于 Debian/Ubuntu 系统
```bash
sudo apt update
sudo apt install p7zip-full
```

#### 对于 CentOS/RHEL 系统
```bash
sudo yum install p7zip
```

#### 对于 Fedora 系统
```bash
sudo dnf install p7zip
```

### 2. 解压 `.7z` 文件

安装完 `p7zip` 后，你可以使用 `7z` 命令来解压 `.7z` 文件：

```bash
7z x GPT-SoVITS-v2-240821.7z
```

`x` 参数表示解压并保持目录结构。如果你想查看文件内容而不解压，可以使用 `l` 参数：

```bash
7z l GPT-SoVITS-v2-240821.7z
```

这样就可以解压 `.7z` 文件了。