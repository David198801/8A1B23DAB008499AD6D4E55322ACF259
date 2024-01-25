#把附件的绝对路径复制到./assets/中并改为相对路径，仅限今天

import os
import datetime

import my_func

if __name__ == '__main__':
    today = datetime.date.today()
    current_dir = os.path.dirname(os.path.abspath(__file__))
    note_path = os.path.join(os.path.dirname(current_dir),"note")
    for root,dirs,files in os.walk(note_path):
        for f in files:
            if f and f.endswith(".md"):
                md_file_path = os.path.join(root,f)
                stat_info = os.stat(md_file_path)
                create_date = datetime.date.fromtimestamp(stat_info.st_ctime)
                modify_date = datetime.date.fromtimestamp(stat_info.st_mtime)
                # 如果创建时间或修改时间的日期部分为今天
                if create_date >= today or modify_date >= today:
                    my_func.copy_files_in_links(md_file_path)
                
                
            
    
    
