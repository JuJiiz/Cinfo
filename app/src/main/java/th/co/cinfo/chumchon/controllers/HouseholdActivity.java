package th.co.cinfo.chumchon.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.*;

public class HouseholdActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listViewHousehold;
    Button btnRefresh;
    String apiURL = "https://api.cinfo.co.th/v2/getTaskList_F01_01?";
    String whatUWant = "Houses";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household);
        ModelToken.checkToken(this);
        init();
        ModelGetJson.getChildJson(this, apiURL, whatUWant, listViewHousehold);
    }

    void init() {
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        listViewHousehold = (ListView) findViewById(R.id.listHousehold);

        btnRefresh.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == btnRefresh) {
            ModelGetJson.getChildJson(this, apiURL, whatUWant, listViewHousehold);
        }
    }
}