package cn.hlq.androidrightsmanagement.hongcheng;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import cn.hlq.androidrightsmanagement.R;

/**
 * 权限管理简单处理
 */
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
