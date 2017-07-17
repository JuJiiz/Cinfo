package th.co.cinfo.chumchon.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.ModelToken;

public class F001Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f001);
        ModelToken.checkToken(this);
    }
}
