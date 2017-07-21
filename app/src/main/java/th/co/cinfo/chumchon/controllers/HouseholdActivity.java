package th.co.cinfo.chumchon.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.*;

public class HouseholdActivity extends AppCompatActivity implements View.OnClickListener , GestureDetector.OnGestureListener {
    ListView listViewHousehold;
    Button btnRefresh;
    String STRING_JSONDATA = "";
    int PAGE_NUMBER = 1;
    String apiURL = "https://api.cinfo.co.th/v2/getTaskList_F01_01?";
    String whatUWant = "Houses";
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
        listViewHousehold = (ListView) findViewById(R.id.listHousehold);
        listViewHousehold.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureScanner.onTouchEvent(event);
            }
        });
        btnRefresh.setOnClickListener(this);
        //refreshPage();
        ModelGetJson.getChildJson(this, apiURL, whatUWant, listViewHousehold);
    }

    void refreshPage(){
        //STRING_JSONDATA = ModelGetData.getByName(this, apiURL, whatUWant);
        //PAGE_NUMBER = ModelGetJson.getChildJsonByPage(this,STRING_JSONDATA,listViewHousehold,1);
    }

    void changePage(int pPage){
        //PAGE_NUMBER = ModelGetJson.getChildJsonByPage(this,STRING_JSONDATA,listViewHousehold,pPage);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureScanner.onTouchEvent(event);
    }


    @Override
    public void onClick(View v) {
        if (v == btnRefresh) {
            refreshPage();
            //ModelGetJson.getChildJson(this, apiURL, whatUWant, listViewHousehold);
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
        if(Math.abs(e1.getX() - e2.getX())<100){
            return false;
        }
        if(e1.getX() > e2.getX()){
            changePage(PAGE_NUMBER+1);
        }else if (e1.getX() < e2.getX()){
            changePage(PAGE_NUMBER-1);
        }
        return false;
    }
}