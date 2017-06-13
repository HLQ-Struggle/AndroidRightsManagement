package cn.hlq.androidrightsmanagement.hongcheng.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
