package th.co.cinfo.chumchon.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import th.co.cinfo.chumchon.models.ModelGetData;
import th.co.cinfo.chumchon.models.ModelGetJson;
import th.co.cinfo.chumchon.models.ModelToken;

public class CommunityActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listCommunity;
    Button btnRefresh;
    String apiURL = "https://api.cinfo.co.th/v3/getGroupAsset_T4?";
    String whatUWant = "task";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        ModelToken.checkToken(this);
        init();
        ModelGetJson.getHeadJson(this, apiURL, whatUWant, listCommunity);
    }

    private void init() {
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        listCommunity = (ListView) findViewById(R.id.listCommunity);

        btnRefresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnRefresh) {
            ModelGetJson.getHeadJson(this, apiURL, whatUWant, listCommunity);
        }
    }
}
