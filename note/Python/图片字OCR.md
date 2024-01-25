安装PaddleOCR

https://github.com/PaddlePaddle/PaddleOCR/blob/release/2.6/doc/doc_ch/quickstart.md



解决 module ‘cv2‘ has no attribute ‘gapi_wip_gst_GStreamerPipeline‘

```javascript
pip install opencv-python install "opencv-python-headless<4.3"
```



```javascript
from paddleocr import PaddleOCR, draw_ocr
from PIL import Image

img_path = r'C:\Users\LiuZhongbin\Desktop\a\4323477214.png'

im = Image.open(img_path)
x,y = im.size
try:
    # 使用白色来填充背景
    p = Image.new('RGBA', im.size, (255,255,255))
    p.paste(im, (0, 0, x, y), im)
    p.save(img_path)
finally:
    im.close()

# Paddleocr目前支持的多语言语种可以通过修改lang参数进行切换
# 例如`ch`, `en`, `fr`, `german`, `korean`, `japan`
ocr = PaddleOCR(use_angle_cls=False, use_gpu=False, lang="ch")  # need to run only once to download and load model into memory
result = ocr.ocr(img_path, det=False)

for line in result:
    print("结果:"+line[0])


```

