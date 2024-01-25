imObj.save(new_name, quality=95, subsampling=0)

Pillow保存jpeg，设置质量，关闭subsampling





在使用Pillow中的Image.save()方法，使用默认参数保存jpg图片的过程中发现图片被压缩的很严重，导致原来很大的大小变成几十K。这是因为在保存为jpg的过程中，内部使用压缩算法对图片进行的压缩处理。



但是有些时候往往需要图片的大小不能变化太大或不能太小。所以在使用此方式时可以加入参数：



imObj.save(img_name, quality=95)



quality参数： 保存图像的质量，值的范围从1(最差)到95(最佳)。 默认值为75，使用中应尽量避免高于95的值; 100会禁用部分JPEG压缩算法，并导致大文件图像质量几乎没有任何增益。



使用此参数后，图片大小会增加。如果图片的大小还不能满足你的需求，是否还有其他方式去增加图片大小呢？



通过查阅资料并尝试，发现save方法还有一个可以配合quality使用的参数，能够大大增加图片大小：



imObj.save(new_name, quality=95, subsampling=0)



subsampling参数：子采样，通过实现色度信息的分辨率低于亮度信息来对图像进行编码的实践。 (参考：https：//en.wikipedia.org/wiki/Chroma_subsampling)



可能的子采样值是0,1和2，对应于4：4：4,4：2：2和4：1：1(或4：2：0？)。



经过实践将值设为0便可以满足图片大小增大的需求。



注意： 以上方法的参数只针对于保存为JPG/JPEG格式的图片的情况。

————————————————

版权声明：本文为CSDN博主「weixin_39834767」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。

原文链接：https://blog.csdn.net/weixin_39834767/article/details/111550307