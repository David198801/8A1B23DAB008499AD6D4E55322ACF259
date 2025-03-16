1. **打开终端**

2. 

   **更新你的包列表**

   ```bash
   sudo apt update
   ```

3. 

   **下载Miniconda安装脚本**

   ```bash
   wget https://repo.anaconda.com/miniconda/Miniconda3-latest-Linux-x86_64.sh
   ```

4. 

   **运行安装脚本**

   ```bash
   bash Miniconda3-latest-Linux-x86_64.sh
   ```

   运行脚本后，你将看到一个许可协议，按`Enter`键开始阅读，然后按`q`退出。之后，你需要同意许可协议。同意后，脚本将询问你安装的位置，你可以直接按`Enter`接受默认位置，或者输入其他路径。

5. 

   **初始化Conda**

   ```bash
   source ~/.bashrc
   ```

   可能需要先运行init

   ```shell
   /root/miniconda3/bin/conda init
   ```

   或者，如果你使用的是bash以外的shell（如zsh），则可能需要运行：

   ```bash
   source ~/.zshrc
   ```

6. 

   **验证安装**

   ```bash
   conda --version
   ```

### 使用Anaconda安装

1. 

   **打开终端**

2. 

   **更新你的包列表**

   

   ```bash
   sudo apt update
   ```

3. 

   **下载Anaconda安装脚本**

   ```bash
   wget https://repo.anaconda.com/archive/Anaconda3-2023.03-Linux-x86_64.sh
   ```

   确保使用最新的链接。你可以从[Anaconda官网](https://www.anaconda.com/products/distribution)获取最新版本。

4. 

   **运行安装脚本**

   ```bash
   bash Anaconda3-2023.03-Linux-x86_64.sh
   ```

   按照提示进行操作，同意许可协议，并选择安装位置。

5. 

   **初始化Anaconda**

   ```bash
   source ~/.bashrc
   ```

   或者，如果你使用的是zsh，则运行：

   ```bash
   source ~/.zshrc
   ```

6. 

   **验证安装**

   ```bash
   conda --version
   ```

### 注意事项：

- 在安装过程中，你可能需要根据你的Ubuntu版本和环境配置选择合适的Python版本（例如Python 3.7, 3.8等）。你可以在运行安装脚本时通过指定版本号来选择（例如使用`bash Miniconda3-py38_4.9.2-Linux-x86_64.sh`来指定Python 3.8版本）。
- 如果你在安装过程中遇到权限问题，可以尝试在命令前添加`sudo`（尽管通常不推荐这样做，除非你确定需要），例如`sudo bash Miniconda3-latest-Linux-x86_64.sh`。然而，最好是按照提示操作，通常不需要sudo权限来安装Miniconda或Anaconda。
- 使用`source ~/.bashrc`或`source ~/.zshrc`是为了使Conda命令在当前会话中可用。你也可以选择将Conda初始化添加到你的shell配置文件中（如`.bashrc`或`.zshrc`），通过运行`conda init`命令自动完成这一步骤。这将在每次打开新终端时自动初始化Conda。