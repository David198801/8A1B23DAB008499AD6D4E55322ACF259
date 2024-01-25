import os
import hashlib
import re
import shutil
import datetime
import logging

# 获取文件md5值
def get_file_md5(file_path):
    with open(file_path, 'rb') as f:
        md5_obj = hashlib.md5()
        md5_obj.update(f.read())
        return md5_obj.hexdigest()
        
def checkAndMakeDir(path):
    if not os.path.exists(path):
        os.makedirs(path)

current_dir = os.path.dirname(os.path.abspath(__file__))
base_dir = os.path.dirname(current_dir)
today = datetime.datetime.now()
log_dir = base_dir+"/logs"
checkAndMakeDir(log_dir)
log_name=log_dir+"/log."+today.strftime("%Y-%m-%d")+".log"
logging.basicConfig(handlers=[logging.FileHandler(filename=log_name,mode='a',encoding='utf-8')], 
    level=logging.INFO,
    format='%(asctime)s %(levelname)s %(name)s %(message)s')

# 读取md文件
def read_md_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        return f.read()

# 写入md文件
def write_md_file(file_path, content):
    with open(file_path, 'w', encoding='utf-8') as f:
        f.write(content)


# 定义正则表达式匹配超链接
LINK_PATTERN = re.compile(r'\[.*?\]\((.*?)\)')
# 把附件的绝对路径复制到./assets/中并改为相对路径
def copy_files_in_links(md_file_path):
    md_content = read_md_file(md_file_path)
    new_md_content = md_content
    for path in re.findall(LINK_PATTERN, md_content):
        if (os.path.sep in path) and os.path.exists(path) and os.path.isabs(path) and os.path.isfile(path):
            logging.info("处理路径"+path)
            file_md5 = get_file_md5(path)
            parent_path = os.path.dirname(md_file_path)
            new_name = file_md5+"_"+os.path.basename(path)
            asset_path = os.path.join(parent_path, 'assets')
            checkAndMakeDir(asset_path)
            new_path = os.path.join(asset_path,new_name)
            logging.info("复制"+path+"到"+new_path)
            shutil.copyfile(path, new_path)
            md_new_path = 'assets/' + new_name
            logging.info("md链接替换为"+md_new_path)
            new_md_content = new_md_content.replace(path, md_new_path)
    if new_md_content!=md_content:
        logging.info("写入文件"+md_file_path)
        write_md_file(md_file_path, new_md_content)

# 测试
if __name__ == '__main__':
    current_dir = os.path.dirname(os.path.abspath(__file__))
    note_path = os.path.join(os.path.dirname(current_dir),"note")
    md_file_path = note_path+"/感谢您的购买,订单3644842683166362555.md"
    
    
