package th.co.cinfo.chumchon.controllers;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.ModelGetData;
import th.co.cinfo.chumchon.models.ModelGetJson;

public class TaskgroupF0101Activity extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener, AdapterView.OnItemClickListener {
    ListView lvData, lvDialog;
    Button btnRefresh, btnSearch, btnPrevious, btnNext;
    EditText etSearch;
    int PAGE_NUMBER = 1;
    String apiURL = "https://api.cinfo.co.th/v3/getGroupAsset_T1?";
    String whatUWant = "data";
    String taskID;
    String STRING_JSONDATA;
    String strSearch;
    Boolean SearchStatus = false;
    Intent intent;
    GestureDetector gestureScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskgroup_f0101);
        taskID = getIntent().getExtras().getString("TaskID");
        init();
        //ModelGetJson.getHouseholdChildJson(this, apiURL, whatUWant, taskID, lvData);
    }

    private void init() {
        gestureScanner = new GestureDetector(getBaseContext(), this);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnNext = (Button) findViewById(R.id.btnNext);

        btnRefresh.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        etSearch = (EditText) findViewById(R.id.etSearch);

        lvData = (ListView) findViewById(R.id.lvData);
        lvData.setOnItemClickListener(this);

        lvData.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureScanner.onTouchEvent(event);
            }
        });
        refreshPage();
    }

    @Override
    public void onClick(View v) {
        if (v == btnRefresh) {
            refreshPage();
        }
        if (v == btnPrevious) {
            changePage(PAGE_NUMBER - 1);
        }
        if (v == btnNext) {
            changePage(PAGE_NUMBER + 1);
        }
        if (v == btnSearch) {
            strSearch = etSearch.getText().toString();
            PAGE_NUMBER = ModelGetJson.getSearchHouseholdChild(this, STRING_JSONDATA, strSearch, 1, lvData);
            SearchStatus = true;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Dialog dialog = new Dialog(TaskgroupF0101Activity.this);
        dialog.requestWindowFeature
                (dialog.getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_popup);
        lvDialog = (ListView) dialog.findViewById(R.id.lvDialog);
        ModelGetJson.getHouseholdChildDialogJson(this, apiURL, whatUWant, taskID, position, lvDialog);
        dialog.show();
        lvDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> Item = (HashMap<String, String>) lvDialog.getItemAtPosition(position);
                String SelectedTaskItem = Item.get("task").toString();
                String SelectedStatusItem = Item.get("status").toString();

                Toast.makeText(TaskgroupF0101Activity.this, SelectedTaskItem, Toast.LENGTH_SHORT).show();
            }
        });
    }

    void refreshPage() {
        STRING_JSONDATA = ModelGetData.getHouseholdJsonArray(this, apiURL, whatUWant, taskID);
        PAGE_NUMBER = ModelGetJson.getHouseholdChildJson(this, STRING_JSONDATA, 1, lvData);
        SearchStatus = false;
    }

    void changePage(int pPage) {
        if(SearchStatus = false){
            PAGE_NUMBER = ModelGetJson.getHouseholdChildJson(this, STRING_JSONDATA, pPage, lvData);
        }else {
            PAGE_NUMBER = ModelGetJson.getSearchHouseholdChild(this, STRING_JSONDATA, strSearch, pPage, lvData);
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
