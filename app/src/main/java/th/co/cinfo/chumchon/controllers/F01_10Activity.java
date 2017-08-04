package th.co.cinfo.chumchon.controllers;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;

import com.google.android.gms.location.LocationListener;

import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.ModelGetJson;
import th.co.cinfo.chumchon.models.ModelSendApi;
import th.co.cinfo.chumchon.models.ModelToken;

import com.google.android.gms.location.LocationServices;

/**
 * Created by JuJiiz on 26/7/2560.
 */

public class F01_10Activity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    int formProgress = 0;
    Button btnSavingData;

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;

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

        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFrag.getMapAsync(this);
    }

    private void init() {
        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnGetPosition = (Button) findViewById(R.id.btnGetPosition);
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

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        Toast.makeText(getApplicationContext(), location.getLatitude() + " and " + location.getLongitude(), Toast.LENGTH_SHORT).show();

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(F01_10Activity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
