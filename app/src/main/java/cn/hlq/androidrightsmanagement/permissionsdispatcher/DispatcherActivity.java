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
                DispatcherActivityPermissionsDispatcher.showToastWithCheck(DispatcherActivity.this);
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
        DispatcherActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
