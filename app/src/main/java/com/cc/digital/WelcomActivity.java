package com.cc.digital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flaviofaria.kenburnsview.KenBurnsView;

public class WelcomActivity extends AppCompatActivity {
    private KenBurnsView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        mImg = (KenBurnsView) findViewById(R.id.img);

        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}
