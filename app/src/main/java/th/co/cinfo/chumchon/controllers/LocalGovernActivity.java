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

public class LocalGovernActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listLocalGovern;
    Button btnRefresh;
    String apiURL = "https://api.cinfo.co.th/v3/getGroupAsset_T3?";
    String whatUWant = "task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_govern);
        ModelToken.checkToken(this);
        init();
        ModelGetJson.getHeadJson(this, apiURL, whatUWant, listLocalGovern);
    }

    private void init() {
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        listLocalGovern = (ListView) findViewById(R.id.listLocalGovern);

        btnRefresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnRefresh) {
            ModelGetJson.getHeadJson(this, apiURL, whatUWant, listLocalGovern);
        }
    }
}
