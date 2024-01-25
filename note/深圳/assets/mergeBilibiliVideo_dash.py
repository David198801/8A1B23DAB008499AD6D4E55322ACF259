import os
import subprocess

path = r"e:\l\v"

for root,dirs,files in os.walk(path):
    for f in files:
        if f[-3:] == 'm4a':
            m4aPath = os.path.join(root,f)
            mp4Path = m4aPath.replace('.m4a','.mp4')
            outTempPath = os.path.join(root,'temp.mp4')
            if os.path.exists(mp4Path) and (not os.path.exists(mp4Path + ".aria2")) and (not os.path.exists(m4aPath + ".aria2")):
                cmd = ['ffmpeg','-i',mp4Path,'-i',m4aPath,'-c','copy',outTempPath]
                subprocess.call(cmd)
                os.remove(m4aPath)
                os.remove(mp4Path)
                os.rename(outTempPath,mp4Path)