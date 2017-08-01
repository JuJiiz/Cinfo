package th.co.cinfo.chumchon.controllers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.ModelGetJson;
import th.co.cinfo.chumchon.models.ModelSendApi;
import th.co.cinfo.chumchon.models.ModelToken;

/**
 * Created by JuJiiz on 26/7/2560.
 */

public class F01_10Activity extends AppCompatActivity implements View.OnClickListener, LocationListener {
    int formProgress = 0;
    Button btnSavingData;
    private GoogleMap googleMap;

    LocationManager locationManager;
    LocationListener locationListener;
    Double Latitude = 0.00;
    Double Longitude = 0.00;

    private static final LatLng ONE = new LatLng(32.882216, -117.222028);
    /*private static final LatLng TWO = new LatLng(32.872000, -117.232004);
    private static final LatLng THREE = new LatLng(32.880252, -117.233034);
    private static final LatLng FOUR = new LatLng(32.885200, -117.226003);*/

    private ArrayList<LatLng> coords = new ArrayList<LatLng>();
    //private static final int POINTS = 4;

    //Topic1
    RadioGroup ownerRadioGroup;
    RadioButton ownerRadioButton;
    LinearLayout loPeopleName, loCorporateName, loGovernmentName, loDepartName;
    EditText etFirstName, etLastName, etPersonId, corporateName, localGovernmentName, otherDepartmentName;
    //Topic2
    EditText etMarketName, etRegisterDate, etBuildingAccessDate, etLandAccessDate, etRegisteredCapital, etNumbersOfEmployment, etLumen, etLampDist;
    Spinner spMarketGenre, spVillagesName;
    RadioGroup rdgMarketComplaints, rdgMarketAssessment, rdgMarketDegree;
    RadioButton rbMarketComplaints, rdMarketAssessment, rdMarketDegree;
    MapView mapMapMap;
    Button btnGetPosition;
    //Topic3
    RadioGroup rdgMarketRenting, rdgPropertyRenting;
    RadioButton rdMarketRenting, rdPropertyRenting;
    LinearLayout loPropertyRenting, loElectric;
    Spinner spRentStatus;
    EditText etRentingTel;
    ImageView ivImageUpload;
    Button etPropertyPhoto;
    //ผู้ให้ข้อมูล
    EditText etInformantFirstName, etInformantLastName, etInformantPosition, etInformantTelephone, etInformationDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f01_10);
        ModelToken.checkToken(this);
        init();
        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        /*mapMapMap.onCreate(savedInstanceState);
        mapMapMap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                setUpMap(googleMap);
            }
        });*/
    }

    private void init() {
        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnGetPosition = (Button) findViewById(R.id.btnGetPosition);
        etPropertyPhoto = (Button) findViewById(R.id.etPropertyPhoto);

        mapMapMap = (MapView) findViewById(R.id.mapMapMap);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPersonId = (EditText) findViewById(R.id.etPersonId);
        corporateName = (EditText) findViewById(R.id.corporateName);
        localGovernmentName = (EditText) findViewById(R.id.localGovernmentName);
        otherDepartmentName = (EditText) findViewById(R.id.otherDepartmentName);
        etMarketName = (EditText) findViewById(R.id.etMarketName);
        etRegisterDate = (EditText) findViewById(R.id.etRegisterDate);
        etBuildingAccessDate = (EditText) findViewById(R.id.etBuildingAccessDate);
        etLandAccessDate = (EditText) findViewById(R.id.etLandAccessDate);
        etRegisteredCapital = (EditText) findViewById(R.id.etRegisteredCapital);
        etNumbersOfEmployment = (EditText) findViewById(R.id.etNumbersOfEmployment);
        etLumen = (EditText) findViewById(R.id.etLumen);
        etLampDist = (EditText) findViewById(R.id.etLampDist);
        etRentingTel = (EditText) findViewById(R.id.etRentingTel);
        etInformantFirstName = (EditText) findViewById(R.id.etInformantFirstName);
        etInformantLastName = (EditText) findViewById(R.id.etInformantLastName);
        etInformantPosition = (EditText) findViewById(R.id.etInformantPosition);
        etInformantTelephone = (EditText) findViewById(R.id.etInformantTelephone);
        etInformationDate = (EditText) findViewById(R.id.etInformationDate);

        ownerRadioGroup = (RadioGroup) findViewById(R.id.ownerRadioGroup);
        ownerRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                ownerRadioButton = (RadioButton) findViewById(ownerRadioGroup.getCheckedRadioButtonId());
                loPeopleName.setVisibility(View.GONE);
                loCorporateName.setVisibility(View.GONE);
                loGovernmentName.setVisibility(View.GONE);
                loDepartName.setVisibility(View.GONE);
                if (ownerRadioButton.getTag().toString().equals("0")) {
                    loPeopleName.setVisibility(View.VISIBLE);
                }
                if (ownerRadioButton.getTag().toString().equals("1")) {
                    loCorporateName.setVisibility(View.VISIBLE);
                }
                if (ownerRadioButton.getTag().toString().equals("2")) {
                    loGovernmentName.setVisibility(View.VISIBLE);
                }
                if (ownerRadioButton.getTag().toString().equals("3")) {
                    loDepartName.setVisibility(View.VISIBLE);
                }
            }
        });

        rdgMarketComplaints = (RadioGroup) findViewById(R.id.rdgMarketComplaints);
        rdgMarketComplaints.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                rbMarketComplaints = (RadioButton) findViewById(rdgMarketComplaints.getCheckedRadioButtonId());
            }
        });

        rdgMarketAssessment = (RadioGroup) findViewById(R.id.rdgMarketAssessment);
        rdgMarketAssessment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                rdMarketAssessment = (RadioButton) findViewById(rdgMarketAssessment.getCheckedRadioButtonId());
            }
        });

        rdgMarketDegree = (RadioGroup) findViewById(R.id.rdgMarketDegree);
        rdgMarketDegree.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                rdMarketDegree = (RadioButton) findViewById(rdgMarketDegree.getCheckedRadioButtonId());
            }
        });

        rdgMarketRenting = (RadioGroup) findViewById(R.id.rdgMarketRenting);
        rdgMarketRenting.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                rdMarketRenting = (RadioButton) findViewById(rdgMarketRenting.getCheckedRadioButtonId());
                if (rdMarketRenting.getTag().toString().equals("0")) {
                    loElectric.setVisibility(View.VISIBLE);
                }
                if (rdMarketRenting.getTag().toString().equals("1")) {
                    loElectric.setVisibility(View.GONE);
                }
            }
        });

        rdgPropertyRenting = (RadioGroup) findViewById(R.id.rdgPropertyRenting);
        rdgPropertyRenting.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                rdPropertyRenting = (RadioButton) findViewById(rdgPropertyRenting.getCheckedRadioButtonId());
                if (rdPropertyRenting.getTag().toString().equals("0")) {
                    loPropertyRenting.setVisibility(View.GONE);
                }
                if (rdPropertyRenting.getTag().toString().equals("1")) {
                    loPropertyRenting.setVisibility(View.VISIBLE);
                }
            }
        });

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
        /*String strFirstName = etFirstName.getText().toString();
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
        }*/
    }

    @Override
    public void onClick(View v) {
        if (v == btnSavingData) {

        }
    }

    public void submit() {
        ModelSendApi modelSendApi = new ModelSendApi(this, "F01_10");
        modelSendApi.add(ownerRadioGroup.getTag().toString(), ownerRadioButton.getTag().toString());
        modelSendApi.add(etFirstName.getTag().toString(), etFirstName.getText().toString());
        modelSendApi.add(etLastName.getTag().toString(), etLastName.getText().toString());
        modelSendApi.add(etPersonId.getTag().toString(), etPersonId.getText().toString());
        modelSendApi.add(corporateName.getTag().toString(), corporateName.getText().toString());
        modelSendApi.add(localGovernmentName.getTag().toString(), localGovernmentName.getText().toString());
        modelSendApi.add(otherDepartmentName.getTag().toString(), otherDepartmentName.getText().toString());
        modelSendApi.add(etMarketName.getTag().toString(), etMarketName.getText().toString());
        modelSendApi.add(etRegisterDate.getTag().toString(), etRegisterDate.getText().toString());
        modelSendApi.add(etBuildingAccessDate.getTag().toString(), etBuildingAccessDate.getText().toString());
        modelSendApi.add(etRegisteredCapital.getTag().toString(), etRegisteredCapital.getText().toString());
        modelSendApi.add(etNumbersOfEmployment.getTag().toString(), etNumbersOfEmployment.getText().toString());
        //
        modelSendApi.add(etLumen.getTag().toString(), etLumen.getText().toString());
        modelSendApi.add(etLampDist.getTag().toString(), etLampDist.getText().toString());
        //
        modelSendApi.add(etRentingTel.getTag().toString(), etRentingTel.getText().toString());
        modelSendApi.add(etInformantFirstName.getTag().toString(), etInformantFirstName.getText().toString());
        modelSendApi.add(etInformantLastName.getTag().toString(), etInformantLastName.getText().toString());
        modelSendApi.add(etInformantPosition.getTag().toString(), etInformantPosition.getText().toString());
        modelSendApi.add(etInformantTelephone.getTag().toString(), etInformantTelephone.getText().toString());
        modelSendApi.add(etInformationDate.getTag().toString(), etInformationDate.getText().toString());

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

    /*private void setUpMap(GoogleMap map) {
        googleMap = map;

        coords.add(ONE);
        for (int i = 0; i < 1; i++) {
            googleMap.addMarker(new MarkerOptions()
                    .position(coords.get(i))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }
    }*/

    @Override
    public void onLocationChanged(Location location) {

        if (location == null) { return; }

        //** Get Latitude & Longitude
        Latitude = location.getLatitude();
        Longitude= location.getLongitude();

        googleMap.clear();

        //*** Focus & Zoom
        LatLng coordinate = new LatLng(Latitude, Longitude);
        googleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 17));

        //*** Marker
        MarkerOptions marker = new MarkerOptions()
                .position(new LatLng(Latitude, Longitude)).title("Your current location");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
        googleMap.addMarker(marker);
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Log.d("Latitude","status");
    }
}
