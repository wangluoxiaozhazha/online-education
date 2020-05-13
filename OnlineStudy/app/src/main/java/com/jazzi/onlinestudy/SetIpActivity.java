package com.jazzi.onlinestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jazzi.onlinestudy.util.IpConfig;

public class SetIpActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_ip);
        editText=(EditText)findViewById(R.id.edit_ip);
        button=(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip=editText.getText().toString();
                IpConfig.setIP(ip);
                Toast.makeText(SetIpActivity.this,"IP设置成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
