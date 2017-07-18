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
import th.co.cinfo.chumchon.models.ModelToken;

public class CommunityActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linearScroll;
    ListView listCommunity;
    Button btnRefresh;
    String NO_COMMUNITY = "number";
    String NAME_COMMUNITY_OWNER = "name";
    String STATUS_COLUMN = "status";
    String status_count = "count";
    ArrayList<HashMap<String, String>> LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        ModelToken.checkToken(this);
        init();
        getData();
    }

    private void init(){
        linearScroll = (LinearLayout) findViewById(R.id.linearScroll);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);

        btnRefresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnRefresh){
            getData();
        }
    }

    private void getData(){
        String apiURL = "https://api.cinfo.co.th/v3/getGroupAsset_T4?";
        listCommunity = (ListView) findViewById(R.id.listCommunity);
        LIST = new ArrayList<HashMap<String, String>>();
        JSONObject jsonObject = null;
        try {
            String strJsonObj = ModelGetData.getByName(this,apiURL,"task");
            JSONArray jsonArray = new JSONArray(strJsonObj);
            for (int i=0 ; i < jsonArray.length(); i++){
                int statusCount = 0, progress = 0;
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                HashMap<String, String> temp = new HashMap<String, String>();
                temp.put(NO_COMMUNITY,jsonObj.getString("ID"));
                temp.put(NAME_COMMUNITY_OWNER,jsonObj.getString("owner"));
                LIST.add(temp);
                temp.put(STATUS_COLUMN, jsonObj.getString("status"));

                JSONArray tmpJsonArray = new JSONArray(jsonObj.getString("asset"));
                if (tmpJsonArray.length() != 0) {
                    for (int j = 0; j < tmpJsonArray.length(); j++) {
                        JSONObject jsonObjcheck = tmpJsonArray.getJSONObject(j);
                        status_count = jsonObjcheck.getString("status");
                        if (!status_count.equals("wait")) {
                            statusCount += 1;
                        }
                    }
                    progress = (statusCount * 100) / tmpJsonArray.length();
                }
                temp.put(STATUS_COLUMN, progress + "%");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, LIST, R.layout.view_column_item,
                new String[]{NO_COMMUNITY, NAME_COMMUNITY_OWNER, STATUS_COLUMN},
                new int[]{R.id.tvNumber, R.id.tvOwnerName, R.id.tvProgress}
        );
        listCommunity.setAdapter(simpleAdapter);
    }

}
