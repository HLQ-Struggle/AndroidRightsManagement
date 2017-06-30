> LZ-Says：
> 
>   江湖上流传着这样一首诗：
>  <center> 床前明月光，我会写代码；千山鸟飞绝，我会写代码；
松下问童子，我会写代码；春眠不觉晓，我会写代码；
白日依山尽，我会写代码；红豆生南国，我会写代码；
锄禾日当午，我会写代码；欲穷千里目，我会写代码。
![这里写图片描述](http://img.blog.csdn.net/20170608220916243?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

不得不说，开发这一行，是真心不容易，要学习技术，要学习调整心态，要学会和各种无奈心平气和沟通，不容易~

今天突然有点想家了，时间过得真快，就感觉一切好像在昨天，不过回头看，一切不过尔尔~

## 前言
以前对于Android这边权限真的是一窍不通，偶尔还忘记写这个东西，静下心，真的该好好想想这东西到底是什么情况。

今天写个一个小demo，不出意料。感觉有点无奈。大家一起回顾下这个例子，之后我们针对这个例子，开启今天权限之旅~

## Permission Study（权限有关知识学习）
### 1.小例子开启权限之旅~
**来个Demo瞅瞅**

1.设置相机权限
> uses-permission android:name="android.permission.CAMERA"

2.设置Android兼容版本为23 此处忽略实际调用相机代码。

>         minSdkVersion 9
>         
        targetSdkVersion 23

关于Android版本号这块，特意为大家截个图，方便大家在查阅过程中记一下~

<center>![这里写图片描述](http://img.blog.csdn.net/20170611192407498?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

来来来，我们运行看看会有什么结果发生

结果如下：

<center>![这里写图片描述](http://img.blog.csdn.net/20170611211801656?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

查看异常如下：

<center>![这里写图片描述](http://img.blog.csdn.net/20170611212225069?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

3.将Android兼容版本设置为21

>         minSdkVersion 9
>         
        targetSdkVersion 21

接下来再看看结果：

<center>![这里写图片描述](http://img.blog.csdn.net/20170611212834577?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

看到这个，不知道大家有没有在想一个问题，为什么同样是调用相机这简单操作，在兼容Android 系统21以及23后运行结果会发生变化呢？

看看异常，看看问题，还是直接看看官方文档看看这是什么情况吧。

### 2.Android 6.0新特性（只针对权限管理部分）

Android 6.0（API 级别 23）除了提供诸多新特性和功能外，还对系统和 API 行为做出了各种变更，而我们今天重点关注**权限**方面的处理。

Android 6.0引入了一种新的权限模式，也就是我们常说的**"运行时动态申请权限"**。如今，用户可直接在运行时管理应用权限。这种模式让用户能够更好地了解和控制权限，同时为应用开发者精简了安装和自动更新过程。用户可为所安装的各个应用分别授予或撤销权限。

如果应用在清单中列出正常权限（即不会对用户隐私或设备操作造成很大风险的权限），系统会自动授予这些权限。如果应用在其清单中列出危险权限（即可能影响用户隐私或设备正常操作的权限），系统会要求用户明确授予这些权限。Android 发出请求的方式取决于系统版本，而系统版本是应用的目标，具体情况在下面分别进行说明：

>  •&nbsp;&nbsp;如果设备运行的是 **Android 6.0（API 级别 23）或更高版本，并且应用的 targetSdkVersion 是 23 或更高版本，则应用在运行时向用户请求权限**。<font color=#FF0000>用户可随时调用权限</font>，因此应用在每次运行时均需检查自身是否具备所需的权限；
> 
> •&nbsp;&nbsp;如果设备运行的是 **Android 5.1（API 级别 22）或更低版本，并且应用的 targetSdkVersion 是 22 或更低版本，则系统会在用户安装应用时要求用户授予权限**。如果将新权限添加到更新的应用版本，系统会在用户更新应用时要求授予该权限。<font color=#FF0000>用户一旦安装应用，撤销权限的唯一方式是卸载应用。
### 3.Permission 简单了解
通常，权限失效会导致 SecurityException 被扔回应用。但不能保证每个地方都是这样。例如，sendBroadcast(Intent) 方法在数据传递到每个接收者时会检查权限，在方法调用返回后，即使权限失效，我们也不会收到异常。但在几乎所有情况下，权限失效会记入系统日志。**（排除某些特例）**

此外，任何应用都可定义并实施自己的权限，因此这不是所有可能权限的详尽列表。So，我们可能在程序运行期间的多个位置实施特定权限，下面为大家简单举几个例子：

> •	在调用系统时，防止应用执行某些功能；
> 
>•	在启动 Activity 时，防止应用启动其他应用的 Activity；
>
>•	在发送和接收广播时，控制谁可以接收你的广播，谁可以向你发送广播；
>
>•	在访问和操作内容提供程序时；
>
>•	绑定至服务或启动服务。

### 4.Permission 分类 （正常权限 or 危险权限）
系统权限分为几个保护级别。需要了解的两个最重要保护级别是**正常权限**和**危险权限**：

•	正常权限涵盖应用需要访问其沙盒外部数据或资源，但对用户隐私或其他应用操作风险很小的区域。例如，设置时区的权限就是正常权限。如果应用声明其需要正常权限，系统会自动向应用授予该权限。如需当前正常权限的完整列表，请文章尾部查阅正常权限访问地址链接。

•	危险权限涵盖应用需要涉及用户隐私信息的数据或资源，或者可能对用户存储的数据或其他应用的操作产生影响的区域。例如，能够读取用户的联系人属于危险权限。如果应用声明其需要危险权限，则用户必须明确向应用授予该权限。
### 5.Permission Group
**所有危险的 Android 系统权限都属于权限组**。如果设备运行的是 Android 6.0（API 级别 23），并且应用的targetSdkVersion 是 23 或更高版本，则当用户请求危险权限时系统会发生以下行为：

•	<font color=#FF0000>如果应用请求其清单中列出的危险权限，而应用目前在权限组中没有任何权限，则系统会向用户显示一个对话框，描述应用要访问的权限组。对话框不描述该组内的具体权限。</font> 例如，如果应用请求READ_CONTACTS 权限，系统对话框只说明该应用需要访问设备的联系信息。如果用户批准，系统将向应用授予其请求的权限。

•	<font color=#FF0000>如果应用请求其清单中列出的危险权限，而应用在同一权限组中已有另一项危险权限，则系统会立即授予该权限，而无需与用户进行任何交互。</font> 例如，如果某应用已经请求并且被授予了 READ_CONTACTS 权限，然后它又请求 WRITE_CONTACTS，系统将立即授予该权限。

**<font color=#FF0000>任何权限都可属于一个权限组，包括正常权限和应用定义的权限。但权限组仅当权限危险时才影响用户体验。可以忽略正常权限的权限组。**

**如果设备运行的是 Android 5.1（API 级别 22）或更低版本，并且应用的 targetSdkVersion 是 22 或更低版本，则系统会在安装时要求用户授予权限。<font color=#FF0000>再次强调，系统只告诉用户应用需要的权限组，而不告知具体权限。**

下面为大家附上危险权限以及危险权限组图：

<center>![这里写图片描述](http://img.blog.csdn.net/20170612012510899?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## Permission Solve（解决权限问题）
### 红橙Darren 案例1 
运行结果图(测试机为红米note4 Android 6.0)：

<center>![这里写图片描述](http://img.blog.csdn.net/20170613094047774?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

实现起来步骤如下：

1.**设置兼容Android API为21**；
2.申请权限 回调中根据状态码处理 授予权限后进行拨打电话

源码如下：

```
public class HongChengActivity1 extends Activity {

    // 打电话权限申请的请求码
    private static final int CALL_PHONE_REQUEST_CODE = 0x0011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hong_cheng1);
    }

    public void phoneClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "申请权限", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{"Manifest.permission.CALL_PHONE"}, CALL_PHONE_REQUEST_CODE);
        } else {
            callPhone();
        }
    }

    /**
     * 拨打电话
     **/
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:114");
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PHONE_REQUEST_CODE) {
            if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                callPhone();
            } else {
                // Permission Denied
                Toast.makeText(this, "权限被拒绝了", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

```
### 红橙Darren 权限管理框架
#### 1.眼见为实 查验运行结果
首先来看下使用其封装的权限管理框架结果，由于测试机短缺，暂时对红米Note4 6.0，以及乐视 6.0.1 进行简单测试，如下：

红米Note4 Android 系统版本：6.0 测试结果如下：

<center>![这里写图片描述](http://img.blog.csdn.net/20170614223653588?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

乐视 Android 系统版本：6.0.1 测试结果如下：

<center>![这里写图片描述](http://img.blog.csdn.net/20170614223814559?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
#### 2.确定实现思路以及使用方式
主要中心思想是通过**反射加注解**去对权限进行管理，大家记住~

撸码之前，我们要掌握如下俩点：

**首先要明确操作流程，简单如下：**
<font color=#FF0000>1.  判断当前系统版本是否小于6.0，如果小于，直接通过反射去获取方法直接执行操作（可以理解为直接拨打电话），由于我们不知道要执行哪儿个方法，所以我们只能通过注解的方式去给方法设置Tag，之后通过反射去执行这个方法；
2. 如果当前设备系统版本大于等于6.0，判断权限是否被用户授予。首先获取用户拒绝的权限，校验返回的权限组，如果返回权限组中有用户拒绝的权限，我们直接开启申请权限，否则直接通过反射执行该方法。

针对以上俩点，我们进行逐个细化，随便编码实现。

关于使用，我们有Build模式以及链式方式，**红橙说，链式方式比较有逼格**，So，秒懂？

#### 3. 撸码实现

首先我们需要定义一个Helper类，这个类的主要作用就是提供方法去让我们进行链式调用。那么我们现在想想，我们应该去传什么参数好呢？

-  当前调用的Activity or Fragment，这个是不可缺少的吧，关于这块，我们直接设定为一个Object；

-  请求肯定会有一个请求码，不然我怎么去区分你到底是要干嘛？所以，定义一个requestCode；

-  最后一个参数当然是请求的权限组了呗，所以我们定义一个String[]数组去接收请求的权限数组，顺便捎带着把我们这个调用类初始化下。

相关代码如下：


```
    // 需要反射的类
    private Object mObject;
    // 请求码
    private int mRequestCode;
    // 请求权限数组
    private String[] mRequestPermission;

    private PermissionHelper(Object object) {
        this.mObject = object;
    }
```

既然我们是通过链式调用，我们必须要提供一个入口，而这个入口又必须支持Activity或者Fragment，如下：

```
    // 链式传参

    /**
     * 兼容Activity
     *
     * @param activity
     * @return
     */
    public static PermissionHelper with(Activity activity) {
        return new PermissionHelper(activity);
    }

    /**
     * 兼容 Fragment
     *
     * @param fragment
     * @return
     */
    public static PermissionHelper with(Fragment fragment) {
        return new PermissionHelper(fragment);
    }

```

兼容了主入口，下面我们要对开始设定的参数进行初始化，话说你不给我入口，我怎么用，怎么传参呢，别急，继续往下看：

```
    /**
     * 添加请求码
     *
     * @param requestCode
     * @return
     */
    public PermissionHelper requestCode(int requestCode) {
        this.mRequestCode = requestCode;
        return this;
    }

    /**
     * 添加请求权限数组
     *
     * @param permissions
     * @return
     */
    public PermissionHelper requestPermission(String... permissions) {
        this.mRequestPermission = permissions;
        return this;
    }

```

刚才说了，我们首先要去判断当前设备是不是6.0，你整个4.4的设备过来，问我动态请求权限，那不是闹呢~！

为了避免一个类中代码太多，抽取工具类，暂定为PermissionUtils。而这个工具类又不能被别人轻易地实例化，那我们直接禁止实例化，也就是禁止使用new的方式去创建，这时候你会问了，不让我new，那让我怎么办？别急往下一步步看：

```
    private PermissionUtils() {
        throw new UnsupportedOperationException("不能被实例化");
    }

    /**
     * 判断当前系统版本是否大于等于6.0
     *
     * @return
     */
    public static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
```

刚刚我们说，如果当前设备是6.0以下，我们判断它已经获取到权限(显而易见有成功就有失败)，但是我们对执行的方法不确定，只能采用注解方式给方法设置Tag，然后通过反射去执行，如下。

定义成功注解类：
```
/**
 * Created by HLQ on 2017/6/13
 */
@Target(ElementType.METHOD) // 放在什么位置 METHOD 放在方法上 FIELD 放在属性上 TYPE 放在类上
@Retention(RetentionPolicy.RUNTIME) // 编译时检测 or 运行时检测
public @interface PermissionSucceed {

    // 注解的作用就是在反射的时候做一个标记 一般配合类的反射使用
    // 请求码
    public int requestCode();

}
```

定义失败注解类：

```
/**
 * Created by HLQ on 2017/6/13
 */
@Target(ElementType.METHOD) // 放在什么位置 METHOD 放在方法上 FIELD 放在属性上 TYPE 放在类上
@Retention(RetentionPolicy.RUNTIME) // 编译时检测 or 运行时检测
public @interface PermissionFail {

    // 注解的作用就是在反射的时候做一个标记 一般配合类的反射使用
    // 请求码
    public int requestCode();

}
```

在Helper中编写请求方法，顺便实现：

```
    /**
     * 判断权限以及请求权限
     */
    public void request() {
        // 判断当前系统版本是否大于等于6.0
        if (!PermissionUtils.isOverMarshmallow()) {
            // 小于6.0 直接执行方法 通过反射去获取
            // 对执行的方法不确定 只能采用注解方式给方法设置Tag 通过反射去执行
            PermissionUtils.executeSucceedMethod(mObject, mRequestCode);
            return;
        }
        // 大于等于6.0 判断权限是否授予
        // 获取用户拒绝的权限 检测权限
        List<String> deniedPermissions = PermissionUtils.getDeniedPermissions(mObject, mRequestPermission);
        // 权限被授予 反射获取执行方法
        if (deniedPermissions.size() == 0) {
            // 用户授予请求权限
            PermissionUtils.executeSucceedMethod(mObject, mRequestCode);
        } else {
            // 权限被拒绝 申请权限
            ActivityCompat.requestPermissions(PermissionUtils.getActivity(mObject), deniedPermissions.toArray(new String[deniedPermissions.size()]), mRequestCode);
        }
    }

```

在Util中实现成功以及失败的方法：

```
    /**
     * 执行成功
     *
     * @param mClass
     * @param requestCode
     */
    public static void executeSucceedMethod(Object reflectObject, int requestCode) {
        // 获取class中所有方法去
        Method[] methods = reflectObject.getClass().getDeclaredMethods();
        // 遍历找到设置Tag的方法
        for (Method method : methods) {
//            Log.e("HLQ_Struggle", "找到的方法：" + method + "");
            // 获取该方法上面有没有设置成功的Tag
            PermissionSucceed succeedMethod = method.getAnnotation(PermissionSucceed.class);
            if (succeedMethod != null) {
                // 代表该方法设置Tag
                // 判断请求码是否一致
                int methodCode = succeedMethod.requestCode();
                if (methodCode == requestCode) {
                    // 找到方法 通过反射执行该方法
                    Log.e("HLQ_Struggle", "找到了该方法：" + method);
                    executeMethod(reflectObject, method);
                }
            }
        }
    }

    /**
     * 执行失败
     *
     * @param object
     * @param requestCode
     */
    public static void executeFailMethod(Object reflectObject, int requestCode) {
        // 获取class中所有方法去
        Method[] methods = reflectObject.getClass().getDeclaredMethods();
        // 遍历找到设置Tag的方法
        for (Method method : methods) {
            Log.e("HLQ_Struggle", method + "");
            // 获取该方法上面有没有设置失败的Tag
            PermissionFail faildMethod = method.getAnnotation(PermissionFail.class);
            if (faildMethod != null) {
                // 代表该方法设置Tag
                // 判断请求码是否一致
                int methodCode = faildMethod.requestCode();
                if (methodCode == requestCode) {
                    // 找到方法 通过反射执行该方法
                    Log.e("HLQ_Struggle", "找到了失败的方法：" + method);
                    executeMethod(reflectObject, method);
                }
            }
        }
    }
```

紧接着，通过反射指定该方法：

```
    /**
     * 反射执行方法
     *
     * @param reflectObject
     * @param method
     */
    private static void executeMethod(Object reflectObject, Method method) {
        // 反射执行方法 该方法所属类
        try {
            // 允许执行私有方法
            method.setAccessible(true);
            method.invoke(reflectObject, new Object[]{});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
```

当前设备大于等于6.0，我们直接读取用户拒绝的权限：

    /**
     * 获取用户拒绝权限组
     *
     * @param mObject            Activity Fragment
     * @param mRequestPermission
     * @return
     */
    public static List<String> getDeniedPermissions(Object object, String[] requestPermissions) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String requestPermission : requestPermissions) {
            // 将用户拒绝的权限添加集合
            if (ContextCompat.checkSelfPermission(getActivity(object), requestPermission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(requestPermission);
            }
        }
        return deniedPermissions;
    }

    /**
     * 获取Context
     *
     * @param mObject
     * @return
     */
    public static Activity getActivity(Object mObject) {
        if (mObject instanceof Activity) {
            return (Activity) mObject;
        }
        if (mObject instanceof Fragment) {
            return ((Fragment) mObject).getActivity();
        }
        return null;
    }

下面，我们直接在权限回调中处理返回结果即可：

```
    /**
     * 处理权限回调
     *
     * @param requestCode  申请权限Code码
     * @param permissions  申请的权限数组
     * @param grantResults
     */
    public static void requestPermissionsResult(Object object, int requestCode, String[] permissions) {
        // 再次获取用户拒绝的权限
        List<String> deniedPermissions = PermissionUtils.getDeniedPermissions(object, permissions);
        if (deniedPermissions.size() == 0) {
            // 用户已授权申请的权限
            PermissionUtils.executeSucceedMethod(object, requestCode);
        } else {
            // 申请的权限中 有用户不同意的
            PermissionUtils.executeFailMethod(object, requestCode);
        }
    }
```

同样，我们也需要对外提供一个入口，方便他人直接调用：

```
    /**
     * 请求权限
     *
     * @param activity
     * @param requestCode
     * @param permissions
     */
    public static void requestPermission(Activity activity, int requestCode, String[] permissions) {
        PermissionHelper.with(activity).requestCode(requestCode).requestPermission(permissions).request();
    }
```

到这里，关于Android 6.0运行时动态申请权限封装框架已完毕，那么我们该如何使用呢？看下面提供的一个小demo：

```
/**
 * 权限管理简单处理 create by heliquan at 2017年6月13日
 */
public class HongChengActivity2 extends Activity {

    private static final int CALL_PHONR_REQUEST_CODE = 0x0011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hong_cheng1);
        findViewById(R.id.id_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionHelper.with(HongChengActivity2.this).requestCode(CALL_PHONR_REQUEST_CODE).requestPermission(Manifest.permission.CALL_PHONE).request();
            }
        });
    }

    /**
     * 拨打电话
     **/
    @PermissionSucceed(requestCode = CALL_PHONR_REQUEST_CODE)
    public void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:114");
        intent.setData(data);
        startActivity(intent);
    }

    @PermissionFail(requestCode = CALL_PHONR_REQUEST_CODE)
    private void callPhoneFail() {
        Toast.makeText(this, "您拒绝了拨打电话", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("HLQ_Struggle", "=====================>CALL_PHONR_REQUEST_CODE：" + CALL_PHONR_REQUEST_CODE);
        Log.e("HLQ_Struggle", "=====================>请求码：" + requestCode);
        PermissionHelper.requestPermissionsResult(this, requestCode, permissions);
    }
}

```
### RxPermissions使用
#### 老规矩 上运行结果
<center>![这里写图片描述](http://img.blog.csdn.net/20170616001906621?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

这里不得不吐槽小米，有时候拒绝权限，它自动设置为以后不再提醒，恶心要死~！！！

#### RxPermissions简介

**RxPermissions是帮助开发者简化requestPermissions()相关的处理。**

（1） 开发者不用担心Android运行环境的版本，如果系统是Android 6.0之前的版本，RxPermissions返回的结果是，app请求的每个权限都被允许（granted）。RxPermissions内部已经对Android版本进行了判断：

```
    boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public boolean isGranted(String permission) {
        // 如果是Android 6.0 （Api 23）之前，则权限被允许使用。
        return !isMarshmallow() || mRxPermissionsFragment.isGranted(permission);
    }
```

（2） 将权限申请的代码（requestPermissions()）和请求结果的代码（onRequestPermissionsResult()）放在一起管理，避免了代码的分散。

（3） RxPermissions具备Rx（RxJava）的特性，例如可以使用链式操作，可以执行filter操作，transform操作等等。

顺便在这里提一下：

> **RxPermissions是基于RxJava**的，RxJava现在有2个大版本，一个RxJava 1.x（通常说RxJava），另一个是RxJava2。所以RxPermissions有2个版本，分别支持RxJava和RxJava2。

#### 使用注意事项
参考 https://github.com/tbruyelle/RxPermissions 中的README。

（1） minSdkVersion必须 >= 11。

（2） 使用RxPermissions申请权限，必须在Activity.onCreate()或者View.onFinishInflate()中处理。不能在onResume()中处理，因为onResume()在App的生命周期中可能执行的很频繁。如果在请求权限的时候，App重新启动了（例如屏幕旋转导致的App关闭再重新创建），那么用户的选择（允许 或者 拒绝）将无法发给App。 更多讨论，请参考：https://github.com/tbruyelle/RxPermissions/issues/69

#### 撸码 

1.导入依赖库
```
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
    compile 'io.reactivex.rxjava2:rxjava:2.0.5'
    compile 'com.tbruyelle.rxpermissions2:rxpermissions:0.9.3@aar'
```

2.在点击事件中请求

```
        findViewById(R.id.id_rx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions rxPermission = new RxPermissions(RxPermissionsActivity.this);
                rxPermission
                        .requestEach(
                                Manifest.permission.CAMERA)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) throws Exception {
                                if (permission.granted) {
                                    // 用户已经同意该权限
                                    Toast.makeText(RxPermissionsActivity.this, "成功~", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivity(intent);
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                                    Toast.makeText(RxPermissionsActivity.this, "拒绝~", Toast.LENGTH_SHORT).show();
                                } else {
                                    // 用户拒绝了该权限，并且选中『不再询问』
                                    Toast.makeText(RxPermissionsActivity.this, "不在询问~", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
```

当然还有一种办法就是直接在首页进行申请应用所需要的权限，如下：

```
    /**
     * 请求权限
     */
    private void requestPermissions() {
        RxPermissions rxPermissions = new RxPermissions(self);
        rxPermissions.requestEach(
                Manifest.permission.ACCESS_COARSE_LOCATION, // 用于进行网络定位
                Manifest.permission.ACCESS_FINE_LOCATION,   // 用于访问GPS定位
                Manifest.permission.CAMERA // 拍照
        ).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) { // 用户授予该权限 让我们尽情浪去吧
                } else if (permission.shouldShowRequestPermissionRationale) { // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                } else { // 用户拒绝了该权限，并且选中『不再询问』

                }
            }
        });
    }
```

以上俩种方式大家可随意选择~

### PermissionsDispatcher使用
#### 简介
用一句话简单概括就是：<font color=#FF0000>基于**注释**的API来处理运行时权限。
#### 优势

- 100％无反射；

-  支持特殊权限；

-  支持小米 <font color=#FF0000>在这里只能呵呵了 这小米 不过不得不佩服人家的工程师~

#### 注释含义
PermissionsDispatcher仅引入了几个注释，保持其通用的API简洁，具体如下：
<center>![这里写图片描述](http://img.blog.csdn.net/20170617001033540?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast) 

对此，官方为我们提供了一个小例子，结合上图查看代码后，我们来实际coding~

```
@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sample_content_fragment, CameraPreviewFragment.newInstance())
                .addToBackStack("camera")
                .commitAllowingStateLoss();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
            .setMessage(R.string.permission_camera_rationale)
            .setPositiveButton(R.string.button_allow, (dialog, button) -> request.proceed())
            .setNegativeButton(R.string.button_deny, (dialog, button) -> request.cancel())
            .show();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void showDeniedForCamera() {
        Toast.makeText(this, R.string.permission_camera_denied, Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void showNeverAskForCamera() {
        Toast.makeText(this, R.string.permission_camera_neverask, Toast.LENGTH_SHORT).show();
    }
}
```
编译时，PermissionsDispatcher为我们Activity（[活动名称] + PermissionsDispatcher）生成一个类，我们可以使用该类来安全地访问这些受权限保护的方法，而我们唯一要做的就是将权限处理委托委托给这个帮助类，那么接下来我们一起来看看官方提供的小例子。

```
// This file was generated by PermissionsDispatcher. Do not modify!
package permissions.dispatcher.sample;

import android.support.v4.app.ActivityCompat;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

import java.lang.ref.WeakReference;

final class MainActivityPermissionsDispatcher {
    private static final int REQUEST_SHOWCAMERA = 0;

    private static final String[] PERMISSION_SHOWCAMERA = new String[] {"android.permission.CAMERA"};

    private MainActivityPermissionsDispatcher() {
    }

    static void showCameraWithCheck(MainActivity target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCAMERA)) {
            target.showCamera();
        } else {
            if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCAMERA)) {
                target.showRationaleForCamera(new ShowCameraPermissionRequest(target));
            } else {
                ActivityCompat.requestPermissions(target, PERMISSION_SHOWCAMERA, REQUEST_SHOWCAMERA);
            }
        }
    }

    static void onRequestPermissionsResult(MainActivity target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SHOWCAMERA:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.showCamera();
                } else {
                    if (!PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCAMERA)) {
                        target.onCameraNeverAskAgain();
                    } else {
                        target.onCameraDenied();
                    }
                }
                break;
            default:
                break;
        }
    }

    private static final class ShowCameraPermissionRequest implements PermissionRequest {
        private final WeakReference<MainActivity> weakTarget;

        private ShowCameraPermissionRequest(MainActivity target) {
            this.weakTarget = new WeakReference<MainActivity>(target);
        }

        @Override
        public void proceed() {
            MainActivity target = weakTarget.get();
            if (target == null) return;
            ActivityCompat.requestPermissions(target, PERMISSION_SHOWCAMERA, REQUEST_SHOWCAMERA);
        }

        @Override
        public void cancel() {
            MainActivity target = weakTarget.get();
            if (target == null) return;
            target.onCameraDenied();
        }
    }
}
```

#### 初步了解之后，还是来点实际的吧。撸码，走起~

1.老规矩 查看运行结果：测试设备为乐视

1-1 用户拒绝
<center>![这里写图片描述](http://img.blog.csdn.net/20170618235314165?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

1-2 用户允许

<center>![这里写图片描述](http://img.blog.csdn.net/20170618235340181?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

2.添加依赖

```
    compile 'com.github.hotchemi:permissionsdispatcher:2.3.2'
    annotationProcessor 'com.github.hotchemi:permissionsdispatcher-processor:2.3.2'
```

3.编写基础类

```
package cn.hlq.androidrightsmanagement.permissionsdispatcher;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import cn.hlq.androidrightsmanagement.R;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * permissionsdispatcher简单使用 create by heliquan at 2017年6月18日
 */
@RuntimePermissions // 标记需要运行时判断的类(用于动态生成代理类)
public class DispatcherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions_dispatcher);
        findViewById(R.id.id_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    @NeedsPermission(Manifest.permission.CAMERA)
        // 标记需要检查权限的方法
    void showToast() {
        startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), 200);
    }

    @OnShowRationale(Manifest.permission.CAMERA)
        // 授权提示回调
    void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this).setMessage("不给权限你试试~")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();//请求权限
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
        // 授权被拒绝回调
    void denied() {
        Toast.makeText(this, "丫的，不给我权限！", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
        // 授权被拒绝并不再提醒回调
    void neverAskAgain() {
        Toast.makeText(this, "丫的，还点击不再询问，fuck！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
    }
}

```

4.Make Project

还记得我们上面说的编译后结果吗？回顾一下吧~

> 编译时，PermissionsDispatcher为我们Activity（[活动名称] + PermissionsDispatcher）生成一个类，我们可以使用该类来安全地访问这些受权限保护的方法，而我们唯一要做的就是将权限处理委托委托给这个帮助类，那么接下来我们一起来看看官方提供的小例子。

So 基于此项目，编译后生成DispatcherActivityPermissionsDispatcher类，我们会基于此类进行权限处理

生成后，我们修改下代码，如下：

```
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions_dispatcher);
        findViewById(R.id.id_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DispatcherActivityPermissionsDispatcher.showToastWithCheck(DispatcherActivity.this);
            }
        });
    }
```

```
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        DispatcherActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
```

> MD，搞这个搞得是真恶心，一开始库依赖不上，接下来网上各种搜索结果又不是很完善，好到搞出来了。



## GitHub 地址

> https://github.com/HLQ-Struggle/AndroidRightsManagement

## 参考文献

> 1.  谷歌官方地址：https://developer.android.google.cn/about/versions/marshmallow/android-6.0-changes.html#behavior-runtime-permissions；
> 2. 谷歌官方权限说明：https://developer.android.google.cn/reference/android/Manifest.permission.html；
> 3. 正常权限：https://developer.android.google.cn/guide/topics/permissions/normal-permissions.html；
> 4. 红橙Darren简书地址：http://www.jianshu.com/u/35083fcb7747；
> 5. HongChengDarren CSDN地址：http://blog.csdn.net/z240336124；
> 6. RxPermissions地址：https://github.com/tbruyelle/RxPermissions；
> 7. Abracadabra地址： http://blog.csdn.net/u013553529/article/details/68948971；
> 8.  PermissionsDispatcher开源库地址:https://github.com/hotchemi/PermissionsDispatcher


感谢如上各位，前人栽树，后人乘凉，Thanks
