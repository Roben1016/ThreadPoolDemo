package com.roshine.threadpooldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.roshine.threadpooldemo.threadpool1.ThreadPoolProxyFactory;
import com.roshine.threadpooldemo.threadpool2.ThreadPoolManage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_factory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThreadPoolProxyFactory.getDownloadThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "动态代理线程池", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        findViewById(R.id.btn_manage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThreadPoolManage.getThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "单例线程管理", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }
}
