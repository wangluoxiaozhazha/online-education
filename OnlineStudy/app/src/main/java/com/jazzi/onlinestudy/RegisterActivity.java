package com.jazzi.onlinestudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jazzi.onlinestudy.util.IpConfig;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {


    private EditText userName;
    private EditText userPhone;
    private EditText userPassword;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        userName=(EditText)findViewById(R.id.et_username);
        userPhone=(EditText)findViewById(R.id.phone);
        userPassword=(EditText)findViewById(R.id.et_password);
        register=(Button)findViewById(R.id.bt_go);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequestWithOkHttp();
            }
        });
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
            try {
                OkHttpClient client=new OkHttpClient();
                RequestBody requestBody=new FormBody.Builder()
                        .add("UserName",userName.getText().toString())
                        .add("Telephone",userPhone.getText().toString())
                        .add("Password",userPassword.getText().toString())
                        .build();

                Request request=new Request.Builder()
                        .url(IpConfig.getIp()+"/user/add")
                        .post(requestBody)
                        .build();
                Response response=client.newCall(request).execute();
                String responseData=response.body().string();
                if(responseData.equals("成功")){
                    Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_LONG);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            }
        }).start();
    }
}
