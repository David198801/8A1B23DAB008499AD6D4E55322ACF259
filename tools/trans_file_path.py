#把附件的绝对路径复制到./assets/中并改为相对路径

import os

import my_func


# 测试
if __name__ == '__main__':
    note_path = os.path.join(base_dir,"note")
    for root,dirs,files in os.walk(note_path):
        for f in files:
            if f and f.endswith(".md"):
                md_file_path = os.path.join(root,f)
                my_func.copy_files_in_links(md_file_path)
            
    
    
