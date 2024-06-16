#删除未引用的附件、图片

import os
#from urllib.parse import quote

import my_func

def md_encode(s):
    if s:
        s = s.replace(" ","%20")
    return s


# 测试
if __name__ == '__main__':
    note_path = os.path.join(my_func.base_dir,"note")
    w = os.walk(note_path)
    uesless_files_all = set()
    for root,dirs,files in w:
        if root.endswith("assets"):
            uesless_files = set()
            for f in files:
                uesless_files.add(os.path.join(root,f))
            parent = os.path.dirname(root)
            for md in os.listdir(parent):
                md_path = os.path.join(parent,md)
                if md.lower().endswith(".md"):
                    with open(md_path,"r",encoding="utf8") as m:
                        md_content = m.read()
                        for f in files:
                            file_path = os.path.join(root,f)
                            encode_name = md_encode(f)
                            if file_path in uesless_files and (f in md_content or encode_name in md_content):
                                uesless_files.remove(file_path)
            
            uesless_files_all.update(uesless_files)
            
    for i in uesless_files_all:
        print(i)
        my_func.soft_delete(i)
    
