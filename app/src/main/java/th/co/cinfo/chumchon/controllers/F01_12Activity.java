package th.co.cinfo.chumchon.controllers;

import android.app.DatePickerDialog;
import android.icu.util.BuddhistCalendar;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.ModelGetData;
import th.co.cinfo.chumchon.models.ModelGetFormData;
import th.co.cinfo.chumchon.models.ModelGetVillage;
import th.co.cinfo.chumchon.models.ModelSendApi;

public class F01_12Activity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, RadioGroup.OnCheckedChangeListener {
    //topic 1
    RadioGroup peopleRadioGroup;
    EditText etFirstName, etLastName, etPersonId, corporateName, localGovernmentName, otherDepartmentName;
    //topic 2
    RadioGroup rdgMarketComplaints;
    EditText etSlaughterHouseName, etSlaughterHouseNumber, etSlaughterHouseLicenceDate, etBuildingAccessDate, etAreaAccessDate,
            etRegisteredCapital, etNumbersOfEmployment;
    Spinner spSlaughterHouseGenre, spVillagesName;
    //topic 3
    RadioGroup rdgMarketRenting, rdgPropertyRenting;
    EditText etF01_12_phone,etF01_12_18 , etF01_12_19;
    Spinner spF01_12_L_01;
    //topic credit
    EditText etInformantFirstName, etInformantLastName, etInformantPosition, etInformantTelephone, etInformationDate;

    Button btnSubmit;
    Calendar mCalendar;
    private int mYear, mMonth, mDay;
    private EditText TMP_EDIT_TEXT;
    private ArrayList<String> listVillagesId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f01_12);
        init();
        initsSetup();
        initSetValues();
    }

    void init() {
        /*spSlaughterHouseGenre = (Spinner) findViewById(R.id.spSlaughterHouseGenre);
        ArrayList<String> stringArrayList = new ArrayList<String>();
        stringArrayList.add("tawun1");
        stringArrayList.add("tawun2");
        stringArrayList.add("tawun3");
        ArrayAdapter<String> adapterThai = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, stringArrayList);
        spSlaughterHouseGenre.setAdapter(adapterThai);

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray("[{\"b\":\"1\"},{\"b\":\"2\"},{\"b\":\"1\"}]");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","tawun");
            jsonArray.put(jsonObject);
            Log.d("HHHHHHHHH", jsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        */
        /***************************************************************************
        View view1 = findViewById(android.R.id.content);
        View textView = view1.findViewWithTag("ssss");
        Log.d("8888888888888", "init: "+(textView==null));
        TextView textView1 = (TextView) textView;
        Log.e("5555555555555", "init: "+ (textView instanceof Button) + "//" + textView1.getText() );
        textView1.setText("55555");
         **************************************************************************/
        peopleRadioGroup = (RadioGroup) findViewById(R.id.peopleRadioGroup);
        peopleRadioGroup.setOnCheckedChangeListener(this);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPersonId = (EditText) findViewById(R.id.etPersonId);
        corporateName = (EditText) findViewById(R.id.corporateName);
        localGovernmentName = (EditText) findViewById(R.id.localGovernmentName);
        otherDepartmentName = (EditText) findViewById(R.id.otherDepartmentName);

        rdgMarketComplaints = (RadioGroup) findViewById(R.id.rdgMarketComplaints);
        etSlaughterHouseName = (EditText) findViewById(R.id.etSlaughterHouseName);
        etSlaughterHouseNumber = (EditText) findViewById(R.id.etSlaughterHouseNumber);
        etSlaughterHouseLicenceDate = (EditText) findViewById(R.id.etSlaughterHouseLicenceDate);
        etSlaughterHouseLicenceDate.setOnTouchListener(this);
        etBuildingAccessDate = (EditText) findViewById(R.id.etBuildingAccessDate);
        etBuildingAccessDate.setOnTouchListener(this);
        etAreaAccessDate = (EditText) findViewById(R.id.etAreaAccessDate);
        etAreaAccessDate.setOnTouchListener(this);
        etRegisteredCapital = (EditText) findViewById(R.id.etRegisteredCapital);
        etNumbersOfEmployment = (EditText) findViewById(R.id.etNumbersOfEmployment);
        spSlaughterHouseGenre = (Spinner) findViewById(R.id.spSlaughterHouseGenre);
        spVillagesName = (Spinner) findViewById(R.id.spVillagesName);

        rdgMarketRenting = (RadioGroup) findViewById(R.id.rdgMarketRenting);
        rdgMarketRenting.setOnCheckedChangeListener(this);
        rdgPropertyRenting = (RadioGroup) findViewById(R.id.rdgPropertyRenting);
        rdgPropertyRenting.setOnCheckedChangeListener(this);
        etF01_12_18 = (EditText) findViewById(R.id.etF01_12_18);
        etF01_12_19 = (EditText) findViewById(R.id.etF01_12_19);
        etF01_12_phone = (EditText) findViewById(R.id.etF01_12_phone);
        spF01_12_L_01 = (Spinner) findViewById(R.id.spF01_12_L_01);

        etInformantFirstName = (EditText) findViewById(R.id.etInformantFirstName);
        etInformantLastName = (EditText) findViewById(R.id.etInformantLastName);
        etInformantPosition = (EditText) findViewById(R.id.etInformantPosition);
        etInformantTelephone = (EditText) findViewById(R.id.etInformantTelephone);
        etInformationDate = (EditText) findViewById(R.id.etInformationDate);
        String year = (Calendar.getInstance().get(Calendar.YEAR) + 543) + "";
        String timeStamp = new SimpleDateFormat("dd/MM/").format(Calendar.getInstance().getTime())+year;
        etInformationDate.setText(timeStamp);

        btnSubmit = (Button) findViewById(R.id.btnSavingData);
        btnSubmit.setOnClickListener(this);

    }

    void initsSetup() {
        String[] itemSlaughterHouseGenre = getResources().getStringArray(R.array.itemSlaughterHouseGenre);
        ArrayAdapter<String> adapterEnglish = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, itemSlaughterHouseGenre);
        spSlaughterHouseGenre.setAdapter(adapterEnglish);

        String[] itemStatusHire = getResources().getStringArray(R.array.itemStatusHire);
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, itemStatusHire);
        spF01_12_L_01.setAdapter(adapterStatus);
        try {
            listVillagesId = new ArrayList<String>();
            ArrayList<String> tmpArrayList = new ArrayList<String>();
            JSONArray jsonArray = new JSONArray(ModelGetVillage.getByName(this,"village"));
            for (int i=0; i<jsonArray.length() ; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                listVillagesId.add(jsonObject.getString("VILLAGE_ID"));
                tmpArrayList.add(jsonObject.getString("VILLAGE_NAME"));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, tmpArrayList);
            spVillagesName.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    void initSetValues() {

        View root = findViewById(android.R.id.content);
        try {
            JSONObject jsData= new JSONObject(ModelGetFormData.getByName(this, "5982cf11c9d8ea0008fda776", "F01_12", "DATAS"));
            Iterator x = jsData.keys();
            JSONArray jsonArray = new JSONArray();
            while (x.hasNext()){

                String name = (String) x.next();
                String value = jsData.getString(name);

                View view = root.findViewWithTag(name);
                if (view == null) continue;
                if (view instanceof EditText) {
                    EditText textView = (EditText) view;
                    textView.setText(value);
                }
                else if (view instanceof RadioGroup) {
                    RadioGroup radioGroup = (RadioGroup) view;
                    RadioButton radioButton = (RadioButton) radioGroup.findViewWithTag(value);
                    radioGroup.check(radioButton.getId());
                }
                else if (view instanceof Spinner) {
                    Spinner spinner = (Spinner) view;
                }
            }
            Log.e("kkkkkkkkkkkkk", jsData.keys().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    void submit() {
        int coutProgress = 0;
        //topic 1
        ModelSendApi modelSendApi = new ModelSendApi(this, "F01_12");
        RadioButton tmpRdBtn = (RadioButton) findViewById(peopleRadioGroup.getCheckedRadioButtonId());
        modelSendApi.add(peopleRadioGroup.getTag().toString(), tmpRdBtn.getTag().toString());
        switch (tmpRdBtn.getTag().toString()) {
            case "0":
                String tmp1 = etFirstName.getText().toString();
                String tmp2 = etLastName.getText().toString();
                String tmp3 = etPersonId.getText().toString();
                modelSendApi.add(etFirstName.getTag().toString(), tmp1);
                modelSendApi.add(etLastName.getTag().toString(), tmp2);
                modelSendApi.add(etPersonId.getTag().toString(), tmp3);
                if (!(tmp1.equals("") || tmp2.equals("") || tmp3.equals(""))) {
                    coutProgress++;
                }
                break;
            case "1":
                modelSendApi.add(corporateName.getTag().toString(), corporateName.getText().toString());
                if (!corporateName.getText().toString().equals("")) {
                    coutProgress++;
                }
                break;
            case "2":
                modelSendApi.add(localGovernmentName.getTag().toString(), localGovernmentName.getText().toString());
                if (!corporateName.getText().toString().equals("")) {
                    coutProgress++;
                }
                break;
            case "3":
                modelSendApi.add(otherDepartmentName.getTag().toString(), otherDepartmentName.getText().toString());
                if (!corporateName.getText().toString().equals("")) {
                    coutProgress++;
                }
                break;
            default:
                break;
        }

        //topic 2
        modelSendApi.add(etSlaughterHouseName.getTag().toString(), etSlaughterHouseName.getText().toString());
        if (!etSlaughterHouseName.equals("")) {
            coutProgress++;
        }
        modelSendApi.add(etSlaughterHouseNumber.getTag().toString(), etSlaughterHouseNumber.getText().toString());
        modelSendApi.add(etSlaughterHouseLicenceDate.getTag().toString(), etSlaughterHouseLicenceDate.getText().toString());
        modelSendApi.add(spSlaughterHouseGenre.getTag().toString(), spSlaughterHouseGenre.getSelectedItem().toString());
        if (!spSlaughterHouseGenre.getSelectedItem().toString().equals("")) {
            coutProgress++;
        }
        //map
        modelSendApi.add(etBuildingAccessDate.getTag().toString(), etBuildingAccessDate.getText().toString());
        modelSendApi.add(etAreaAccessDate.getTag().toString(), etAreaAccessDate.getText().toString());
        modelSendApi.add(etRegisteredCapital.getTag().toString(), etRegisteredCapital.getText().toString());
        if (!etRegisteredCapital.getText().toString().equals("")) {
            coutProgress++;
        }
        modelSendApi.add(etNumbersOfEmployment.getTag().toString(), etNumbersOfEmployment.getText().toString());
        if (!etNumbersOfEmployment.getText().toString().equals("")) {
            coutProgress++;
        }
        if (rdgMarketComplaints.getCheckedRadioButtonId() != -1) {
            tmpRdBtn = (RadioButton) findViewById(rdgMarketComplaints.getCheckedRadioButtonId());
            modelSendApi.add(rdgMarketComplaints.getTag().toString(), tmpRdBtn.getTag().toString());
        }

        //topic 3
        if (rdgMarketRenting.getCheckedRadioButtonId() != -1) {
            tmpRdBtn = (RadioButton) findViewById(rdgMarketRenting.getCheckedRadioButtonId());
            modelSendApi.add(rdgMarketRenting.getTag().toString(), tmpRdBtn.getTag().toString());
            if (tmpRdBtn.getTag().toString().equals("0")){
                modelSendApi.add(etF01_12_18.getTag().toString(), etF01_12_18.getText().toString());
                modelSendApi.add(etF01_12_19.getTag().toString(), etF01_12_19.getText().toString());
            }
        }
        if (rdgPropertyRenting.getCheckedRadioButtonId() != -1) {
            tmpRdBtn = (RadioButton) findViewById(rdgPropertyRenting.getCheckedRadioButtonId());
            modelSendApi.add(rdgPropertyRenting.getTag().toString(), tmpRdBtn.getTag().toString());
            if (tmpRdBtn.getTag().toString().equals("1")) {
                modelSendApi.add(spF01_12_L_01.getTag().toString(), spF01_12_L_01.getSelectedItem().toString());
                modelSendApi.add(etF01_12_phone.getTag().toString(), etF01_12_phone.getText().toString());
            }
        }
        modelSendApi.add(etInformantFirstName.getTag().toString(), etInformantFirstName.getText().toString());
        modelSendApi.add(etInformantLastName.getTag().toString(), etInformantLastName.getText().toString());
        modelSendApi.add(etInformantPosition.getTag().toString(), etInformantPosition.getText().toString());
        modelSendApi.add(etInformantTelephone.getTag().toString(), etInformantTelephone.getText().toString());

        int progressStatus = (coutProgress*100) / 5;
        if (progressStatus < 70){
            Toast.makeText(this,"กรุณากรอกข้อมูลสำคัญมากกว่า 70 % ขึ้นไป\nความคืบหน้าปัจจุบัน "+progressStatus+"%",Toast.LENGTH_LONG).show();
            return;
        }
        modelSendApi.add("successForm", progressStatus + "");

        modelSendApi.sendInsert();
        Toast.makeText(this, "Save", Toast.LENGTH_LONG).show();
    }

    private void showDateDialog(EditText pEditText) {
        TMP_EDIT_TEXT = (EditText) findViewById(pEditText.getId());
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        TMP_EDIT_TEXT.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + (year + 543));

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    @Override
    public void onClick(View v) {
        if (v == btnSubmit) {
            RadioButton radioButton = (RadioButton) peopleRadioGroup.findViewWithTag("1");
            Log.e("************", "onClick: "+radioButton.getText().toString());
            //submit();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (v == etSlaughterHouseLicenceDate) {
                showDateDialog(etSlaughterHouseLicenceDate);
            } else if (v == etAreaAccessDate) {
                showDateDialog(etAreaAccessDate);
            } else if (v == etBuildingAccessDate) {
                showDateDialog(etBuildingAccessDate);
            }
        }
        return false;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (group == peopleRadioGroup) {
            LinearLayout tmpLinearLayout0 = (LinearLayout) findViewById(R.id.layoutPeople0);
            tmpLinearLayout0.setVisibility(LinearLayout.GONE);
            LinearLayout tmpLinearLayout1 = (LinearLayout) findViewById(R.id.layoutPeople1);
            tmpLinearLayout1.setVisibility(LinearLayout.GONE);
            LinearLayout tmpLinearLayout2 = (LinearLayout) findViewById(R.id.layoutPeople2);
            tmpLinearLayout2.setVisibility(LinearLayout.GONE);
            LinearLayout tmpLinearLayout3 = (LinearLayout) findViewById(R.id.layoutPeople3);
            tmpLinearLayout3.setVisibility(LinearLayout.GONE);
            RadioButton tmpRadioBtn = (RadioButton) findViewById(peopleRadioGroup.getCheckedRadioButtonId());
            switch (tmpRadioBtn.getTag().toString()) {
                case "0":
                    tmpLinearLayout0.setVisibility(LinearLayout.VISIBLE);
                    break;
                case "1":
                    tmpLinearLayout1.setVisibility(LinearLayout.VISIBLE);
                    break;
                case "2":
                    tmpLinearLayout2.setVisibility(LinearLayout.VISIBLE);
                    break;
                case "3":
                    tmpLinearLayout3.setVisibility(LinearLayout.VISIBLE);
                    break;
            }
        }
        else if (group == rdgMarketRenting) {
            LinearLayout tmpLinearLayout = (LinearLayout) findViewById(R.id.layoutMarketRenting);
            tmpLinearLayout.setVisibility(LinearLayout.GONE);
            RadioButton tmpRadioBtn = (RadioButton) findViewById(rdgMarketRenting.getCheckedRadioButtonId());
            switch (tmpRadioBtn.getTag().toString()) {
                case "0":
                    tmpLinearLayout.setVisibility(LinearLayout.VISIBLE);
                    break;
            }
        }
        else if (group == rdgPropertyRenting) {
            LinearLayout tmpLinearLayout = (LinearLayout) findViewById(R.id.layoutPropertyRenting);
            tmpLinearLayout.setVisibility(LinearLayout.GONE);
            RadioButton tmpRadioBtn = (RadioButton) findViewById(rdgPropertyRenting.getCheckedRadioButtonId());
            switch (tmpRadioBtn.getTag().toString()) {
                case "1":
                    tmpLinearLayout.setVisibility(LinearLayout.VISIBLE);
                    break;
            }
        }
    }
}
