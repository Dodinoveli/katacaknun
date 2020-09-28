package com.app.katacaknun;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Detail.this,MainActivity.class);
        finish();
        startActivity(intent);
    }
    }
