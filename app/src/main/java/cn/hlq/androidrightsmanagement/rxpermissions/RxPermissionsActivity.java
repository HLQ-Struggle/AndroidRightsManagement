package cn.hlq.androidrightsmanagement.rxpermissions;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import cn.hlq.androidrightsmanagement.R;
import io.reactivex.functions.Consumer;

public class RxPermissionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_permissions);
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
    }
}
