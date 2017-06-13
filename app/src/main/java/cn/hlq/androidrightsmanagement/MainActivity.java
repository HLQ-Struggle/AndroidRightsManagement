package cn.hlq.androidrightsmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.hlq.androidrightsmanagement.hongcheng.HongChengActivity1;
import cn.hlq.androidrightsmanagement.hongcheng.HongChengActivity2;

/**
 *  create by heliquan at 2017年6月13日
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_hongchen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HongChengActivity1.class));
            }
        });
        findViewById(R.id.btn_hongchen_1).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HongChengActivity2.class));
            }
        });
    }

}
