package cn.hlq.androidrightsmanagement.hongcheng;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import cn.hlq.androidrightsmanagement.R;
import cn.hlq.androidrightsmanagement.hongcheng.permission.PermissionFail;
import cn.hlq.androidrightsmanagement.hongcheng.permission.PermissionHelper;
import cn.hlq.androidrightsmanagement.hongcheng.permission.PermissionSucceed;

/**
 * 权限管理简单处理
 */
public class HongChengActivity2 extends Activity {

    private static final int CALL_PHONR_REQUEST_CODE = 0 * 0011;

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
        PermissionHelper.requestPermissionsResult(this, requestCode, permissions);
    }
}
