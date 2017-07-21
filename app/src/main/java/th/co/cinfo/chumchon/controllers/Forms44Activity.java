package th.co.cinfo.chumchon.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.ModelToken;

public class Forms44Activity extends AppCompatActivity implements View.OnClickListener {
    ImageButton ibtF001, ibtF002, ibtF003, ibtF004;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms44);
        ModelToken.checkToken(this);
        init();
    }

    private void init() {
        ibtF001 = (ImageButton) findViewById(R.id.ibtF001);
        ibtF002 = (ImageButton) findViewById(R.id.ibtF002);
        ibtF003 = (ImageButton) findViewById(R.id.ibtF003);
        ibtF004 = (ImageButton) findViewById(R.id.ibtF004);

        ibtF001.setOnClickListener(this);
        ibtF002.setOnClickListener(this);
        ibtF003.setOnClickListener(this);
        ibtF004.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == ibtF001){
            intent = new Intent(getApplicationContext(), F001Activity.class);
            startActivity(intent);
        }
        if(v == ibtF002){
            intent = new Intent(getApplicationContext(), F002Activity.class);
            startActivity(intent);
        }
        if(v == ibtF003){
            intent = new Intent(getApplicationContext(), F003Activity.class);
            startActivity(intent);
        }
        if(v == ibtF004){
            intent = new Intent(getApplicationContext(), F004Activity.class);
            startActivity(intent);
        }
    }
}
