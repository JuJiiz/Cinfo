package th.co.cinfo.chumchon.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import th.co.cinfo.chumchon.R;
import th.co.cinfo.chumchon.models.ModelToken;

public class InsertActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton ibtHousehold, ibtCommucial, ibtLocalGovern, ibtCommunity, ibtForms44;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        ModelToken.checkToken(this);
        init();
    }

    private void init() {
        ibtHousehold = (ImageButton) findViewById(R.id.ibtHousehold);
        ibtCommucial = (ImageButton) findViewById(R.id.ibtCommucial);
        ibtLocalGovern = (ImageButton) findViewById(R.id.ibtLocalGovern);
        ibtCommunity = (ImageButton) findViewById(R.id.ibtCommunity);
        ibtForms44 = (ImageButton) findViewById(R.id.ibtForms44);

        ibtHousehold.setOnClickListener(this);
        ibtCommucial.setOnClickListener(this);
        ibtLocalGovern.setOnClickListener(this);
        ibtCommunity.setOnClickListener(this);
        ibtForms44.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ibtHousehold) {
            intent = new Intent(getApplicationContext(), HouseholdActivity.class);
            startActivity(intent);
        }
        if (v == ibtCommucial) {
            intent = new Intent(getApplicationContext(), CommucialActivity.class);
            startActivity(intent);
        }
        if (v == ibtLocalGovern) {
            intent = new Intent(getApplicationContext(), LocalGovernActivity.class);
            startActivity(intent);
        }
        if (v == ibtCommunity) {
            intent = new Intent(getApplicationContext(), CommunityActivity.class);
            startActivity(intent);
        }
        if (v == ibtForms44) {
            intent = new Intent(getApplicationContext(), Forms44Activity.class);
            startActivity(intent);
        }
    }
}
