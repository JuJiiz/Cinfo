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

    String NO_HOUSE_HOLD = "number";
    String NAME_HOUSE_HOLD = "name";
    String STATUS_HOUSE_HOLD = "status";
    ArrayList<HashMap<String, String>> LIST;
    ListView listViewHousehold;
    int rowSize = 5;
    LinearLayout linearScroll;
    Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household);
        ModelToken.checkToken(this);
        init();
        getData();
    }

    void init(){
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
        String apiURL = "https://api.cinfo.co.th/v2/getTaskList_F01_01?";
        ListView listView = (ListView) findViewById(R.id.listHousehold);
        LIST = new ArrayList<HashMap<String, String>>();
        JSONObject jsonObject = null;
        try {
            String strJsonObj = ModelGetData.getByName(this, apiURL, "Houses");
            JSONArray jsonArray = new JSONArray(strJsonObj);
            for (int i=0 ; i < jsonArray.length(); i++){
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                HashMap<String, String> temp = new HashMap<String, String>();
                temp.put(NO_HOUSE_HOLD,jsonObj.getString("H_NO"));
                JSONObject tmpJsonObj = new JSONObject(jsonObj.getString("zone"));
                temp.put(NAME_HOUSE_HOLD,tmpJsonObj.getString("VIL_NAME"));
                tmpJsonObj = new JSONObject(jsonObj.getString("SURV_Status"));
                temp.put(STATUS_HOUSE_HOLD,tmpJsonObj.getString("status"));
                LIST.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, LIST, R.layout.view_house_hold_item,
                new String[]{NO_HOUSE_HOLD, NAME_HOUSE_HOLD, STATUS_HOUSE_HOLD},
                new int[]{R.id.tvNoHousehold, R.id.tvNameHousehold, R.id.tvStatusHousehold}
        );
        listView.setAdapter(simpleAdapter);
    }

}