package th.co.cinfo.chumchon.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.ModelToken;

/**
 * Created by thanatat on 7/5/17.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton imageButtonInsert, imageButtonShowData, imageButtonMap, imageButtonSetting;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ModelToken.checkToken(this);
        init();
    }

    private void init() {
        imageButtonInsert = (ImageButton) findViewById(R.id.imageButtonInsert);
        imageButtonShowData = (ImageButton) findViewById(R.id.imageButtonShowData);
        imageButtonMap = (ImageButton) findViewById(R.id.imageButtonMap);
        imageButtonSetting = (ImageButton) findViewById(R.id.imageButtonSetting);

        imageButtonInsert.setOnClickListener(this);
        imageButtonShowData.setOnClickListener(this);
        imageButtonMap.setOnClickListener(this);
        imageButtonSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == imageButtonInsert){
            intent = new Intent(getApplicationContext(), InsertActivity.class);
            startActivity(intent);
        }
        if (v == imageButtonShowData) {
            intent = new Intent(getApplicationContext(), ShowDataActivity.class);
            startActivity(intent);
        }
        if (v == imageButtonMap) {

        }
        if (v == imageButtonSetting) {
            intent = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(intent);
        }

    }
}
