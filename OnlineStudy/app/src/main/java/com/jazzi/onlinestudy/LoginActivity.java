package com.jazzi.onlinestudy;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jazzi.onlinestudy.util.IpConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText userPhone;
    private EditText userPassword;
    private Button login;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        userPhone=(EditText) findViewById(R.id.lg_username);
        userPassword=(EditText) findViewById(R.id.lg_password);
        login=(Button) findViewById(R.id.lg_go);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestWithOkHttp();
            }
        });
    }
    private void sendRequestWithOkHttp(){//发送网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder()
                            .add("Telephone",userPhone.getText().toString())
                            .add("Password",userPassword.getText().toString())
                            .build();
                    Request request=new Request.Builder()
                            .url(IpConfig.getIp()+"/user/login")
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    if (!responseData.equals("")){
                        parseClassJson(responseData);
                    }else {
                            Message message=new Message();
                            handler.sendMessage(message);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseClassJson(String jsonData){//解析json
        try{
            JSONArray jsonArray =new JSONArray(jsonData);
                JSONObject jsonObject=jsonArray.getJSONObject(0);
                String userId=jsonObject.getString("userID");
                String userName=jsonObject.getString("userName");
                String uimagePath=jsonObject.getString("uimagePath");
                UserInformation.id=userId;
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("userID",userId);
                intent.putExtra("userName",userName);
                intent.putExtra("uimagePath",uimagePath);
                startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            userPhone.setText("");
            userPassword.setText("");
            Toast.makeText(LoginActivity.this, "登录失败，请重新输入", Toast.LENGTH_LONG).show();
        }
    };
}
