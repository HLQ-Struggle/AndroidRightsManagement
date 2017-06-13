package cn.hlq.androidrightsmanagement.hongcheng.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HLQ on 2017/6/13  处理权限请求工具类
 */
public class PermissionUtils {

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
            Log.e("HLQ_Struggle", "找到的方法：" + method + "");
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

    /**
     * 获取用户拒绝权限组
     *
     * @param mObject            Activity Fragment
     * @param mRequestPermission
     * @return
     */
    public static List<String> getDeniedPermissions(Object mObject, String[] mRequestPermission) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String requestPermission : mRequestPermission) {
            // 将用户拒绝的权限添加集合
            if (ContextCompat.checkSelfPermission(getActivity(mObject), requestPermission) == PackageManager.PERMISSION_DENIED) {
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
}
