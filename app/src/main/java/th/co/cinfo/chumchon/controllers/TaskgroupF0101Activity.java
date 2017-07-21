package th.co.cinfo.chumchon.controllers;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.ModelGetJson;

public class TaskgroupF0101Activity extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemClickListener {
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
        ModelGetJson.getHouseholdChildJson(this, apiURL, whatUWant, taskID, lvData);
    }

    private void init() {
        lvData = (ListView) findViewById(R.id.lvData);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);

        btnRefresh.setOnClickListener(this);
        lvData.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnRefresh) {
            ModelGetJson.getHouseholdChildJson(this, apiURL, whatUWant, taskID, lvData);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Dialog dialog = new Dialog(TaskgroupF0101Activity.this);
        dialog.requestWindowFeature
                (dialog.getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_popup);
        ListView lvDialog = (ListView) dialog.findViewById(R.id.lvDialog);
        ModelGetJson.getHouseholdChildDialogJson(this, apiURL, whatUWant, taskID, position, lvDialog);
        dialog.show();
    }
}
