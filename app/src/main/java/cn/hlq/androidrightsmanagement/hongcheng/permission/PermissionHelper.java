package cn.hlq.androidrightsmanagement.hongcheng.permission;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.ActivityCompat;

import java.util.List;

/**
 * Created by HLQ on 2017/6/13 权限管理工具类(关键)
 */
public class PermissionHelper {

    // 需要反射的类
    private Object mObject;
    // 请求码
    private int mRequestCode;
    // 请求权限数组
    private String[] mRequestPermission;

    private PermissionHelper(Object object) {
        this.mObject = object;
    }

    // 传递参数如下：
    // Object Fragment or Activity
    // int 请求码
    // 需要请求的权限 String[]

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

}
