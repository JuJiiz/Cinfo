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
import th.co.cinfo.chumchon.models.ModelToken;

public class CommucialActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ListView listCommucial;
    Button btnRefresh;
    String apiURL = "https://api.cinfo.co.th/v3/getGroupAsset_T2?";
    String whatUWant = "task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commucial);
        ModelToken.checkToken(this);
        init();
        ModelGetJson.getHeadJson(this, apiURL, whatUWant, listCommucial);
    }

    private void init() {
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        listCommucial = (ListView) findViewById(R.id.listCommucial);

        btnRefresh.setOnClickListener(this);
        listCommucial.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnRefresh) {
            ModelGetJson.getHeadJson(this, apiURL, whatUWant, listCommucial);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Dialog dialog = new Dialog(CommucialActivity.this);
        dialog.requestWindowFeature
                (dialog.getWindow().FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_popup);
        ListView lvDialog = (ListView) dialog.findViewById(R.id.lvDialog);
        ModelGetJson.getDialogJson(this, apiURL, whatUWant, position, lvDialog);
        dialog.show();
    }
}
