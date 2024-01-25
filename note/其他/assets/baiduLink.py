#coding:utf8
import os
import hashlib

def calMD5(filePath):
    m = hashlib.md5()
    with open(filePath,'rb') as fobj:
        while True:
            data = fobj.read(4096)
            if not data:
                break
            m.update(data)  #更新md5对象
    return m.hexdigest()

def cal256MD5(filePath):
    m256 = hashlib.md5()
    with open(filePath,'rb') as fobj:
        data = fobj.read(256*1024)
        m256.update(data)  #更新md5对象
    return m256.hexdigest()

currentPath = os.path.dirname(__file__)
txt = open("baidupcsLink.txt","w",encoding="utf-8-sig")
calNum = 0

'''
continueMark = True
continuefile = "【TALKin'】Vol.9 LiSA　Part.3 LiSAの過去と未来.mp4"
'''


for root,dirs,files in os.walk(currentPath):
    for f in files:
        calNum += 1
        print("[" + str(calNum) + ']')
        filePath = os.path.join(root,f)
        if filePath != __file__ and f != "baidupcsLink.txt":
            fileSize = os.path.getsize(filePath)
            '''
            if continueMark:
                if f != continuefile:
                    continue
                else:
                    continueMark = False
            '''
            fileMD5 = calMD5(filePath)
            if fileSize>(256*1024):
                file256MD5 = cal256MD5(filePath)
            else:
                file256MD5 = fileMD5
            linkPath = filePath.replace(currentPath,"").replace("\\","/")
            fileLink = fileMD5.upper() + "#" + file256MD5.upper() + "#" + str(fileSize) + "#" + linkPath[1:]
            txt.write(fileLink)
            txt.write("\n")
            print(linkPath)
txt.close()