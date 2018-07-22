# selfimpr_android
https://blog.csdn.net/coolwxb/article/details/7939246
http://chuansong.me/n/504597251735
https://mp.weixin.qq.com/s?__biz=MzAxNDEwNjk5OQ==&mid=2650400177&idx=1&sn=b9c235b7fcbb39f5262277726b2b7c6f&chksm=83952da9b4e2a4bf33bf1aaa0db5c495f8dab4cd4d9fcb69f0f532266ff70829db2e320eb506&scene=0#rd
https://www.zhihu.com/question/19903344
http://www.apmbe.com/category/android/
http://www.vogella.com/tutorials/AndroidNetworking/article.html

一、Android内存管理基础
物理内存与进程内存
物理内存即移动设备上的RAM，当启动一个Android程序时，会启动一个Dalvik VM进程，系统会给它分配固定的内存空间（16M,32M不定），这块内存空间会映射到RAM上某个区域。然后这个Android程序就会运行在这块空间上。Java里会将这块空间分成Stack栈内存和Heap堆内存。stack里存放对象的引用，heap里存放实际对象数据。
在程序运行中会创建对象，如果未合理管理内存，比如不及时回收无效空间就会造成内存泄露，严重的话可能导致使用内存超过系统分配内存，即内存溢出OOM，导致程序卡顿甚至直接退出。

内存泄露（Memory Leak）
Java内存泄漏指的是进程中某些对象（垃圾对象）已经没有使用价值了，但是它们却可以直接或间接地引用到gc roots导致无法被GC回收。Dalvik VM具备的GC机制（垃圾回收机制）会在内存占用过多时自动回收，严重时会造成内存溢出OOM。

内存溢出OOM
当应用程序申请的java heap空间超过Dalvik VM HeapGrowthLimit时，溢出。
注意：OOM并不代表内存不足，只要申请的heap超过Dalvik VM HeapGrowthLimit时，即使内存充足也会溢出。效果是能让较多进程常驻内存。

如果RAM不足时系统会做什么？
Android的Memory Killer会杀死优先级较低的进程，让高优先级进程获取更多内存。

Android系统默认内存回收机制

进程优先级：Foreground进程、Visible进程、Service进程、Background进程、Empty进程;

如果用户按Home键返回桌面，那么该app成为Background进程；如果按Back返回，则成为Empty进程

ActivityManagerService直接管理所有进程的内存资源分配。所有进程要申请或释放内存都需要通过ActivityManagerService对象。

垃圾回收不定期执行。当内存不够时就会遍历heap空间，把垃圾对象删除。

堆内存越大，则GC的时间更长

二、Android内存优化
Bitmap优化
Bitmap非常消耗内存，而且在Android中，读取bitmap时， 一般分配给虚拟机的图片堆栈只有8M，所以经常造成OOM问题。所以有必要针对Bitmap的使用作出优化：

图片显示：加载合适尺寸的图片，比如显示缩略图的地方不要加载大图。

图片回收：使用完bitmap，及时使用Bitmap.recycle()回收。

问题：Android不是自身具备垃圾回收机制吗？此处为何要手动回收。
Bitmap对象不是new生成的，而是通过BitmapFactory生产的。而且通过源码可发现是通过调用JNI生成Bitmap对象（nativeDecodeStream()等方法）。所以，加载bitmap到内存里包括两部分，Dalvik内存和Linux kernel内存。前者会被虚拟机自动回收。而后者必须通过recycle()方法，内部调用nativeRecycle()让linux kernel回收。

捕获OOM异常：程序中设定如果发生OOM的应急处理方式。

图片缓存：内存缓存、硬盘缓存等

图片压缩：直接使用ImageView显示Bitmap时会占很多资源，尤其当图片较大时容易发生OOM。可以使用BitMapFactory.Options对图片进行压缩。

图片像素：android默认颜色模式为ARGB_8888，显示质量最高，占用内存最大。若要求不高时可采用RGB_565等模式。图片大小：图片长度*宽度*单位像素所占据字节数
ARGB_4444：每个像素占用2byte内存
ARGB_8888：每个像素占用4byte内存 （默认）
RGB_565：每个像素占用2byte内存

对象引用类型

强引用 strong：Object object=new Object()。当内存不足时，Java虚拟机宁愿抛出OOM内存溢出异常，也不会轻易回收强引用对象来解决内存不足问题；

软引用 soft：只有当内存达到某个阈值时才会去回收，常用于缓存；

弱引用 weak ：只要被GC线程扫描到了就进行回收；

虚引用
如果想要避免OOM发生，则使用软引用对象，即当内存快不足时进行回收；如果想尽快回收某些占用内存较大的对象，例如bitmap，可以使用弱引用，能被快速回收。不过如果要对bitmap作缓存就不要使用弱引用，因为很快就会被GC回收，导致缓存失败。

池 pool

对象池：如果某个对象在创建时，需要较大的资源开销，那么可以将其放入对象池，即将对象保存起来，下次需要时直接取出使用，而不用再次创建对象。当然，维护对象池也需要一定开销，故要衡量。

线程池：与对象池差不多，将线程对象放在池中供反复使用，减少反复创建线程的开销。

三、Android内存分配与回收
参考地址：http://www.jianshu.com/p/106795175971
