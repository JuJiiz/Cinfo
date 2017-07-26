package th.co.cinfo.chumchon.controllers;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.ModelGetJson;
import th.co.cinfo.chumchon.models.ModelSendApi;
import th.co.cinfo.chumchon.models.ModelToken;

/**
 * Created by JuJiiz on 26/7/2560.
 */

public class F01_10Activity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    int formProgress = 0;
    //Topic1
    RadioButton rbPeople, rbCorporate, rbLocalGovernment, rbOtherDepartment;
    LinearLayout loPeopleName, loCorporateName, loGovernmentName, loDepartName;
    EditText etFirstName, etLastName, etPersonId, corporateName, localGovernmentName, otherDepartmentName;
    //Topic2
    EditText etMarketName, etRegisterDate, etBuildingAccessDate, etRegisteredCapital, etNumbersOfEmployment, etLumen, etLampDist, etPropertyRentingYes;
    Spinner spMarketGenre, spVillagesName;
    RadioButton MarketComplaintsYes, MarketComplaintsNo, MarketAssessmentPass, MarketAssessmentFail, MarketDegreeGood, MarketDegreeBest;
    //Topic3
    RadioButton MarketRentingYes, MarketRentingNo, PropertyRentingYes, PropertyRentingNo;
    LinearLayout loPropertyRenting, loElectric;
    ImageView ivImageUpload;
    Button etPropertyPhoto;
    Spinner spRentStatus;
    //ผู้ให้ข้อมูล
    EditText etInformantFirstName, etInformantLastName, etInformantPosition, etInformantTelephone, etInformationDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f01_10);
        ModelToken.checkToken(this);
        init();
    }

    private void init() {
        etPropertyPhoto = (Button) findViewById(R.id.etPropertyPhoto);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPersonId = (EditText) findViewById(R.id.etPersonId);
        corporateName = (EditText) findViewById(R.id.corporateName);
        localGovernmentName = (EditText) findViewById(R.id.localGovernmentName);
        otherDepartmentName = (EditText) findViewById(R.id.otherDepartmentName);
        etMarketName = (EditText) findViewById(R.id.etMarketName);
        etRegisterDate = (EditText) findViewById(R.id.etRegisterDate);
        etBuildingAccessDate = (EditText) findViewById(R.id.etBuildingAccessDate);
        etRegisteredCapital = (EditText) findViewById(R.id.etRegisteredCapital);
        etNumbersOfEmployment = (EditText) findViewById(R.id.etNumbersOfEmployment);
        etLumen = (EditText) findViewById(R.id.etLumen);
        etLampDist = (EditText) findViewById(R.id.etLampDist);
        etPropertyRentingYes = (EditText) findViewById(R.id.etPropertyRentingYes);
        etInformantFirstName = (EditText) findViewById(R.id.etInformantFirstName);
        etInformantLastName = (EditText) findViewById(R.id.etInformantLastName);
        etInformantPosition = (EditText) findViewById(R.id.etInformantPosition);
        etInformantTelephone = (EditText) findViewById(R.id.etInformantTelephone);
        etInformationDate = (EditText) findViewById(R.id.etInformationDate);

        rbPeople = (RadioButton) findViewById(R.id.rbPeople);
        rbCorporate = (RadioButton) findViewById(R.id.rbCorporate);
        rbLocalGovernment = (RadioButton) findViewById(R.id.rbLocalGovernment);
        rbOtherDepartment = (RadioButton) findViewById(R.id.rbOtherDepartment);
        MarketComplaintsYes = (RadioButton) findViewById(R.id.MarketComplaintsYes);
        MarketComplaintsNo = (RadioButton) findViewById(R.id.MarketComplaintsNo);
        MarketAssessmentPass = (RadioButton) findViewById(R.id.MarketAssessmentPass);
        MarketAssessmentFail = (RadioButton) findViewById(R.id.MarketAssessmentFail);
        MarketDegreeGood = (RadioButton) findViewById(R.id.MarketDegreeGood);
        MarketDegreeBest = (RadioButton) findViewById(R.id.MarketDegreeBest);
        MarketRentingYes = (RadioButton) findViewById(R.id.MarketRentingYes);
        MarketRentingNo = (RadioButton) findViewById(R.id.MarketRentingNo);
        PropertyRentingYes = (RadioButton) findViewById(R.id.PropertyRentingYes);
        PropertyRentingNo = (RadioButton) findViewById(R.id.PropertyRentingNo);

        rbPeople.setOnCheckedChangeListener(this);
        rbCorporate.setOnCheckedChangeListener(this);
        rbLocalGovernment.setOnCheckedChangeListener(this);
        rbOtherDepartment.setOnCheckedChangeListener(this);


        loPeopleName = (LinearLayout) findViewById(R.id.loPeopleName);
        loCorporateName = (LinearLayout) findViewById(R.id.loCorporateName);
        loGovernmentName = (LinearLayout) findViewById(R.id.loGovernmentName);
        loDepartName = (LinearLayout) findViewById(R.id.loDepartName);
        loElectric = (LinearLayout) findViewById(R.id.loElectric);
        loPropertyRenting = (LinearLayout) findViewById(R.id.loPropertyRenting);

        spMarketGenre = (Spinner) findViewById(R.id.spMarketGenre);
        spVillagesName = (Spinner) findViewById(R.id.spVillagesName);
        spRentStatus = (Spinner) findViewById(R.id.spRentStatus);

        ivImageUpload = (ImageView) findViewById(R.id.ivImageUpload);
    }

    private void requireCheck() {
        String strFirstName = etFirstName.getText().toString();
        String strLastName = etLastName.getText().toString();
        String strPersonID = etPersonId.getText().toString();
        String strCorporate = rbCorporate.getText().toString();
        String strLocalGovernment = rbLocalGovernment.getText().toString();
        String strOtherDepartment = rbOtherDepartment.getText().toString();
        String strMarketName = etMarketName.getText().toString();
        String strRegisteredCapital = etRegisteredCapital.getText().toString();
        String strNumbersOfEmployment = etNumbersOfEmployment.getText().toString();
        String strMarketGenre = spMarketGenre.getSelectedItem().toString();

        //Topic1
        if (rbPeople.isChecked()) {
            if (strFirstName.equals("") || strLastName.equals("") || strPersonID.equals("")) {
            } else {
                formProgress++;
            }
        } else if (rbCorporate.isChecked()) {
            if (strCorporate.equals("")) {
            } else {
                formProgress++;
            }
        } else if (rbLocalGovernment.isChecked()) {
            if (strLocalGovernment.equals("")) {
            } else {
                formProgress++;
            }
        } else if (rbOtherDepartment.isChecked()) {
            if (strOtherDepartment.equals("")) {
            } else {
                formProgress++;
            }
        } else {
        }

        //Topic2
        if (strMarketName.equals("")) {
        } else {
            formProgress++;
        }
        if (strMarketGenre.equals("")) {
        } else {
            formProgress++;
        }
        if (strRegisteredCapital.equals("")) {
        } else {
            formProgress++;
        }
        if (strNumbersOfEmployment.equals("")) {
        } else {
            formProgress++;
        }
        if (!MarketComplaintsYes.isChecked() || !MarketComplaintsNo.isChecked()) {
        } else {
            formProgress++;
        }
        if (!MarketAssessmentPass.isChecked() || !MarketAssessmentFail.isChecked()) {
        } else {
            formProgress++;
        }
        if (!MarketDegreeGood.isChecked() || !MarketDegreeBest.isChecked()) {
        } else {
            formProgress++;
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        loPeopleName.setVisibility(View.GONE);
        loCorporateName.setVisibility(View.GONE);
        loGovernmentName.setVisibility(View.GONE);
        loDepartName.setVisibility(View.GONE);
        if (rbPeople.isChecked()) loPeopleName.setVisibility(View.VISIBLE);
        if (rbCorporate.isChecked()) loCorporateName.setVisibility(View.VISIBLE);
        if (rbLocalGovernment.isChecked()) loGovernmentName.setVisibility(View.VISIBLE);
        if (rbOtherDepartment.isChecked()) loDepartName.setVisibility(View.VISIBLE);
    }

    public void submit(){
        ModelSendApi modelSendApi = new ModelSendApi(this,"F01_10");
        modelSendApi.add(etFirstName.getTag().toString(),etFirstName.getText().toString());
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        modelSendApi.add(etPersonId.getTag().toString(),etPersonId.getText().toString());
        modelSendApi.add(corporateName.getTag().toString(),corporateName.getText().toString());
        modelSendApi.add(localGovernmentName.getTag().toString(),localGovernmentName.getText().toString());
        modelSendApi.add(otherDepartmentName.getTag().toString(),otherDepartmentName.getText().toString());
        modelSendApi.add(etMarketName.getTag().toString(),etMarketName.getText().toString());
        modelSendApi.add(etRegisterDate.getTag().toString(),etRegisterDate.getText().toString());
        modelSendApi.add(etBuildingAccessDate.getTag().toString(),etBuildingAccessDate.getText().toString());
        modelSendApi.add(etRegisteredCapital.getTag().toString(),etRegisteredCapital.getText().toString());
        modelSendApi.add(etNumbersOfEmployment.getTag().toString(),etNumbersOfEmployment.getText().toString());
        modelSendApi.add(etLumen.getTag().toString(),etLumen.getText().toString());
        modelSendApi.add(etLampDist.getTag().toString(),etLampDist.getText().toString());
        modelSendApi.add(etPropertyRentingYes.getTag().toString(),etPropertyRentingYes.getText().toString());
        modelSendApi.add(etInformantFirstName.getTag().toString(),etInformantFirstName.getText().toString());
        modelSendApi.add(etInformantLastName.getTag().toString(),etInformantLastName.getText().toString());
        modelSendApi.add(etInformantPosition.getTag().toString(),etInformantPosition.getText().toString());
        modelSendApi.add(etInformantTelephone.getTag().toString(),etInformantTelephone.getText().toString());
        modelSendApi.add(etInformationDate.getTag().toString(),etInformationDate.getText().toString());

        rbPeople = (RadioButton) findViewById(R.id.rbPeople);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        rbCorporate = (RadioButton) findViewById(R.id.rbCorporate);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        rbLocalGovernment = (RadioButton) findViewById(R.id.rbLocalGovernment);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        rbOtherDepartment = (RadioButton) findViewById(R.id.rbOtherDepartment);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        MarketComplaintsYes = (RadioButton) findViewById(R.id.MarketComplaintsYes);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        MarketComplaintsNo = (RadioButton) findViewById(R.id.MarketComplaintsNo);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        MarketAssessmentPass = (RadioButton) findViewById(R.id.MarketAssessmentPass);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        MarketAssessmentFail = (RadioButton) findViewById(R.id.MarketAssessmentFail);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        MarketDegreeGood = (RadioButton) findViewById(R.id.MarketDegreeGood);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        MarketDegreeBest = (RadioButton) findViewById(R.id.MarketDegreeBest);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        MarketRentingYes = (RadioButton) findViewById(R.id.MarketRentingYes);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        MarketRentingNo = (RadioButton) findViewById(R.id.MarketRentingNo);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        PropertyRentingYes = (RadioButton) findViewById(R.id.PropertyRentingYes);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());
        PropertyRentingNo = (RadioButton) findViewById(R.id.PropertyRentingNo);
        modelSendApi.add(etLastName.getTag().toString(),etLastName.getText().toString());

        rbPeople.setOnCheckedChangeListener(this);
        rbCorporate.setOnCheckedChangeListener(this);
        rbLocalGovernment.setOnCheckedChangeListener(this);
        rbOtherDepartment.setOnCheckedChangeListener(this);


        loPeopleName = (LinearLayout) findViewById(R.id.loPeopleName);
        loCorporateName = (LinearLayout) findViewById(R.id.loCorporateName);
        loGovernmentName = (LinearLayout) findViewById(R.id.loGovernmentName);
        loDepartName = (LinearLayout) findViewById(R.id.loDepartName);
        loElectric = (LinearLayout) findViewById(R.id.loElectric);
        loPropertyRenting = (LinearLayout) findViewById(R.id.loPropertyRenting);

        spMarketGenre = (Spinner) findViewById(R.id.spMarketGenre);
        spVillagesName = (Spinner) findViewById(R.id.spVillagesName);
        spRentStatus = (Spinner) findViewById(R.id.spRentStatus);

        ivImageUpload = (ImageView) findViewById(R.id.ivImageUpload);
    }
}
