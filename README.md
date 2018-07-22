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






目录
一、WebView简介
二、WebViewClient
三、WebChromeClient
四、WebSetting
一、WebView简介
WebView的一些常用方法：

String getUrl()：获取当前页面的URL。

reload()：重新reload当前的URL，即刷新。

boolean canGoBack()：用来确认WebView里是否还有可回退的历史记录。通常我们会在WebView里重写返回键的点击事件，通过该方法判断WebView里是否还有历史记录，若有则返回上一页。

boolean canGoForward()：用来确认WebView是否还有可向前的历史记录。

boolean canGoBackOrForward(int steps)：以当前的页面为起始点，用来确认WebView的历史记录是否足以后退或前进给定的步数，正数为前进，负数为后退。

goBack()：在WebView历史记录后退到上一项。

goForward()：在WebView历史记录里前进到下一项。

goBackOrForward(int steps)：以当前页面为起始点，前进或后退历史记录中指定的步数，正数为前进，负数为后退。

clearCache(boolean includeDiskFiles)：清空网页访问留下的缓存数据。需要注意的时，由于缓存是全局的，所以只要是WebView用到的缓存都会被清空，即便其他地方也会使用到。该方法接受一个参数，从命名即可看出作用。若设为false，则只清空内存里的资源缓存，而不清空磁盘里的。

clearHistory()：清除当前webview访问的历史记录。

clearFormData()：清除自动完成填充的表单数据。需要注意的是，该方法仅仅清除当前表单域自动完成填充的表单数据，并不会清除WebView存储到本地的数据。

onPause()：当页面被失去焦点被切换到后台不可见状态，需要执行onPause操作，该操作会通知内核安全地暂停所有动作，比如动画的执行或定位的获取等。需要注意的是该方法并不会暂停JavaScript的执行，若要暂停JavaScript的执行请使用接下来的这个方法。

onResume()：在先前调用onPause()后，我们可以调用该方法来恢复WebView的运行。

pauseTimers()：该方法面向全局整个应用程序的webview，它会暂停所有webview的layout，parsing，JavaScript Timer。当程序进入后台时，该方法的调用可以降低CPU功耗。

resumeTimers()：恢复pauseTimers时的所有操作。

destroy()：销毁WebView。需要注意的是：这个方法的调用应在WebView从父容器中被remove掉之后。我们可以手动地调用

getScrollY()：该方法返回的当前可见区域的顶端距整个页面顶端的距离，也就是当前内容滚动的距离。

getHeight()：方法都返回当前WebView这个容器的高度。其实以上两个方法都属于View。

getContentHeight()：该方法返回整个HTML页面的高度，但该高度值并不等同于当前整个页面的高度，因为WebView有缩放功能， 所以当前整个页面的高度实际上应该是原始HTML的高度再乘上缩放比例。因此，准确的判断方法应该是

pageUp(boolean top)：将WebView展示的页面滑动至顶部。

pageDown(boolean bottom)：将WebView展示的页面滑动至底部。

二、WebViewClient
WebViewClient是帮助WebView处理各种通知和请求事件的，主要的方法有：

onLoadResource(WebView view, String url)：该方法在加载页面资源时会回调，每一个资源（比如图片）的加载都会调用一次。

onPageStarted(WebView view, String url, Bitmap favicon)：该方法在WebView开始加载页面且仅在Main frame loading（即整页加载）时回调，一次Main frame的加载只会回调该方法一次。我们可以在这个方法里设定开启一个加载的动画，告诉用户程序在等待网络的响应。

onPageFinished(WebView view, String url)：该方法只在WebView完成一个页面加载时调用一次（同样也只在Main frame loading时调用），我们可以可以在此时关闭加载动画，进行其他操作。

onReceivedError(WebView view, WebResourceRequest request, WebResourceError error)：该方法在web页面加载错误时回调，这些错误通常都是由无法与服务器正常连接引起的，最常见的就是网络问题。 这个方法有两个地方需要注意：
1.这个方法只在与服务器无法正常连接时调用，类似于服务器返回错误码的那种错误（即HTTP ERROR），该方法是不会回调的，因为你已经和服务器正常连接上了（全怪官方文档(︶^︶)）；
2.这个方法是新版本的onReceivedError()方法，从API23开始引进，与旧方法onReceivedError(WebView view,int errorCode,String description,String failingUrl)不同的是，新方法在页面局部加载发生错误时也会被调用（比如页面里两个子Tab或者一张图片）。这就意味着该方法的调用频率可能会更加频繁，所以我们应该在该方法里执行尽量少的操作。

onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse)：上一个方法提到onReceivedError并不会在服务器返回错误码时被回调，那么当我们需要捕捉HTTP ERROR并进行相应操作时应该怎么办呢？API23便引入了该方法。当服务器返回一个HTTP ERROR并且它的status code>=400时，该方法便会回调。这个方法的作用域并不局限于Main Frame，任何资源的加载引发HTTP ERROR都会引起该方法的回调，所以我们也应该在该方法里执行尽量少的操作，只进行非常必要的错误处理等。

onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)：当WebView加载某个资源引发SSL错误时会回调该方法，这时WebView要么执行handler.cancel()取消加载，要么执行handler.proceed()方法继续加载（默认为cancel）。需要注意的是，这个决定可能会被保留并在将来再次遇到SSL错误时执行同样的操作。

WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request)：当WebView需要请求某个数据时，这个方法可以拦截该请求来告知app并且允许app本身返回一个数据来替代我们原本要加载的数据。
比如你对web的某个js做了本地缓存，希望在加载该js时不再去请求服务器而是可以直接读取本地缓存的js，这个方法就可以帮助你完成这个需求。你可以写一些逻辑检测这个request，并返回相应的数据，你返回的数据就会被WebView使用，如果你返回null，WebView会继续向服务器请求。

boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)：哈~ 终于到了这个方法，在最开始的基础演示时我们用到了这个方法。从实践中我们知道，当我们没有给WebView提供WebViewClient时，WebView如果要加载一个url会向ActivityManager寻求一个适合的处理者来加载该url（比如系统自带的浏览器），这通常是我们不想看到的。于是我们需要给WebView提供一个WebViewClient，并重写该方法返回true来告知WebView url的加载就在app中进行。这时便可以实现在app内访问网页。

onScaleChanged(WebView view, float oldScale, float newScale)：当WebView得页面Scale值发生改变时回调。

boolean shouldOverrideKeyEvent(WebView view, KeyEvent event)：默认值为false，重写此方法并return true可以让我们在WebView内处理按键事件。

 

三、WebChromeClient
WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等偏外部事件。

onProgressChanged(WebView view, int newProgress)：当页面加载的进度发生改变时回调，用来告知主程序当前页面的加载进度。

onReceivedIcon(WebView view, Bitmap icon)：用来接收web页面的icon，我们可以在这里将该页面的icon设置到Toolbar。

onReceivedTitle(WebView view, String title)：用来接收web页面的title，我们可以在这里将页面的title设置到Toolbar。
以下两个方法是为了支持web页面进入全屏模式而存在的（比如播放视频），如果不实现这两个方法，该web上的内容便不能进入全屏模式。

onShowCustomView(View view, WebChromeClient.CustomViewCallback callback)：该方法在当前页面进入全屏模式时回调，主程序必须提供一个包含当前web内容（视频 or Something）的自定义的View。

onHideCustomView()：该方法在当前页面退出全屏模式时回调，主程序应在这时隐藏之前show出来的View。

Bitmap getDefaultVideoPoster()：当我们的Web页面包含视频时，我们可以在HTML里为它设置一个预览图，WebView会在绘制页面时根据它的宽高为它布局。而当我们处于弱网状态下时，我们没有比较快的获取该图片，那WebView绘制页面时的gitWidth()方法就会报出空指针异常~ 于是app就crash了。。
这时我们就需要重写该方法，在我们尚未获取web页面上的video预览图时，给予它一个本地的图片，避免空指针的发生。

View getVideoLoadingProgressView()：重写该方法可以在视频loading时给予一个自定义的View，可以是加载圆环 or something。

boolean onJsAlert(WebView view, String url, String message, JsResult result)：处理Javascript中的Alert对话框。

boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result)：处理Javascript中的Prompt对话框。

boolean onJsConfirm(WebView view, String url, String message, JsResult result)：处理Javascript中的Confirm对话框

boolean onShowFileChooser(WebView webView, ValueCallback filePathCallback, WebChromeClient.FileChooserParams fileChooserParams)：该方法在用户进行了web上某个需要上传文件的操作时回调。我们应该在这里打开一个文件选择器，如果要取消这个请求我们可以调用filePathCallback.onReceiveValue(null)并返回true。

onPermissionRequest(PermissionRequest request)：该方法在web页面请求某个尚未被允许或拒绝的权限时回调，主程序在此时调用grant(String [])或deny()方法。如果该方法没有被重写，则默认拒绝web页面请求的权限。

onPermissionRequestCanceled(PermissionRequest request)：该方法在web权限申请权限被取消时回调，这时应该隐藏任何与之相关的UI界面。

 

四、WebSetting
WebSetting常用的方法有：

setJavaScriptEnabled(boolean flag)：设置WebView是否可以运行JavaScript。

setJavaScriptCanOpenWindowsAutomatically(boolean flag)：设置WebView是否可以由JavaScript自动打开窗口，默认为false，通常与JavaScript的window.open()配合使用。

setAllowFileAccess(boolean allow)：启用或禁用WebView访问文件数据。

setBlockNetworkImage(boolean flag)：禁止或允许WebView从网络上加载图片。需要注意的是，如果设置是从禁止到允许的转变的话，图片数据并不会在设置改变后立刻去获取，而是在WebView调用reload()的时候才会生效。
这个时候，需要确保这个app拥有访问Internet的权限，否则会抛出安全异常。通常没有禁止图片加载的需求的时候，完全不用管这个方法，因为当我们的app拥有访问Internet的权限时，这个flag的默认值就是false。

setSupportZoom(boolean support)：设置是否支持缩放。

setBuiltInZoomControls(boolean enabled)：显示或不显示缩放按钮（wap网页不支持）。

setSupportMultipleWindows(boolean support)：设置WebView是否支持多窗口。

setLayoutAlgorithm(WebSettings.LayoutAlgorithm l)：指定WebView的页面布局显示形式，调用该方法会引起页面重绘。默认值为LayoutAlgorithm#NARROW_COLUMNS。

setNeedInitialFocus(boolean flag)：通知WebView是否需要设置一个节点获取焦点当WebView#requestFocus(int,android.graphics.Rect)被调用时，默认为true。

setAppCacheEnabled(boolean flag)：启用或禁用应用缓存。

setAppCachePath(String appCachePath)：设置应用缓存路径，这个路径必须是可以让app写入文件的。该方法应该只被调用一次，重复调用会被无视~

setCacheMode(int mode)：用来设置WebView的缓存模式。当我们加载页面或从上一个页面返回的时候，会按照设置的缓存模式去检查并使用（或不使用）缓存。
缓存模式有四种：
LOAD_DEFAULT：默认的缓存使用模式。在进行页面前进或后退的操作时，如果缓存可用并未过期就优先加载缓存，否则从网络上加载数据。这样可以减少页面的网络请求次数。LOAD_CACHE_ELSE_NETWORK：只要缓存可用就加载缓存，哪怕它们已经过期失效。如果缓存不可用就从网络上加载数据。
LOAD_NO_CACHE：不加载缓存，只从网络加载数据。
LOAD_CACHE_ONLY：不从网络加载数据，只从缓存加载数据。
通常我们可以根据网络情况将这几种模式结合使用，比如有网的时候使用LOAD_DEFAULT，离线时使用LOAD_CACHE_ONLY、LOAD_CACHE_ELSE_NETWORK，让用户不至于在离线时啥都看不到。

setDatabaseEnabled(boolean flag)：启用或禁用数据库缓存。

setDomStorageEnabled(boolean flag)：启用或禁用DOM缓存。

setUserAgentString(String ua)：设置WebView的UserAgent值。

setDefaultEncodingName(String encoding)：设置编码格式，通常都设为“UTF-8”。

setStandardFontFamily(String font)：设置标准的字体族，默认“sans-serif”。

setCursiveFontFamily：设置草书字体族，默认“cursive”。

setFantasyFontFamily：设置CursiveFont字体族，默认“cursive”。

setFixedFontFamily：设置混合字体族，默认“monospace”。

setSansSerifFontFamily：设置梵文字体族，默认“sans-serif”。

setSerifFontFamily：设置衬线字体族，默认“sans-serif”

setDefaultFixedFontSize(int size)：设置默认填充字体大小，默认16，取值区间为[1-72]，超过范围，使用其上限值。

setDefaultFontSize(int size)：设置默认字体大小，默认16，取值区间[1-72]，超过范围，使用其上限值。

setMinimumFontSize：设置最小字体，默认8. 取值区间[1-72]，超过范围，使用其上限值。

setMinimumLogicalFontSize：设置最小逻辑字体，默认8. 取值区间[1-72]，超过范围，使用其上限值。


