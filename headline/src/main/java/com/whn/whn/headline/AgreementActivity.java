package com.whn.whn.headline;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whn.whn.headline.utils.AgreementText;

import static com.whn.whn.headline.R.id.toolbar;

/**
 * Created by somnus-sir on 2017/4/16.
 * new Activity
 */

public class AgreementActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        EditText tvAgreement = (EditText) findViewById(R.id.tv_agreemnet_content);
        Button btBack = (Button) findViewById(R.id.bt_agreement_back);
        Button btChange = (Button) findViewById(R.id.bt_agreement_change);

        tvAgreement.setText(AgreementText.Text);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AgreementActivity.this, "无法更改协议", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
