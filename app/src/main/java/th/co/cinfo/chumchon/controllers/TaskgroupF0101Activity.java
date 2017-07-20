package th.co.cinfo.chumchon.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.ModelGetJson;

public class TaskgroupF0101Activity extends AppCompatActivity implements View.OnClickListener {
    ListView lvData;
    Button btnRefresh;
    String apiURL = "https://api.cinfo.co.th/v3/getGroupAsset_T1?";
    String whatUWant = "data";
    String taskID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskgroup_f0101);
        taskID = getIntent().getExtras().getString("TaskID");
        init();
        ModelGetJson.getGroupAssetJson(this, apiURL, whatUWant, taskID, lvData);
    }

    private void init() {
        lvData = (ListView) findViewById(R.id.lvData);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);

        btnRefresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnRefresh) {
            Toast.makeText(getApplicationContext(), taskID, Toast.LENGTH_SHORT).show();
            //ModelGetJson.getGroupAssetJson(this, apiURL, whatUWant, taskID, lvData);
        }
    }
}
