package th.co.cinfo.chumchon.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import th.co.cinfo.chumchon.models.*;

public class HouseholdActivity extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener, AdapterView.OnItemClickListener {
    ListView listViewHousehold;
    Button btnRefresh, btnSearch, btnPrevious, btnNext;
    EditText etSearch;
    int PAGE_NUMBER = 1;
    String apiURL = "https://api.cinfo.co.th/v2/getTaskList_F01_01?";
    String whatUWant = "Houses";
    String STRING_JSONDATA;
    Intent intent;
    GestureDetector gestureScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household);
        ModelToken.checkToken(this);
        init();
    }

    void init() {
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

        listViewHousehold = (ListView) findViewById(R.id.listHousehold);
        listViewHousehold.setOnItemClickListener(this);

        listViewHousehold.setOnTouchListener(new View.OnTouchListener() {
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
            String strSearch = etSearch.getText().toString();
            PAGE_NUMBER = ModelGetJson.getSearch(this, STRING_JSONDATA, strSearch, 1, listViewHousehold);
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
        } else {
            Toast.makeText(getApplicationContext(), "ยังไม่ทำ เดี๋ยวทำเพิ่ม", Toast.LENGTH_SHORT).show();
        }
    }

    void refreshPage() {
        STRING_JSONDATA = ModelGetData.getJsonArray(this, apiURL, whatUWant);
        PAGE_NUMBER = ModelGetJson.getHouseholdHeadJson(this, STRING_JSONDATA, 1, listViewHousehold);
    }

    void changePage(int pPage) {
        PAGE_NUMBER = ModelGetJson.getHouseholdHeadJson(this, STRING_JSONDATA, pPage, listViewHousehold);
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
        if (Math.abs(e1.getX() - e2.getX()) < 100) {
            return false;
        }
        if (e1.getX() > e2.getX()) {
            changePage(PAGE_NUMBER + 1);
        } else if (e1.getX() < e2.getX()) {
            changePage(PAGE_NUMBER - 1);
        }
        return false;
    }
}
