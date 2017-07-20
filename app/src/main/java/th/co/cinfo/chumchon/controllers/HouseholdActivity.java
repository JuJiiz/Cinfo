package th.co.cinfo.chumchon.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.*;

public class HouseholdActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    //String CHILD_TASKID_COLUMN;
    ListView listViewHousehold;
    Button btnRefresh;
    String apiURL = "https://api.cinfo.co.th/v2/getTaskList_F01_01?";
    String whatUWant = "Houses";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household);
        ModelToken.checkToken(this);
        init();
    }

    void init() {
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        listViewHousehold = (ListView) findViewById(R.id.listHousehold);
        btnRefresh.setOnClickListener(this);
        ModelGetJson.getChildJson(this, apiURL, whatUWant, listViewHousehold);
        listViewHousehold.setOnItemClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btnRefresh) {
            ModelGetJson.getChildJson(this, apiURL, whatUWant, listViewHousehold);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> Item = (HashMap<String, String>) listViewHousehold.getItemAtPosition(position);
        String SelectedItem = Item.get("taskid").toString();
        if (!SelectedItem.equals("")) {
            intent = new Intent(getApplicationContext(), TaskgroupF0101Activity.class);
            intent.putExtra("TaskID", SelectedItem);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "ยังไม่ทำ เดี๋ยวทำเพิ่ม", Toast.LENGTH_SHORT).show();
        }
    }
}
